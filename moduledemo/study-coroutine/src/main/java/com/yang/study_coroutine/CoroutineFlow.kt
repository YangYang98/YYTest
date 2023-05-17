package com.yang.study_coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


/**
 * Flow
 * Create by Yang Yang on 2023/5/16
 */

suspend fun main5_0() {
    //创建 Flow
    val intFlow = flow {
        (1..3).forEach {
            emit(it)
            delay(1000)
        }
    }

    //设定运行时所使用的调度器
    //intFlow.flowOn(Dispatchers.IO)

    val aaaq = listOf(1, 2, 3, 4).asFlow()

    //消费 Flow
    GlobalScope.launch(CoroutineName("MyThread")) {
        intFlow.collect { printlnThread(it) }
        intFlow.collect { printlnThread(it) }

        aaaq.collect()
    }.join()


}


/**
 * Flow 的元素变换 ---- map
 */
suspend fun main5_1() {
    //创建 Flow
    val intFlow = flow {
        List(5){
            emit(it)
        }
    }.map {
        it * 2
    }

    //消费 Flow
    GlobalScope.launch(CoroutineName("MyThread")) {
        intFlow.collect { printlnThread(it) }

    }.join()
}

/**
 * Flow 的嵌套
 * 实际上得到的是一个数据类型为 Flow 的 Flow
 */
suspend fun main5_2() {
    val intFlow = flow {
        List(5){
            emit(it)
        }
    }.map {
        flow { List(it) { emit(it) } }
    }

    GlobalScope.launch(CoroutineName("MyThread")) {
        intFlow.collect {
            //printlnThread(it)
            it.collect { a ->
                printlnThread("-------")
                printlnThread(a)
                //printlnThread("-------")
            }
        }

    }.join()
}

/**
 * 拼接 Flow ---按顺序拼接的flattenConcat()
 */
suspend fun main5_3() {
    val intFlow = flow {
        List(5){
            emit(it)
        }
    }.map {
        flow { List(it) { emit(it) } }
    }.flattenConcat()

    GlobalScope.launch(CoroutineName("MyThread")) {
        intFlow.collect {
            printlnThread(it)
        }
    }.join()
}

/**
 * 拼接 Flow ---不保证拼接顺序的flattenMerge()
 */
suspend fun main5_4() {
    val intFlow = flow {
        List(5){
            emit(it)
        }
    }.map {
        flow { List(it) { emit(it) } }
    }.flattenMerge()

    GlobalScope.launch(CoroutineName("MyThread")) {
        intFlow.collect {
            printlnThread(it)
        }
    }.join()
}

/**
 * 使用 Flow --- filter,map,take
 *
 * onStart,onCompletion
 */
suspend fun main5_5() {
    flowOf(1, 2, 3, 4)
        .filter { it > 1 }   //过滤
        .onStart { printlnThread("Flow onStart") }
        .onCompletion { printlnThread("Flow onCompletion $it") }
        .map { it * 2 }    //变换
        .take(2)   //截取
        .collect {
            printlnThread("Flow result:$it")
        }
}

/**
 * catch异常处理
 */

suspend fun main5_6_1() {
    val flow = flow {
        emit(1)
        throw IllegalStateException()    //<-------------------发送数据抛异常
        emit(2)
    }
    flow.catch {      //<----------------------这里catch
        printlnThread("Flow catch $it")
    }.onCompletion {
        printlnThread("Flow onCompletion $it")
    }.collect{        //<-------------结束操作符
        printlnThread("Flow collect $it")
    }
}


/**
 * catch异常处理
 * 发送时候的异常是可以捕获的，但是处理数据时的异常是没有办法捕获的。
 * ----------- catch 的作用域，仅限于 catch 的上游。 -------------
 */
suspend fun main5_6_2() {
    flowOf(1, 2)
        .catch { printlnThread("Flow catch $it") }
        .onCompletion {
            printlnThread("Flow onCompletion $it")
        }
        .collect {
            printlnThread("Flow collect $it")
            throw IllegalStateException()
        }
}

/**
 * flowOn 切换线程
 *
 * flowOn切换线程只在它的上游起作用
 */
suspend fun main5_7() = runBlocking{
    flow {
        emit(1)
        emit(2)
    }.filter {
        printlnThread("filter $it")
        it > 1
    }.flowOn(
        Dispatchers.IO
    ).map {
        printlnThread("map $it")
        it * 2
    }.collect {
        printlnThread("collect $it")
    }
}

