package com.yang.study_coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * Create by Yang Yang on 2023/8/30
 */
fun main1() {

    GlobalScope.launch {
        val job = launch {
            printMsg("job start")
            printMsg("job start")
            printMsg("job start")
            printMsg("job start")
            printMsg("job start")
            printMsg("job start")
            printMsg("job start")
            printMsg("job start")
            printMsg("job start")
        }
        printMsg("GlobalScope mid")
        printMsg("GlobalScope mid")
        printMsg("GlobalScope mid")
        printMsg("GlobalScope mid")
        printMsg("GlobalScope mid")
        printMsg("GlobalScope mid")
        printMsg("GlobalScope mid")
        printMsg("GlobalScope mid")
    }
    for (i in 0..1000) {
        printMsg("main")
    }

    Thread.sleep(10000)
}

fun printMsg(msg: String) {
    println("${Thread.currentThread().name}: $msg")
    //Log.e("TAG", "${Thread.currentThread().name}: $msg")
}

fun main2() {
    runBlocking {
        println("out start")
        val job = launch {
            println("job start")
            delay(1000)
            println("job end")
        }
        job.ensureActive()
        println("out mid")
        job.invokeOnCompletion {
            println("job completion")
        }
        println("out end")
    }
    GlobalScope.launch {

    }
    CoroutineScope(Dispatchers.Default).launch {
        println("CoroutineScope()")
    }

    Thread.sleep(100)
}

fun main3() {
    runBlocking {
        val start = System.currentTimeMillis()
        val flow1 = flow {
            delay(3000)
            emit("a")
        }
        val flow2 = flow {
            delay(2000)
            emit(1)
        }
        flow1.zip(flow2) { a, b ->
            a + b
        }.collect {
            val end = System.currentTimeMillis()
            println("a + b : $it")
            println("Time cost: ${end - start}ms")
        }
    }
}

fun main4() {
    //TODO retry(2)函数只有程序在错误或异常的时候尝试重新执行创建者，没有异常还是只执行一次
    val flow = flow {
        for (i in 1..5) {
            delay(100)
            emit(i)
            //if (i == 3) throw RuntimeException("自定义错误")
        }
    }.retry(2).onStart {
        println("onStart")
    }.onCompletion { exception ->
        println("onCompletion: $exception")
    }.catch { exception ->
        println("catch: $exception")
    }

    runBlocking {
        flow.collect { v ->
            println(v.toString())
        }
    }
}

fun main() {
    runBlocking {
        val channel = Channel<Int>(3, BufferOverflow.SUSPEND)

        launch {
            for (x in 1..10) {
                println("Sending $x")
                channel.send(x)
                //println("channel Size: ${channel.queue}")
            }
            // 关闭Channel
            channel.close()
        }

        launch {
            // 从Channel接收数据并打印
            for (y in channel) {
                delay(1000)
                println("Receiving $y")
            }
            // 当Channel关闭时退出循环
            println("Done!")
        }
    }
}