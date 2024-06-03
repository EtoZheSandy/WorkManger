package su.afk.workmanger

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class SaveImageWorker(
    private val appContext: Context,
    private val workerParameters: WorkerParameters
): CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        val imageName = workerParameters.inputData.getString("filter") ?: return Result.failure()
        delay(2000)
        Log.d("TAG", "Картинка Сохранена: $imageName")
        return Result.success()
    }


}