package com.yang.study_coroutine

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*


/**
 * 协程调度器
 *
 * Create by Yang Yang on 2023/5/11
 */
suspend fun main40() {
    val customDispatcher = Executors.newSingleThreadExecutor{ r -> Thread(r, "")}.asCoroutineDispatcher()
    GlobalScope.launch(customDispatcher) {
        println("111")
    }.join()
    println("222")
    customDispatcher.close()
}

suspend fun main41() {
    GlobalScope.launch(MyContinuationInterceptor()) {
        printlnThread("111")
        val job = async(MyContinuationInterceptor2()) {
            printlnThread("222")
            delay(1000)
            printlnThread("333")
            "Hello"
        }
        printlnThread("444")
        val result = job.await()
        printlnThread("555 $result")
    }.join()
    printlnThread("666")
}

class MyContinuationInterceptor: ContinuationInterceptor{
    override val key = ContinuationInterceptor
    override fun <T> interceptContinuation(continuation: Continuation<T>) = MyContinuation(continuation)
}

class MyContinuation<T>(val continuation: Continuation<T>): Continuation<T> {
    override val context = continuation.context
    override fun resumeWith(result: Result<T>) {
        println("<MyContinuation> $result" )
        continuation.resumeWith(result)
    }
}

class MyContinuationInterceptor2: ContinuationInterceptor{
    override val key = ContinuationInterceptor
    override fun <T> interceptContinuation(continuation: Continuation<T>) = MyContinuation2(continuation)
}

class MyContinuation2<T>(val continuation: Continuation<T>): Continuation<T> {
    override val context = continuation.context
    override fun resumeWith(result: Result<T>) {
        println("<MyContinuation2> $result" )
        continuation.resumeWith(result)
    }
}

suspend fun main42() {
    printlnThread(1)
    try {
        supervisorScope { //①
            printlnThread(2)
            launch { // ②
                printlnThread(3)
                val jj = async { // ③
                    printlnThread(4)
                    delay(100)
                    throw ArithmeticException("Hey!!")
                }
                jj.join()
                printlnThread(5)
            }
            printlnThread(6)
            val job = launch { // ④
                printlnThread(7)
                delay(1000)
            }
            try {
                printlnThread(8)
                job.join()
                printlnThread("9")
            } catch (e: Exception) {
                printlnThread("10. $e")
            }
        }
        printlnThread(11)
    } catch (e: Exception) {
        printlnThread("12. $e")
    }
    printlnThread(13)
}

suspend fun hello() = suspendCoroutineUninterceptedOrReturn<Int>{
        continuation ->
    printlnThread(1)
    thread {
        Thread.sleep(1000)
        printlnThread(2)
        continuation.resume(1024)
    }
    printlnThread(3)
    COROUTINE_SUSPENDED
}

suspend fun main43() {
    val fi = sequence<Long> {
        yield(1L)
        var cur = 1L
        var next = 1L
        while (true) {
            yield(next)
            val temp = cur + next
            cur = next
            next = temp
        }
    }
    fi.iterator().next()

    fi.take(5).forEach { printlnThread(it) }

}

suspend fun returnSuspended() = suspendCoroutineUninterceptedOrReturn<String>{
        continuation ->
    thread {
        Thread.sleep(1000)
        continuation.resume("Return suspended.")
    }
    COROUTINE_SUSPENDED
}

suspend fun returnImmediately() = suspendCoroutineUninterceptedOrReturn<String>{
    printlnThread(1)
    "Return immediately."
}

suspend fun main() {
    printlnThread(1)
    printlnThread(returnSuspended())
    printlnThread(2)
    delay(1000)
    printlnThread(3)
    printlnThread(returnImmediately())
    printlnThread(4)
}





