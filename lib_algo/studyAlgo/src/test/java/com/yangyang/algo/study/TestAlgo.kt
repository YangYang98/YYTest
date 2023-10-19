package com.yangyang.algo.study


/**
 * Create by Yang Yang on 2023/9/12
 */
class TestAlgo {

}
fun main() {
    //println(fib(8))
    //insertArray(intArrayOf(1, 2, 3, 4, 5), 6, 3)
    //removeArray(intArrayOf(1, 2, 3, 4, 5), 3)
    //println(findArray(intArrayOf(1, 2, 3, 4, 5), 3))
    //extendArray(intArrayOf(1, 2, 3, 4, 5), 6)
}

fun fib(n: Int): Int {
    if (n == 1 || n == 2) {
        return n - 1
    }
    return fib(n - 1) + fib(n - 2)
}

