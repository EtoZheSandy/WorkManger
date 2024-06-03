package su.afk.workmanger

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.time.Duration

class MainActivity : AppCompatActivity() {
    val workManager = WorkManager.getInstance(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val downloadImageWorkRequest = PeriodicWorkRequestBuilder<DownloadImageWorker>( // переодическое выполнение
                Duration.ofHours(12) // минимум раз в 15 минут
            ) // можно использовать для синхронизации данных
        }
    }

}