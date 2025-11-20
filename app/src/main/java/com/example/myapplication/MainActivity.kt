package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.myapplication.notifactions.ChargeNotification
import com.example.myapplication.notifactions.ISP_406
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    lateinit var chargeBroadcastReceiver: ChargeBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        ISP_406(notificationManager)
        chargeBroadcastReceiver = ChargeBroadcastReceiver()
        setContent {
            val chargeNotification = ChargeNotification(
                LocalContext.current, notificationManager
            )
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding),
                        chargeNotification
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(
            chargeBroadcastReceiver,
            IntentFilter(Intent.ACTION_POWER_CONNECTED)
                .apply { addAction(Intent.ACTION_POWER_DISCONNECTED) }
        )
    }

    override fun onPause() {
        super.onPause()
//        unregisterReceiver(
//            chargeBroadcastReceiver
//        )
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    chargeNotification: ChargeNotification
) {
    Column(){
        Button(onClick = {
            chargeNotification.makeNotification("Зарядка подключена")}){
            Text("Click me...")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyApplicationTheme {
//        Greeting("Android")
//    }
//}