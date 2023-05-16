package com.yang.study_coroutine.interfaces

import com.yang.study_coroutine.ui.BaseActivity2
import kotlinx.coroutines.CoroutineScope
import java.util.IdentityHashMap
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel


/**
 * Create by Yang Yang on 2023/5/15
 */
interface CoroutineScopeInterface {

    companion object {
        internal val scopeMap = IdentityHashMap<CoroutineScopeInterface, CoroutineScope>()
    }

    fun createScope() {
        val activity = this as BaseActivity2
        scopeMap[activity] ?: MainScope().also {
            scopeMap[activity] = it
        }
    }

    fun destroyScope(){
        scopeMap.remove(this as BaseActivity2)?.cancel()
    }

    val mainScope: CoroutineScope
        get() = scopeMap[this as BaseActivity2]!!
}
