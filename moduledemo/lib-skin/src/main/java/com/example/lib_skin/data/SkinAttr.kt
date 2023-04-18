package com.example.lib_skin.data

import android.content.Context


/**
 * Create by Yang Yang on 2023/4/14
 */
class SkinAttr constructor(val context: Context, val key: String, var value: String) {

    var attrValue: String

    /**
     * 属性对应的值得name   eg: drawable color
     */
    lateinit var attrType :String

    private var isAddValue: Boolean = false

    init {
        attrValue = getCheckValue()
    }

    private fun getCheckValue(): String {
        if (value.startsWith("@")) {
            value = value.substring(1)
            try {
                val id = value.toInt()
                // 保存资源类型,用来区分background时候是drawable还是color等
                attrType = context.resources.getResourceTypeName(id)
                // 通过id转换成value
                val name = context.resources.getResourceEntryName(id)

                isAddValue = true
                return name
            } catch (e: Exception) {
                isAddValue = false
            }
        }
        return value
    }

    fun isAddValue(): Boolean {
        return isAddValue && key.isNotEmpty() && value.isNotEmpty()
    }
}