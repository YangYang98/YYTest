package java.com.example.jetpackdemo.ui.activity.wrokmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters


/**
 * Create by Yang Yang on 2023/5/25
 */
class MyWork(context: Context, params: WorkerParameters): Worker(context, params) {

    override fun doWork(): Result {
        //接收传入的数据
        val inputData = inputData.getString("input_data")

        //Log.e("YANGYANG", "MyWork doWork sleep before")
        //SystemClock.sleep(2000)
        Log.e("YANGYANG", "MyWork doWork inputData: $inputData")

        //任务执行完，返回数据
        val outputData = Data.Builder()
            .putString("output_data", "输出数据")
            .build()

        return Result.success(outputData)
    }
}