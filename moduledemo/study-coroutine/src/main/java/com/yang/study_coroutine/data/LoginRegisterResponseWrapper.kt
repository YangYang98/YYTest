package com.yang.study_coroutine.data


/**
 * Create by Yang Yang on 2023/5/4
 */

data class LoginRegisterResponse(val admin: Boolean,
                                 val chapterTops:List<*>,
                                 val collectIds:List<*>,
                                 val email:String?,
                                 val icon:String?,
                                 val id:String?,
                                 val nickname:String?,
                                 val password:String?,
                                 val publicName:String?,
                                 val token:String?,
                                 val type:Int,
                                 val username:String?
                                 )

data class LoginRegisterResponseWrapper<T>(val data: T, val errorCode: Int, val errorMsg: String)