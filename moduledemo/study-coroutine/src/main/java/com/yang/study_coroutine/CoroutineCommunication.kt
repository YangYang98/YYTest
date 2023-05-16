package com.yang.study_coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
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

suspend fun main23_1() {
    val channel = Channel<Int>()
    val producer = GlobalScope.launch {
        var i = 0
        while (true){
            i++ //为了方便输出日志，我们将自增放到前面
            printlnThread("before send $i")
            channel.send(i)
            printlnThread("before after $i")
            delay(1000)
        }
    }

    val consumer = GlobalScope.launch {
        while(true){
            delay(2000) //receive 之前延迟 2s
            val element = channel.receive()
            printlnThread(element)
        }
    }

    producer.join()
    consumer.join()
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
fun main25() = runBlocking {

    val sendChannel = actor<String> {
        println("actor result: ${receive()}")
    }

    println("执行了，receive会挂起等待返回")
    delay(1000)
    sendChannel.send("来自sendChannel的数据")


}

/**
 * 对于一个 Channel，如果我们调用了它的 close，它会立即停止接受新元素，也就是说这时候它的 isClosedForSend 会立即返回 true，
 * 而由于 Channel 缓冲区的存在，这时候可能还有一些元素没有被处理完，
 * 所以要等所有的元素都被读取之后 isClosedForReceive 才会返回 true。
 */
suspend fun main26() {
    val channel = Channel<Int>(3)

    val producer = GlobalScope.launch {
        List(5){
            channel.send(it)
            printlnThread("send $it")
        }
        channel.close()
        printlnThread("close channel. ClosedForSend = ${channel.isClosedForSend} ClosedForReceive = ${channel.isClosedForReceive}")
    }

    val consumer = GlobalScope.launch {
        for (element in channel) {
            printlnThread("receive: $element")
            delay(1000)
        }

        printlnThread("After Consuming. ClosedForSend = ${channel.isClosedForSend} ClosedForReceive = ${channel.isClosedForReceive}")
    }

    producer.join()
    consumer.join()
}

/**
 * BroadcastChannel
 */
@OptIn(ObsoleteCoroutinesApi::class, DelicateCoroutinesApi::class)
suspend fun main27() {

    val broadcastChannel = BroadcastChannel<Int>(5)

    val producer = GlobalScope.launch {
        List(5) {
            broadcastChannel.send(it)
            printlnThread("send $it")
        }
        broadcastChannel.close()
    }

    List(3) { index ->
        GlobalScope.launch {
            val receiveChannel = broadcastChannel.openSubscription()

            for (element in receiveChannel) {
                printlnThread("[$index] receive: $element")
                delay(1000)
            }
        }
    }.forEach {
        it.join()
    }

    producer.join()
}


/**
 * 我们传入了一个 Dispatchers.Unconfined 调度器，意味着协程会立即在当前协程执行到第一个挂起点，
 * 所以会立即输出 A 并在 send(1) 处挂起，直到后面的 for 循环读到第一个值时，实际上就是 channel 的 iterator 的 hasNext 方法的调用，
 * 这个 hasNext 方法会检查是否有下一个元素，是一个挂起函数，在检查的过程中就会让前面启动的协程从 send(1) 挂起的位置继续执行，
 * 因此会看到日志 B 输出，然后再挂起到 send(2) 这里，这时候 hasNext 结束挂起，for 循环终于输出第一个元素，依次类推。
 */
suspend fun main28() {
    val channel = GlobalScope.produce(Dispatchers.Unconfined) {
        printlnThread("A")
        send(1)
        printlnThread("B")
        send(2)
        printlnThread("Done")
    }

    for (item in channel) {
        printlnThread("Got $item")
    }

}

suspend fun main29() {
    val channel = GlobalScope.produce(Dispatchers.Unconfined) {
        printlnThread(1)
        send(1)
        withContext(Dispatchers.IO){
            printlnThread(2)
            send(2)
        }
        printlnThread("Done")
    }


    for (item in channel) {
        printlnThread("Got $item")
    }

}