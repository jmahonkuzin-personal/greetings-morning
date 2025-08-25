package com.example.hopouttabed

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.provider.Settings
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class AlarmSoundService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val title = intent?.getStringExtra(AlarmReceiver.EXTRA_TITLE) ?: "Alarm"
        val body  = intent?.getStringExtra(AlarmReceiver.EXTRA_BODY) ?: "It's time!"

        ensureChannel()
        startForeground(NOTIF_ID, buildNotification(title, body))

        playAlarmTone()

        // Auto-stop after 60s (adjust as you like)
        Handler(Looper.getMainLooper()).postDelayed({ stopSelf() }, 60_000)

        return START_NOT_STICKY
    }

    private fun playAlarmTone() {
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        mediaPlayer = MediaPlayer().apply {
            setDataSource(this@AlarmSoundService, uri)
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
            isLooping = true
            prepare()
            start()
        }
    }

    override fun onDestroy() {
        mediaPlayer?.run {
            try { stop() } catch (_: Throwable) {}
            release()
        }
        mediaPlayer = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun ensureChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Alarms",
                NotificationManager.IMPORTANCE_HIGH
            )
            mgr.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(title: String, body: String): Notification {
        // Build the "Stop" action PendingIntent
        val stopIntent = Intent(this, StopAlarmReceiver::class.java).apply {
            action = ACTION_STOP
        }
        val stopPending = PendingIntent.getBroadcast(
            this,
            0,
            stopIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Ensure channel exists on O+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ensureChannel() // your existing function that creates CHANNEL_ID
        }

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)         // use your own if you have one
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true)
            // For Android 12+ foreground services, makes it show immediately
            .setForegroundServiceBehavior(
                NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE
            )
            .addAction(
                android.R.drawable.ic_media_pause,  // non-zero, valid icon
                "Stop",
                stopPending
            )
            .build()
    }

//    private fun buildNotification(title: String, body: String) =
//        NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
//            .setContentTitle(title)
//            .setContentText(body)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .addAction(
//                0, "Stop",
//                PendingIntents.stopAction(this)
//            )
//            .setOngoing(true)
//            .build()

    companion object {
        private const val CHANNEL_ID = "alarm_sound"
        private const val NOTIF_ID = 1001
        const val ACTION_STOP = "com.example.hopouttabed.ACTION_STOP_ALARM"

        fun start(context: Context, title: String, body: String) {
            val i = Intent(context, AlarmSoundService::class.java).apply {
                putExtra(AlarmReceiver.EXTRA_TITLE, title)
                putExtra(AlarmReceiver.EXTRA_BODY, body)
            }
            ContextCompat.startForegroundService(context, i)
        }

        fun stop(context: Context) {
            context.stopService(Intent(context, AlarmSoundService::class.java))
        }
    }
}

class StopAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == AlarmSoundService.ACTION_STOP) {
            AlarmSoundService.stop(context)
        }
    }
}