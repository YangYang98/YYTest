package java.com.example.jetpackdemo.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map


/**
 * Create by Yang Yang on 2023/8/28
 */
class LiveDataBus {

    private val bus: HashMap<String, MutableLiveData<Any>> = HashMap()

    companion object {

        private lateinit var DATA_BUS: LiveDataBus
        fun get(): LiveDataBus {
            DATA_BUS = if (::DATA_BUS.isInitialized) DATA_BUS else LiveDataBus()
            return DATA_BUS
        }
    }

    fun <T : Any> getChannel(target: String, type: Class<T>): MutableLiveData<T> {
        if (bus.containsKey(target)) {
            bus[target] = MutableLiveData()
        }
        return bus[target] as MutableLiveData<T>
    }

    fun getChannel(target: String): MutableLiveData<Any> {
        return getChannel(target, Any::class.java).apply {
            map {

            }
        }
    }
}