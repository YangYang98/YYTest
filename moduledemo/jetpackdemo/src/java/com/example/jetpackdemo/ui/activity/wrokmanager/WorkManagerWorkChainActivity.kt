package java.com.example.jetpackdemo.ui.activity.wrokmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkContinuation
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

        binding.buttonWorkCombination.setOnClickListener {
            val workA = OneTimeWorkRequest.Builder(WorkChainA::class.java).build()
            val workB = OneTimeWorkRequest.Builder(WorkChainB::class.java).build()
            val workC = OneTimeWorkRequest.Builder(WorkChainC::class.java).build()
            val workD = OneTimeWorkRequest.Builder(WorkChainD::class.java).build()
            val workE = OneTimeWorkRequest.Builder(WorkChainE::class.java).build()

            /**
             * A->B
             *      ->E
             * C->D
             */
            //任务组合，先执行A、B和C、D,然后执行E

            val workContinuation1 = WorkManager.getInstance(this).beginWith(workA).then(workB)
            val workContinuation2 = WorkManager.getInstance(this).beginWith(workC).then(workD)

            val workContinuationList = listOf(workContinuation1, workContinuation2)
            WorkContinuation.combine(workContinuationList).then(workE).enqueue()

        }
    }
}