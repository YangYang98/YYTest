package com.yang.study_coroutine.model

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


/**
 * Create by Yang Yang on 2023/5/17
 */
class SimpleFlowClient {

    private val mutableStateFlow = MutableStateFlow("offline")
    private val stateFlow: StateFlow<String> = mutableStateFlow

    companion object {
        private var instance: SimpleFlowClient? = null

        @Synchronized
        fun getInstance(): SimpleFlowClient {
            return instance ?: SimpleFlowClient().also { instance = it }
        }
    }

    fun setFlowValue(value: String) {
        mutableStateFlow.value = value
    }

    /**
     * 获取Flow的值,不关心页面的生命周期,
     * 使用LifecycleCoroutineScope是页面关闭时同时关闭协程
     * @param scope lifecycleScope
     */
    fun getFlowValue(
        scope: LifecycleCoroutineScope,
        result: (value: String) -> Unit = { _ -> }
    ) {
        scope.launch {
            stateFlow.collect {
                result(it)
            }
        }
    }

    /**
     * 获取Flow的值,转换成LiveData,关心页面的生命周期
     * @param owner lifecycleScope
     */
    fun getFlowValueAsLiveData(
        owner: LifecycleOwner,
        observer: (value: String) -> Unit = { _ -> }
    ) {
        stateFlow.asLiveData().observe(owner) {
            observer(it)
        }
    }
}