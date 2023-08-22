package java.com.example.jetpackdemo.lifecycle

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.com.example.jetpackdemo.model.AdvertisingViewModel


/**
 * Create by Yang Yang on 2023/8/21
 */
class AdvertisingManage(advertisingViewModel: AdvertisingViewModel): DefaultLifecycleObserver {
    var TAG = "AdvertisingManage"
    //定时器
    private var countDownTimer: CountDownTimer? = object : CountDownTimer(advertisingViewModel.millsInFuture, 1000){
        override fun onTick(millisUntilFinished: Long) {
            Log.d(TAG, "广告剩余${(millisUntilFinished / 1000).toInt()}秒")
            advertisingViewModel.apply {
                millsInFuture = millisUntilFinished
                setTimingResult(millisUntilFinished / 1000)
            }
        }
        override fun onFinish() {
            Log.d(TAG, "广告结束，准备进入主页面")
        }
    }
    /**
     * 开始计时
     */
    fun start() {
        Log.d(TAG, "开始计时")
        countDownTimer?.start()
    }
    /**
     * 停止计时
     */
    fun onCancel() {
        Log.d(TAG, "停止计时")
        countDownTimer?.cancel()
        countDownTimer = null
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        start()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        onCancel()
    }
}