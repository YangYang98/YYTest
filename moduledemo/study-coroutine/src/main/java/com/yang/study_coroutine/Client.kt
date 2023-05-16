package com.yang.study_coroutine

import android.annotation.SuppressLint
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.system.measureTimeMillis


/**
 * Create by Yang Yang on 2023/5/6
 */

fun main1() = runBlocking {
    printlnThread("main start ==== $coroutineContext ===")

    val job: Job = launch {
        printlnThread("launch 开始 ==== $coroutineContext ===")
        Thread.sleep(2000)
    }

    val sss = async {
        printlnThread("async 开始 ==== $coroutineContext ===")
        "async返回结果"
    }
    println("result: ${sss.await()}")
    println("result: ${sss.await()}")
    println("result: ${sss.await()}")
    println("result: ${sss.await()}")

    printlnThread("main end ==== $coroutineContext ===")
}

@SuppressLint("SimpleDateFormat")
fun printlnThread(any: Any) {
    val date = SimpleDateFormat("yyyy-MM:dd-HH:mm:ss").format(Date(System.currentTimeMillis()))
    println("$date thread:${Thread.currentThread().name} :\t $any")
}

fun <T> printlnCC(key: Any, cc: CancellableContinuation<T>) {
    println(
        "$key\tCancellableContinuation状态:${if (cc.isActive) "活跃" else "未活跃"}\t" +
                "${if (cc.isCancelled) "取消" else "未取消" }\t" +
                if (cc.isCompleted) "完成" else "未完成"
    )
}

fun printJob(job: Job) {
    println(
        "isActive:${if (job.isActive) "活跃" else "非活跃"}" +
                "\tisComplete:${if (job.isCompleted) "完成" else "未完成"}" +
                "\tisCancelled:${if (job.isCancelled) "协程取消" else "协程未取消"}"
    )
}

fun main3() = runBlocking<Unit> {
    printlnThread("main")

    launch(Dispatchers.IO) {
        printlnThread("launch2")
        launch {
            withContext(Dispatchers.Main) {
                printlnThread("launch4")
            }
        }
    }
}


/**
 * SupervisorJob()和Job()
 *  Job 某个协程出现问题,会直接影响兄弟协程,兄弟协程不会执行
 *  SupervisorJob 某个协程出现问题,不会影响兄弟协程.
 */
suspend fun main4() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        printlnThread("catch 到了 $throwable")
    }

    val customScope = CoroutineScope(Job() + CoroutineName("自定义协程") + Dispatchers.IO + exceptionHandler)

    customScope.launch {
        printlnThread("准备出错1")
        throw KotlinNullPointerException("============出错啦1")
    }.join()

    customScope.launch {
        printlnThread("准备出错2")
        throw KotlinNullPointerException("============出错啦2")
    }.join()
}


/**
 * coroutineScope{}
 */
suspend fun main5() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        printlnThread("catch 到了 $throwable")
    }

    val customScope = CoroutineScope(SupervisorJob() + CoroutineName("自定义协程") + Dispatchers.IO + exceptionHandler)

    customScope.launch {
        childLaunch()
    }.join()
}

private suspend fun childLaunch() = coroutineScope {
    launch {
        printlnThread("准备出错1")
        delay(3000)
        throw KotlinNullPointerException("============出错啦1")
    }

    launch {
        printlnThread("准备出错2")
        delay(2000)
        throw KotlinNullPointerException("============出错啦2")
    }
}

/**
 * supervisorScope{}
 */
suspend fun main6() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        printlnThread("catch 到了 $throwable")
    }

    val customScope = CoroutineScope(SupervisorJob() + CoroutineName("自定义协程") + Dispatchers.IO + exceptionHandler)

    customScope.launch {
        childLaunch2()
    }.join()
}

private suspend fun childLaunch2() = supervisorScope {
    launch {
        printlnThread("准备出错1")
        delay(1000)
        throw KotlinNullPointerException("============出错啦1")
    }

    launch {
        printlnThread("准备出错2")
        delay(2000)
        throw KotlinNullPointerException("============出错啦2")
    }
}

/**
 * async捕获异常
 */
suspend fun main7() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        printlnThread("catch 到了 $throwable")
    }

    val customScope = CoroutineScope(Job() + CoroutineName("自定义协程") + Dispatchers.IO + exceptionHandler)

    val deferred = customScope.async {
        printlnThread("准备出错1")
        throw KotlinNullPointerException("============出错啦1")
    }

    try {
        deferred.await()
    } catch (e: Exception) {
        printlnThread("catch 到了 $e")
    }

    /*customScope.launch {
        printlnThread("准备出错2")
        throw KotlinNullPointerException("============出错啦2")
    }.join()*/
}

