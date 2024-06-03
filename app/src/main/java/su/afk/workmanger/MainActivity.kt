package su.afk.workmanger

import android.app.Notification
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {
    val sendMessageWorkRequest = OneTimeWorkRequest.from(SendMessageWorker::class.java)
    val workManager = WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            // помещается в очередь workManager и выполняет работу даже если приложение было закрыто
            // но не уничтожено, при уничтожение - служба остановится
            workManager.enqueue(sendMessageWorkRequest)
        }
    }

}