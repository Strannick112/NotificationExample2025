package com.example.myapplication.notifactions

import android.app.NotificationChannel
import android.app.NotificationManager

class ChargeNotificationChannel(notificationManager: NotificationManager) {
    val channel: NotificationChannel = NotificationChannel(
        channelId, "Первый", NotificationManager.IMPORTANCE_HIGH
    ).apply {
        description = "Самый самый чудесный первый канал, российский"
        group = ISP_406.channelGroupId
    }

    init {
        notificationManager.createNotificationChannel(channel)
    }

    companion object{
        val channelId = "my_channel_id"
    }
}