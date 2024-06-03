package su.afk.workmanger

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "send Message notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            // создаем канал уведомлений через NotificationManager
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "sendMessage" // id канала для уведомлений
    }
}