/**
 * launchIn 切换线程
 *
 * launchIn将它上游的代码切换到给定上下文的线程执行
 */
suspend fun main5_8() = runBlocking<Unit>{
    flow {
        emit(1)
        emit(2)
    }.map {
        printlnThread("map $it")
        it > 1
    }.flowOn(
        Dispatchers.IO
    ).onEach {
        printlnThread("flow onEach $it")
    }.onCompletion {
        printlnThread("flow onCompletion $it")
    }.catch {
        printlnThread("flow catch $it")
    }.launchIn(this)
}

/**
 * retry异常时重试次数
 *
 * retry重试的是整个代码块，代码中的1其实是没有问题的也重试了。
 */
suspend fun main5_9() {
    flow<Int> {
        printlnThread("emit 1")
        emit(1)
        printlnThread("emit 2")
        emit(2)
    }.onEach {
        printlnThread("onEach $it")
        if (it == 2) {
            throw RuntimeException("Exception on $it")
        }
    }.retry(1)
        .catch { printlnThread("catch $it") }
        .collect { printlnThread("collect $it") }
}

/**
 * takeWhile - 按照给定的条件从流中获取元素，直到条件不成立
 */
suspend fun main5_10() {
    (1..10)
        .asFlow()
        .takeWhile { it < 5 }
        .collect {
            println(it) // 输出 1 2 3 4
        }
}


/**
 * zip组合不同flow的元素
 */
fun main5_11() = runBlocking {
    val nums = (1..4).asFlow()
    val strs = flowOf("one", "two", "three")
    nums.zip(strs) { a, b ->
        "$a -> $b"
    }.collect {
        printlnThread("collect $it")
    }
}

/**
 * combine组合不同flow的元素
 *
 * zip和combine操作符的区别在于它们组合Flow的方式。
 * zip操作符会按顺序一一对应地组合两个Flow中的项，
 * 而combine操作符则会将每个Flow中的最新项作为参数应用于给定的lambda函数。
 * 因此，zip操作符只有当两个Flow的项数相同时才会产生输出，而combine操作符则可以组合项数不同的Flow并能立即输出组合结果。
 */
fun main5_12() = runBlocking {
    val nums = (1..4).asFlow()
    val strs = flowOf("one", "two", "three")
    nums.combine(strs) { a, b ->
        "$a -> $b"
    }.collect {
        printlnThread("collect $it")
    }
}


/**
 * flatMapConcat和flatMapMerge操作符都是用于将一个Flow的项转换为其他Flow的操作符
 *
 * flatMapConcat操作符会顺序地组合每个转换后的Flow，即它会等待前一个转换后的Flow完成后再开始下一个转换。因此，它会按顺序发出所有转换后的Flow的项。常用于串行的数据传递和转换，比如串行的网络请求等。
 *
 * flatMapMerge操作符会并发地组合每个转换后的Flow，即它会立即开始下一个转换而不等待前一个转换完成。因此，它会立即发出所有转换后的Flow的项，并通过任意顺序发射它们。
 */
fun main5_13() = runBlocking {
    (1..2).asFlow().flatMapConcat {
        zipElement(it)
    }.collect {
        printlnThread("collect $it")
    }
}

fun zipElement(int: Int): Flow<String> {
    return flow {
        emit("$int -> Hello")
        delay(1000)
        emit("$int -> Kotlin")
    }
}

/**
 * sample 操作符会在一定的时间间隔内，发射最新的元素
 */
fun main5_14() = runBlocking {
    flowOf("A", "B", "C", "D").onEach {
        delay(100)
    }.sample(250)
        .collect {
        printlnThread("collect $it")
    }
}

/**
 * debounce 只有达到指定时间后才发出数据，最后一个数据一定会发出
 * 定义 1000 毫秒，也就是 1 秒，被观察者发出数据，1秒后，观察者收到数据，如果 1 秒内多次发出数据，则重置计算时间。
 */
fun main5_15() = runBlocking {
    flow {
        emit(1)
        delay(590)
        emit(2)
        delay(590)
        emit(3)
        delay(1010)
        emit(4)
        delay(1010)
    }.debounce(1000).collect {
        printlnThread("collect $it")
    }
}


/**
 * distinctUntilChanged  过滤重复的值
 */
fun main() = runBlocking {
    val flow = flowOf(1, 2, 2, 3, 3, 3, 4, 5, 5)
    flow.distinctUntilChanged().collect {
        printlnThread("collect $it")
    }
}

