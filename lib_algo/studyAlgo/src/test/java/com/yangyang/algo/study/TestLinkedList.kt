package com.yangyang.algo.study


/**
 * Create by Yang Yang on 2023/9/14
 */
data class ListNode(var value: Int) {
    var next: ListNode? = null
}

fun main() {
    var temp: ListNode? = initLinkedList()
    //insertLinkedList(temp!!, ListNode(6))
    deleteLinkedList(temp)

    while (temp != null) {
        println(temp.value)
        temp = temp.next
    }
}

fun initLinkedList(): ListNode {
    val n0 = ListNode(1)
    val n1 = ListNode(3)
    val n2 = ListNode(2)
    val n3 = ListNode(5)
    val n4 = ListNode(4)

    n0.next = n1
    n1.next = n2
    n2.next = n3
    n3.next = n4

    return n0
}

fun insertLinkedList(target: ListNode, p: ListNode) {
    var targetNext = target.next
    p.next = target.next
    target.next = p
}

fun deleteLinkedList(target: ListNode?) {
    target ?: return
    target.next = target.next?.next
}