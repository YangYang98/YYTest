package com.example.lib_skin


/**
 * Create by Yang Yang on 2023/4/13
 */
class SkinConfig {

    companion object {

        const val SP_NAME = "sp_skin"
        const val SP_SKIN_STATE_NAME = "sp_skin_state"
        const val SP_SKIN_PATH = "sp_skin_path"

        const val SKIN_FAIL = -1
        const val SKIN_FAIL_FLOAT = -1f

        // SkinManager没有初始化
        const val SKIN_ERROR_1 = 10001

        const val SKIN_ERROR_2 = 10002
        //SkinSharedPreferences没有初始化
        const val SKIN_ERROR_3 = 10003
        // SkinManager没有初始化
        const val SKIN_ERROR_4 = 10004
        //SkinResource没有初始化
        const val SKIN_ERROR_5 = 10005
        // 皮肤包和本地资源都没有当前属性
        const val SKIN_ERROR_6 = 10006
        // 反射失败 + e.getMessage()
        const val SKIN_ERROR_7 = 10007
        // 获取系统资源失败，请检查资源文件下是否存在 value packName;
        const val SKIN_ERROR_8 = 10008
        // 换肤失败
        const val SKIN_ERROR_9 = 10009
    }
}