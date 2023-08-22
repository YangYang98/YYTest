package java.com.example.jetpackdemo.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * Create by Yang Yang on 2023/8/22
 */
class AdvertisingViewModel: ViewModel() {

    var millsInFuture: Long = 5000

    private var timingResult = MutableLiveData<Long>()
    val _timingResult
        get() = timingResult

    fun setTimingResult(millsInFuture: Long) {
        timingResult.value = millsInFuture
    }
}