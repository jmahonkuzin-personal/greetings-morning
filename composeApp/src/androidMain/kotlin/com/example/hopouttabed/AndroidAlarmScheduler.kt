package com.example.hopouttabed

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.core.os.postDelayed

actual fun provideAlarmScheduler(): AlarmScheduler = AndroidAlarmScheduler(AppContext.get())

class AndroidAlarmScheduler(
    private val context: Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun schedule(alarmId: String, epochMillis: Long, title: String, body: String) {
        if (isSOrAbove() && !alarmManager.canScheduleExactAlarms()) {
            Toast.makeText(context, "SORABOVETRUE", Toast.LENGTH_SHORT).show()
            // Surface a UX to route the user to Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
            // (Don’t startActivity here directly from lib code.)
            return
        }

        val pendingIntent = buildPendingIntent(
            alarmId = alarmId,
            title = title,
            body = body,
            createIfAbsent = true
        )

        if (isMOrAbove()) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                epochMillis,
                pendingIntent
            )
        } else {
            @Suppress("DEPRECATION")
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                epochMillis,
                pendingIntent
            )
        }
    }

    override fun cancel(alarmId: String) {
        buildPendingIntent(alarmId = alarmId, title = "", body = "", createIfAbsent = false)
            ?.let { alarmManager.cancel(it) }
    }

    override fun cancelAll() {
        // No global cancel in AlarmManager; track your own IDs and call cancel(id) for each.
        // This is a no-op by design; keep a repository of scheduled ids in your app layer.
    }

    private fun buildPendingIntent(
        alarmId: String,
        title: String,
        body: String,
        createIfAbsent: Boolean
    ): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            action = AlarmReceiver.ACTION_ALARM_FIRED
            putExtra(AlarmReceiver.EXTRA_ALARM_ID, alarmId)
            putExtra(AlarmReceiver.EXTRA_TITLE, title)
            putExtra(AlarmReceiver.EXTRA_BODY, body)
        }

        val flags = PendingIntent.FLAG_IMMUTABLE or
                if (createIfAbsent) PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_NO_CREATE

        // Use a stable requestCode per id
        val requestCode = alarmId.hashCode()

        return PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            flags
        )
    }

    companion object {
        fun exactAlarmSettingsIntent(context: Context) =
            if (isSOrAbove()) {
                Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                    .setData(android.net.Uri.parse("package:${context.packageName}"))
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            } else null

        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.M)
        private fun isMOrAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
        private fun isSOrAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    }
}
