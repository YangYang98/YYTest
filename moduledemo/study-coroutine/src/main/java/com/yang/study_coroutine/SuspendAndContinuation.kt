package com.yang.study_coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


/**
 * Create by Yang Yang on 2023/5/8
 */


/**
 * 网络请求的原始写法
 */
private suspend fun requestLoginNetworkData(account: String, pwd: String) = withContext(Dispatchers.IO) {
    delay(2000)
    if (account == "yangyang" && pwd == "123456") {
        Result.success("登录成功")
    } else {
        Result.failure(Throwable("登录失败"))
    }
}

fun main30(): Unit = runBlocking {
    val deferred = async {
        requestLoginNetworkData("yangyang", "123456")
    }

    val result = deferred.await()

    result.onSuccess {
        println("登陆成功:${result.getOrNull()}")
    }.onFailure {
        println("登陆失败:${result.getOrNull()}")
    }
}

/**
 * 使用suspendCoroutine改造网络请求
 */
private suspend fun <T> requestLoginNetworkData2(account: String, pwd: String): String {
    return withContext(Dispatchers.IO) {
        delay(2000)

        return@withContext suspendCoroutine {
            if (account == "yangyang" && pwd == "123456") {
                //it.resume("登录成功")
            } else {
                //it.resumeWithException(RuntimeException("登录失败"))
            }
        }
    }
}

fun main31() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO)

    val deferred = scope.async {
        requestLoginNetworkData2<String>("yangyang1", "123456")
    }

    val result = runCatching {
        deferred.await()
    }

    if (result.isSuccess) {
        printlnThread("登陆成功:${result.getOrNull()}")
    } else {
        printlnThread("登陆失败:${result.exceptionOrNull()}")
    }
}

/**
 * 使用suspendCancellableCoroutine改造网络请求
 */
private suspend fun <T> requestLoginNetworkData3(account: String, pwd: String): String {
    delay(2000)

    return suspendCancellableCoroutine {
        if (account == "yangyang" && pwd == "123456") {
            it.resume("登录成功")
        } else {
            it.resumeWithException(RuntimeException("登录失败"))
        }

        it.invokeOnCancellation {
            println("suspendCancellableCoroutine关闭")
        }
    }
}

fun main32(): Unit = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO)

    val deferred = scope.async {
        requestLoginNetworkData3<String>("yangyang1", "123456")
    }

    val result = runCatching {
        deferred.await()
    }

    result.onSuccess {
        printlnThread("登陆成功:${it}")
    }.onFailure {
        printlnThread("登陆失败:${it.message}")
    }
}

/**
 * 父协程取消子协程也取消
 *
 * 当一个挂起函数中的suspendCancellableCoroutine函数被恢复（例如，通过调用continuation.resume或continuation.resumeWithException）后，
 *  该协程就不再挂起，并且不能再被取消。因此，在恢复之后，该协程将无法响应invokeOnCancellation函数。
 */
private suspend fun testCancelFunction() = suspendCancellableCoroutine<String> { //协程2
    val scope = CoroutineScope(EmptyCoroutineContext)
    val job = scope.launch {
        delay(1000)
        printlnCC("状态1", it)
        it.resume("完成")
        printlnCC("状态2", it)
    }

    it.invokeOnCancellation {
        println("Cancellation")
        job.cancel()
    }
}

fun main() = runBlocking {
    val job = launch { //协程1
        val result = testCancelFunction()
        println(result)
    }
    delay(500)
    job.cancel() //cancel协程1后，协程2也会被cancel
}

