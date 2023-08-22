package java.com.example.jetpackdemo.lifecycle

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner


/**
 * Create by Yang Yang on 2023/8/21
 */
class AdvertisingManage(millsInFuture: Long): DefaultLifecycleObserver {
    var TAG = "AdvertisingManage"
    //监听事件
    var advertisingManageListener: AdvertisingManageListener? = null
    //定时器
    private var countDownTimer: CountDownTimer? = object : CountDownTimer(millsInFuture, 1000){
        override fun onTick(millisUntilFinished: Long) {
            Log.d(TAG, "广告剩余${(millisUntilFinished / 1000).toInt()}秒")
            advertisingManageListener?.timing((millisUntilFinished / 1000).toInt())
        }
        override fun onFinish() {
            Log.d(TAG, "广告结束，准备进入主页面")
            advertisingManageListener?.enterMainActivity()
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
    /**
     *广告管理接口
     */
    interface AdvertisingManageListener {
        /**
         * 计时
         * @param second秒
         */
        fun timing(second: Int)
        /**
         * 计时结束, 进入主页面
         */
        fun enterMainActivity()
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