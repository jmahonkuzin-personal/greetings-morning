package com.example.hopouttabed

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import timber.log.Timber

class AlarmReceiver : BroadcastReceiver() {

//    private val context = AppContext.get()


    override fun onReceive(context: Context, intent: Intent?) {
        Timber.d("AlarmReceiver onReceive: ${intent?.action}")
        Toast.makeText(context, R.string.app_name, Toast.LENGTH_SHORT).show()

        playTestBeep(context, 500)
        handleIntent(context, intent)
    }

    private fun handleIntent(context: Context, intent: Intent?) {
        when (intent?.action) {
            ACTION_ALARM_FIRED -> {
                val alarmScreenIntent = Intent(context, AlarmActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(alarmScreenIntent)
            }

            Intent.ACTION_BOOT_COMPLETED -> {
                Timber.i("BOOT_COMPLETED → reschedule alarms")
                // TODO: reschedule all exact alarms here from storage/DB
            }

            AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED -> {
                Timber.i("Exact alarm permission state changed → re-check & reschedule")
                // TODO: check permission state and reschedule if granted
            }

            else -> {
                Timber.w("Unknown action: ${intent?.action}")
            }
        }
    }

    companion object {
        const val ACTION_ALARM_FIRED = "com.example.hopouttabed.ACTION_ALARM_FIRED"
        const val EXTRA_ALARM_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_BODY = "extra_body"
    }

    private fun playTestBeep(context: Context, ms: Long) {
//        val context = AppContext.get() // your existing app-wide context
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val r: Ringtone = RingtoneManager.getRingtone(context, uri).apply {
            audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        }

        r.play()
        // use the classic overload (no ktx needed)
        Handler(Looper.getMainLooper()).postDelayed(
            { try { r.stop() } catch (_: Throwable) {} },
            ms
        )
    }
}