/**
 * async 配合 SupervisorJob()
 */
suspend fun main8() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        printlnThread("catch 到了 $throwable")
    }

    val customScope = CoroutineScope(SupervisorJob() + CoroutineName("自定义协程") + Dispatchers.IO + exceptionHandler)

    val deferred = customScope.async {
        printlnThread("准备出错1")
        delay(1000)
        throw KotlinNullPointerException("============出错啦1")
    }

    val deferred2 = customScope.async {
        delay(1000)
        printlnThread("准备出错2")
        throw KotlinNullPointerException("============出错啦2")
    }

    try {
        deferred.await()
    } catch (e: Exception) {
        printlnThread("catch 到了 $e")
    }

    try {
        deferred2.await()
    } catch (e: Exception) {
        printlnThread("catch 到了 $e")
    }
}

fun main9() {

    val useTime = measureTimeMillis {
        runBlocking {
            printlnThread("main start")

            launch {
                printlnThread("launch 1")
            }
            //println("sleep1 start")
            //Thread.sleep(1000)
            delay(1000)
            //println("sleep1 end")

            launch {
                printlnThread("launch 2")
            }

            //println("sleep2 start")
            //Thread.sleep(2000)
            delay(2000)
            //println("sleep2 end")

            printlnThread("main end")
        }
    }

    printlnThread("执行时间：$useTime")
}

/**
 * 协程执行流程理解
 * 1425367
 */
fun main10() = runBlocking<Unit> {

    println("main start") // 1

    val job = launch {     // 协程1
        println("launch 1 start") // 2
        delay(1000L)  // TODO 延时1
        println("launch 1 end")  // 3
    }

    println("main mid")  // 4

    val job2 = launch {   // 协程2
        println("launch 2 start") //5
        delay(1000L) // TODO 延时2
        println("launch 2 end") //6
    }

    delay(1500) // TODO 延时3
    println("main end") // 7
}

/**
 * 协程执行流程理解
 * 14237
 *
 * 将一个没有生命的job给到一个新的协程中,运行起来可想而知,这个协程也是没有生命的, 所以协程2中的代码不会执行
 */
fun main11() = runBlocking<Unit> {

    println("main start") // 1

    val job = async {     // 协程1
        println("launch 1 start") // 2
        delay(1000L)
        println("launch 1 end")  // 3
    }

    println("main mid")  // 4

    job.join()

    launch(job) {   // 协程2
        println("launch 2 start") //5
        delay(1000L)
        println("launch 2 end") //6
    }

    println("main end") // 7
}

/**
 * 协程执行流程理解
 *
 * 1423567
 */
fun main12() = runBlocking<Unit> {

    println("main start") // 1

    val job = launch {     // 协程1
        println("launch 1 start") // 2
        delay(1000L)
        println("launch 1 end")  // 3
    }

    println("main mid")  // 4

    Thread.sleep(1000)
    job.join()

    launch(coroutineContext + Dispatchers.IO) {   // 协程2
        println("launch 2 start") //5
        delay(1000L)
        println("launch 2 end") //6
    }

    Thread.sleep(1000)
    println("main end") // 7
}

/**
 * 协程执行流程理解
 *
 * 1 7 2 10
 *
 * 假如案例七sleep3000ms为什么[5]也不会执行呢?
 * 答：job不是异步的，所以要等协程之外的代码执行完再执行协程2，3，
 *     但是协程之外的代码执行时就已经被cancel了，所以协程2，3也没执行
 */
fun main13() = runBlocking<Unit> {

    println("main start") // 1

    val job = launch(Dispatchers.IO) {     // 协程1
        println("launch 1 start") // 2
        delay(1000L)
        println("launch 1 end")  // 3
    }

    launch(job) {   // 协程2
        println("launch 2 start") //5
        delay(1000L)
        println("launch 2 end") //6
    }

    println("main mid")  // 7

    Thread.sleep(500)
    job.cancel()

    launch(job) {
        println("launch 3 start") //8
        delay(1000L)
        println("launch 3 end") //9
    }

    println("main end") // 10
}

/**
 * 协程执行流程理解
 *
 * 1 4 2 7 5
 */
fun main14(){

    val useTime = measureTimeMillis {
        runBlocking {
            println("main start") // 1

            val job = GlobalScope.launch {     // 全局协程
                println("launch 1 start") // 2
                delay(1000L)
                println("launch 1 end")  // 3
            }

            println("main mid")  // 4

            launch(context = job) {   // 协程2
                println("launch 2 start") //5
                delay(2000L)
                println("launch 2 end") //6
            }

            println("main end") // 7
        }
    }

    println("使用时间：$useTime")
}