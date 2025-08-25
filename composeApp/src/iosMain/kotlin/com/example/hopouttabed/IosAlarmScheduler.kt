package com.example.hopouttabed

import platform.Foundation.*
import platform.UserNotifications.*

actual fun provideAlarmScheduler(): AlarmScheduler = IosAlarmScheduler()

class IosAlarmScheduler : AlarmScheduler {

    private val center: UNUserNotificationCenter = UNUserNotificationCenter.currentNotificationCenter()

    override fun schedule(alarmId: String, epochMillis: Long, title: String, body: String) {
        // Ask for notification permission if needed (no-op if already granted)
        requestAuthorizationIfNeeded()

        val content = UNMutableNotificationContent().apply {
            setTitle(title)
            setBody(body)
            setSound( UNNotificationSound.defaultSound()) // TODO change later
        }

        // Convert epochMillis -> calendar components (local time)
        val date = NSDate.dateWithTimeIntervalSince1970(epochMillis.toDouble() / 1000.0)

        val comps = NSCalendar.currentCalendar.components(
            NSCalendarUnitYear or NSCalendarUnitMonth or NSCalendarUnitDay or
                    NSCalendarUnitHour or NSCalendarUnitMinute or NSCalendarUnitSecond,
            fromDate = date
        )

        val trigger = UNCalendarNotificationTrigger.triggerWithDateMatchingComponents(
            dateComponents = comps,
            repeats = false
        )

        val request = UNNotificationRequest.requestWithIdentifier(
            identifier = alarmId,
            content = content,
            trigger = trigger
        )

        center.addNotificationRequest(request) { error ->
            if (error != null) {
                // Log however you prefer; println works fine on iOS logs
                println("IosAlarmScheduler: failed to schedule $alarmId -> $error")
            }
        }
    }

    override fun cancel(alarmId: String) {
        center.removePendingNotificationRequestsWithIdentifiers(listOf(alarmId))
        center.removeDeliveredNotificationsWithIdentifiers(listOf(alarmId))
    }

    override fun cancelAll() {
        center.removeAllPendingNotificationRequests()
        center.removeAllDeliveredNotifications()
    }

    private fun requestAuthorizationIfNeeded() {
        center.getNotificationSettingsWithCompletionHandler { settings ->
            if (settings?.authorizationStatus != UNAuthorizationStatusAuthorized) {
                center.requestAuthorizationWithOptions(
                    options = UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge
                ) { granted, error ->
                    if (!granted) {
                        println("IosAlarmScheduler: notification permission not granted ($error)")
                    }
                }
            }
        }
    }
}
