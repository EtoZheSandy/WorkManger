package su.afk.workmanger

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class ApplyFilterWorker(
    private val appContext: Context,
    private val workerParameters: WorkerParameters
): CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        val imageName = workerParameters.inputData.getString("image") ?: "Image not found"
        delay(2000)
        Log.d("TAG", "Apply Filter to: $imageName")
        return Result.success(workDataOf("filter" to imageName))
    }


}