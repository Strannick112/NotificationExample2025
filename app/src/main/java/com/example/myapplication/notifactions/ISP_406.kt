package com.example.myapplication.notifactions

import android.app.NotificationChannelGroup
import android.app.NotificationManager

class ISP_406 (notificationManager: NotificationManager) {
    val notificationChannelGroup: NotificationChannelGroup = NotificationChannelGroup(channelGroupId, "Россия")

    init {
        notificationManager.createNotificationChannelGroup(notificationChannelGroup)
        ChargeNotificationChannel(notificationManager)
    }

    companion object {
        val channelGroupId = "russian"
    }
}
