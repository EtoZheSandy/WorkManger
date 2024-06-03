package su.afk.workmanger

import android.app.Notification
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.UUID

class MainActivity : AppCompatActivity() {
//    val sendMessageWorkRequest = OneTimeWorkRequest.from(SendMessageWorker::class.java)
    val workManager = WorkManager.getInstance(this)
    var loading = false
    var workerId: UUID? = null // у каждого workRequest есть свой id для выполнения

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val editText = findViewById<EditText>(R.id.editTextText)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            // помещается в очередь workManager и выполняет работу даже если приложение было закрыто
            // но не уничтожено, при уничтожение - служба остановится если не использовать Foreground service
//            workManager.enqueue(sendMessageWorkRequest)

            val sendMessageWorkRequest = OneTimeWorkRequestBuilder<SendMessageWorker>()
                .setInputData(workDataOf("message" to editText.text.toString())) // ключ - значение
                // добавим ограничеuние, работает лишь когда есть подключения к интернету
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED) // ограничение на тип сети, без сети оно не запустится
                        .build()
                ).build()
            // если отправить с выкл инт то сообщение просто поместится в очередь и будет выполнено при появление сети

            workManager.enqueue(sendMessageWorkRequest) // добавляем запрос в очередь WorkManager
            workerId = sendMessageWorkRequest.id // получаем id выполнения запроса



            // отслеживание прогресс бара во время загрузки
            workerId?.let { uUID ->
                workManager.getWorkInfoByIdLiveData(uUID).observe(this) {
                    if(it != null){
                        loading = it.progress.getBoolean("loading", false)
                    }
                    if(loading) progressBar.isVisible = true else progressBar.isVisible = false // прогресс бар
                }
            }
        }
    }

}