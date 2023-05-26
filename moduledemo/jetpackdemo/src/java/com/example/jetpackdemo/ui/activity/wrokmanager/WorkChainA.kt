package java.com.example.jetpackdemo.ui.activity.wrokmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


/**
 * Create by Yang Yang on 2023/5/26
 */
class WorkChainA(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        Log.e("YANGYANG", "WorkChainA doWork")
        return Result.success()
    }
}

class WorkChainB(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        Log.e("YANGYANG", "WorkChainB doWork")
        return Result.success()
    }
}

class WorkChainC(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        Log.e("YANGYANG", "WorkChainC doWork")
        return Result.success()
    }
}

class WorkChainD(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        Log.e("YANGYANG", "WorkChainD doWork")
        return Result.success()
    }
}

class WorkChainE(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        Log.e("YANGYANG", "WorkChainE doWork")
        return Result.success()
    }
}