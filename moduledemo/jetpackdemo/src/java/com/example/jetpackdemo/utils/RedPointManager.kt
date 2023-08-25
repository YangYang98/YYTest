package java.com.example.jetpackdemo.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData


/**
 * Create by Yang Yang on 2023/8/25
 */
class RedPointManager: IRedPointManager {

    companion object {
        val TAG = "RedPointManager"

        @JvmStatic
        val instance: IRedPointManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            RedPointManager()
        }
    }

    override val liveDataA = MediatorLiveData<Boolean>()
    override val liveDataB1 = MediatorLiveData<Boolean>()
    override val liveDataB2 = MediatorLiveData<Boolean>()
    override val liveDataC1 = MediatorLiveData<Boolean>()
    override val liveDataC2 = MediatorLiveData<Boolean>()

    init {

        /**
         * 构建树型关系。按路径层级，进行观察。一般外部只需要改动最低层的红点对应的LiveData，顶部的LiveData就会自动改变
         */
        liveDataA.apply {
            addSource(liveDataB1) {
                liveDataA.postValue(liveDataB1.value == true || liveDataB2.value == true)
            }
            addSource(liveDataB2) {
                liveDataA.postValue(liveDataB1.value == true || liveDataB2.value == true)
            }
        }
        liveDataB1.apply {
            addSource(liveDataC1) {
                liveDataB1.postValue(liveDataC1.value == true || liveDataC2.value == true)
            }
            addSource(liveDataC2) {
                liveDataB1.postValue(liveDataC1.value == true || liveDataC2.value == true)
            }
        }
    }

    override fun testChangeDataC1(show: Boolean) {
        liveDataC1.postValue(show)
    }
}

interface IRedPointManager {
    val liveDataA: LiveData<Boolean>
    val liveDataB1: LiveData<Boolean>
    val liveDataB2: LiveData<Boolean>
    val liveDataC1: LiveData<Boolean>
    val liveDataC2: LiveData<Boolean>

    fun testChangeDataC1(show: Boolean)
}