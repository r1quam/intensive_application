package com.example.test_application.utils

import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import com.example.test_application.service.AlarmBroadcastReceiver
import java.util.Calendar
class AlarmUtils(private val context: Context) {
    private val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager

    fun setAlarm(time: Calendar) {
        alarmManager.setExactAndAllowWhileIdle(
            RTC_WAKEUP,
            time.timeInMillis,
            getPendingIntent(),
        )
    }

    private fun getPendingIntent() = PendingIntent.getBroadcast(
        context,
        0,
        Intent(context, AlarmBroadcastReceiver::class.java).apply {
            action = AlarmBroadcastReceiver.ALARM
        },
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
    )
}