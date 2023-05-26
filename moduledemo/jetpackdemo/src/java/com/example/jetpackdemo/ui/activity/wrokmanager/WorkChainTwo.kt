package java.com.example.jetpackdemo.ui.activity.wrokmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


/**
 * Create by Yang Yang on 2023/5/26
 */
class WorkChainTwo(context: Context, params: WorkerParameters): Worker(context, params) {

    override fun doWork(): Result {
        Log.e("YANGYANG", "WorkChainTwo doWork")
        return Result.success()
    }
}