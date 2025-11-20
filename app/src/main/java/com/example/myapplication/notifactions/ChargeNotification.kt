package com.example.myapplication.notifactions

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.myapplication.MainActivity
import com.example.myapplication.R

class ChargeNotification(
    val context: Context,
    val notificationManager: NotificationManager
) {
    val channelId = ChargeNotificationChannel.channelId
    val notificationId = 1
    fun makeNotification(text: String){
        // Создать уведомление
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setLargeIcon(BitmapFactory.decodeResource(
                context.resources,
                R.drawable.ic_launcher_foreground)
            )
            .setContentTitle("Состояние зарядки")
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        // Показать уведомление
        notificationManager.notify(notificationId, builder)
    }
}