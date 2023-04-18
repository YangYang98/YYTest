package com.example.lib_skin.data


/**
 * Create by Yang Yang on 2023/4/13
 */
class SkinResourceCacheBean constructor(val resourceId: Int, val type: Type) {

    enum class Type {
        SKIN, SYSTEM
    }

    override fun toString(): String {
        return "resourceId: $resourceId  type: $type"
    }
}