package com.yangyang.algo.study


/**
 * Create by Yang Yang on 2023/9/13
 */
fun insertArray(nums: IntArray, num: Int, index: Int) {
    for (i in nums.size - 1 downTo index + 1) {
        nums[i] = nums[i - 1]
    }
    nums[index] = num
    nums.forEach {
        println(it)
    }
}

fun removeArray(nums: IntArray, index: Int) {
    for (i in index until nums.size - 1) {
        nums[i] = nums[i + 1]
    }
    nums.forEach {
        println(it)
    }
}

fun findArray(nums: IntArray, target: Int): Int {
    for (i in nums.indices) {
        if (nums[i] == target) {
            return i
        }
    }
    return -1
}

fun extendArray(nums: IntArray, enlarge: Int): IntArray {
    val newArray = IntArray(nums.size + enlarge)

    for (i in nums.indices) {
        newArray[i] = nums[i]
    }
    newArray.print()
    return newArray
}