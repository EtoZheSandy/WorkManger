package su.afk.workmanger

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay
import java.util.UUID

class DownloadImageWorker(
    private val appContext: Context,
    private val workerParameters: WorkerParameters
): CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        delay(2000)
        Log.d("TAG", "Картинка скачана")
        return Result.success(workDataOf("image" to UUID.randomUUID().toString()))
    }


}