package com.example.myapplication

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService
import com.example.myapplication.notifactions.ChargeNotification

class ChargeBroadcastReceiver : BroadcastReceiver() {

    var chargeNotification: ChargeNotification? = null
    var notificationManager: NotificationManager? = null

    override fun onReceive(context: Context, intent: Intent) {
        if (notificationManager == null)
            notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE)
                        as NotificationManager
        if(chargeNotification == null)
            chargeNotification = ChargeNotification(
                context, notificationManager!!
            )
        Log.d("broadcast_test", "Broadcast message is received")
        if(intent.action == Intent.ACTION_POWER_CONNECTED) {
            chargeNotification?.makeNotification("Зарядка подключена")
            val intent = Intent(context, MainActivity::class.java)
                .apply{
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
            context.startActivity(intent)
            Log.d("broadcast_test", "Intent is sended")
        }
        if(intent.action == Intent.ACTION_POWER_DISCONNECTED)
            chargeNotification?.makeNotification("Зарядка отключена")
    }
}
