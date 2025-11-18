package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
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
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

// Создать NotificationChannel (только для Android 8.0+)
        val channelId = "my_channel_id"
        val channelName = "Мой канал"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = "Описание канала"
        }
        notificationManager.createNotificationChannel(channel)


        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

fun makeNotification(context: Context){
    // Создать уведомление
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

//    val notificationId = 1
    val channelId = "my_channel_id"
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_launcher_foreground) // Установите свой значок
        .setContentTitle("Заголовок уведомления")
        .setContentText("Текст уведомления")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .build()

// Показать уведомление
    notificationManager.notify(System.currentTimeMillis().toInt(), builder)
}

@Composable
fun Greeting( modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(){
        Button(onClick = {makeNotification(context)}){
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