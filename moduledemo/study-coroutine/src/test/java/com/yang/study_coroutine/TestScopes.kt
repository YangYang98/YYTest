package com.yang.study_coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.flow
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

fun main() {
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