package su.afk.workmanger

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {
    val workManager = WorkManager.getInstance(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val downloadImageWorker = OneTimeWorkRequest.from(DownloadImageWorker::class.java)
            val applyFilterWorker = OneTimeWorkRequest.from(ApplyFilterWorker::class.java)
            val saveImageWorker = OneTimeWorkRequest.from(SaveImageWorker::class.java)

            workManager
//                .beginWith(downloadImageWorker) // последовательное выполнение но если кликнуть нескольк раз они будут выполнятся все вместе
                .beginUniqueWork("image", ExistingWorkPolicy.KEEP, downloadImageWorker) // применяем политику при повторном клике по заданию workManager
                .then(applyFilterWorker)
                .then(saveImageWorker)
                .enqueue() // ставим цепочку запросов в очередь
        }
    }

}