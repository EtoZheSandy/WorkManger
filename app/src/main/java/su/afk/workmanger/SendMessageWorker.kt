package su.afk.workmanger

import android.app.Notification
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay


//class SendMessageWorker: Worker() {
class SendMessageWorker(
    private val appContext: Context,
    private val workerParameters: WorkerParameters
): CoroutineWorker(appContext = appContext, params = workerParameters) {

    override suspend fun doWork(): Result {
        val messageText = workerParameters.inputData.getString("message") ?: "Message is null"
        setProgress(workDataOf("loading" to true))
        setForeground(getForegroundInfo()) // благодаря Foreground наш Worker продолжит работу даже при закрытие приложения и уничтожения activity
        delay(5000)
        Log.d("TAG", "send.... workmanager: $messageText")
        setProgress(workDataOf("loading" to false))
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