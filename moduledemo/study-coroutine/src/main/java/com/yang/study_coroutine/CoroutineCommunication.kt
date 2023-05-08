package com.yang.study_coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select


/**
 * 协程通信
 * Channel、
 *
 * Create by Yang Yang on 2023/5/6
 */

/**
 * 1.1 Channel
 */
fun main20() = runBlocking<Unit>{

    //方式一：capacity 通道容量
    val channel = Channel<String>(5)
    println("main start")

    launch {
        delay(2000)
        channel.send("来自launch1的数据1")
        channel.send("来自launch1的数据2")
        channel.send("来自launch1的数据3")
    }

    launch {
        println("result ${channel.receive()}")
    }

    println("main end")
}

fun main21() = runBlocking {
    //方式二：onBufferOverflow
    val channel = Channel<String>(capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    println("main start")

    launch {
        channel.send("来自launch1的数据1")
        channel.send("来自launch1的数据2")
        channel.send("来自launch1的数据3")
    }

    launch {
        println("result ${channel.receive()}")
        println("result ${channel.receive()}")
        println("result ${channel.receive()}")
    }

    println("main end")
}

fun main22() = runBlocking {
    val channel = Channel<String>(capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    println("main start")

    launch {
        val trySend = channel.trySend("来自launch1的数据1")
        if (trySend.isSuccess) {
            println("channel 发送成功")
        } else if (trySend.isClosed) {
            println("channel 关闭")
        } else if (trySend.isFailure) {
            println("channel 发送失败")
        }
    }.join()

    launch {
        val tryReceive = channel.tryReceive()
        if (tryReceive.isSuccess) {
            println("tryReceive 发送成功")
        } else if (tryReceive.isClosed) {
            println("tryReceive 关闭")
        } else if (tryReceive.isFailure) {
            println("tryReceive 发送失败")
        }
    }

    channel.invokeOnClose {
        println("channel close")
    }

    println("main end")
}

fun main23() = runBlocking {
    val channel = Channel<String>(capacity = 5, onBufferOverflow = BufferOverflow.SUSPEND)
    println("main start")

    launch {
        channel.send("来自send的数据")
        channel.trySend("来自trySend的数据")
    }

    //  select 接收数据 默认会挂起，等待数据返回
    select {
        channel.onReceive {
            println("onReceive数据：$it")
        }
    }

    println("result ${channel.receive()}")

    channel.invokeOnClose {
        println("channel close")
    }
    println("main end")
}

/**
 * 2.1 produce
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun main24() = runBlocking {

    val receiveChannel = produce<String> {
        launch {
            delay(2000)
            send("来自send的数据")
            close()
        }
    }

    println("执行了，receive会挂起等待返回")
    println("result: ${receiveChannel.receive()}")


}

/**
 * 2.2 actor
 */
@OptIn(ObsoleteCoroutinesApi::class)
fun main() = runBlocking {

    val sendChannel = actor<String> {
        println("actor result: ${receive()}")
    }

    println("执行了，receive会挂起等待返回")
    delay(1000)
    sendChannel.send("来自sendChannel的数据")


}