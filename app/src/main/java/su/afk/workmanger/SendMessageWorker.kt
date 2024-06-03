package su.afk.workmanger

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay


//class SendMessageWorker: Worker() {
class SendMessageWorker(
    private val appContext: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(appContext = appContext, params = workerParameters) {
    override suspend fun doWork(): Result {
        // благодаря Foreground наш Worker продолжит работу даже при закрытие приложения
        // и уничтожения activity
        setForeground(getForegroundInfo())
        delay(5000)
        println("send.... workmanager")
        return Result.success()
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(1, createNotification())
    }

    //
    private fun createNotification(): Notification {
        val notification = NotificationCompat.Builder(appContext, App.CHANNEL_ID)
            .setContentTitle("Загрузка фотографии профиля")
            .setContentText("Фотография отправляется")
            .setSmallIcon(R.drawable.ic_message)
            .build()
        return notification
    }
}