package java.com.example.jetpackdemo.ui.activity.wrokmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityWorkManagerBaseBinding
import java.util.concurrent.TimeUnit


/**
 * Create by Yang Yang on 2023/5/25
 */
class WorkManagerBaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityWorkManagerBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_work_manager_base)

        binding.buttonAddWork.setOnClickListener {
            //设置触发条件
            val constraints = Constraints.Builder()
            //.setReguiredNetworkType (NetworkType.CONNECTED) // N路连接时执行
            //.setRequiresBatteryNotLow(true)//不在电量不足执行
            //.setRequrresCharging(true)//在充电时执行
            //.setRegurresStorageNotLow(true)//不在存储容量不足时执行
            //.setRequiresDeviceIdle(true)//在待机状态下执行调用用需要API级别最低为23
            //NetworkType.NOT REQUIRED:对网络没有要求
            //NetworkType.CONNECTED:网络连接的时候执行
            //NetworkType.UNMETERED:不计费的网络比如WIFI下执行
            //NetworkType.NOT_ROAMING:非漫游网络状态
            //NetworkType.METERED:计费网络比如3G,4G下执行。
            //注意:不代表恢复网络了,就立马执行
            .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()


            //WorkRequest --- 配置任务
            //这里是一次性执行的任务
            val workRequest = OneTimeWorkRequest.Builder(MyWork::class.java)
                //设置触发条件
                .setConstraints(constraints)
                //设置延迟执行
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build()

            val workManager = WorkManager.getInstance(this)
            workManager.enqueue(workRequest)
        }

    }
}