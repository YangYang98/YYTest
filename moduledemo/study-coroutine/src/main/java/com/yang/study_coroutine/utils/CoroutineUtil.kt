package com.yang.study_coroutine.utils

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine


/**
 * Create by Yang Yang on 2023/5/4
 */

/**
 * Kotlin没有提供直接声明带有Receiver的Lambda表达式的语法，
 * 为了方便使用带有Receiver的协程API，
 * 我们封装一个用以启动协程的函数launchCoroutine
 */
fun <R, T> launchCoroutine(receiver: R, block: suspend R.() -> T) {
    block.startCoroutine(receiver, object : Continuation<T> {

        override fun resumeWith(result: Result<T>) {
            println("Coroutine End: $result")
        }

        override val context: CoroutineContext = EmptyCoroutineContext

    })
}