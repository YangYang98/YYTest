package java.com.example.jetpackdemo.ui.activity.wrokmanager

import android.content.Context
import android.os.SystemClock
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


/**
 * Create by Yang Yang on 2023/5/25
 */
class MyWork(context: Context, params: WorkerParameters): Worker(context, params) {

    override fun doWork(): Result {
        SystemClock.sleep(2000)
        Log.e("YANGYANG", "MyWork doWork")
        return Result.success()
    }
}