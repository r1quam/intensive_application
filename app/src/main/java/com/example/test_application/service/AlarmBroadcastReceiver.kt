package com.example.test_application.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.test_application.R

class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ALARM) {
            showNotification(context)
        }
    }

    private fun showNotification(context: Context) {
        createNotificationChannel(context, ALARM_CHANNEL_ID)
        val builder = NotificationCompat.Builder(context, ALARM_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Напоминание")
            .setContentText("Пора покормить кота")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(ALARM_NOTIFICATION_ID, builder.build())
    }

    private fun createNotificationChannel(
        context: Context,
        channelId: String,
        importance: Int = IMPORTANCE_HIGH,
    ) {
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val name = context.getString(R.string.app_name)
        val channel = NotificationChannel(channelId, name, importance)
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val ALARM = "ALARM"
        const val ALARM_CHANNEL_ID = "ALARM_CHANNEL_ID"
        const val ALARM_NOTIFICATION_ID = 999
    }
}