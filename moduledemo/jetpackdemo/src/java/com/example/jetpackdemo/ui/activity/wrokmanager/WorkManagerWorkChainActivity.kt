package java.com.example.jetpackdemo.ui.activity.wrokmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityWorkManagerWorkChainBinding


/**
 * Create by Yang Yang on 2023/5/26
 */
class WorkManagerWorkChainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityWorkManagerWorkChainBinding = DataBindingUtil.setContentView(this, R.layout.activity_work_manager_work_chain)

        binding.buttonAddWork.setOnClickListener {
            val workOne = OneTimeWorkRequest.Builder(WorkChainOne::class.java).build()
            val workTwo = OneTimeWorkRequest.Builder(WorkChainTwo::class.java).build()

            //任务链，先执行One然后执行Two
            WorkManager.getInstance(this)
                .beginWith(workOne)
                .then(workTwo)
                .enqueue()
        }
    }
}