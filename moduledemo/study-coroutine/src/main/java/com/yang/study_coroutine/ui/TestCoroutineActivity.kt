package com.yang.study_coroutine.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.yang.study_coroutine.R
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Create by Yang Yang on 2023/8/30
 */
class TestCoroutineActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_coroutine)

        System.setProperty("kotlinx.coroutines.debug", "on")

        lifecycleScope.launch(CoroutineName("aaa")) {
            logMsg("lifecycleScope.launch start")
            val user = getUserInfo()  //获取用户信息
            val list = getFriendList(user)   //获取好友列表
            getChatRecord(list)  //获取聊天记录
            logMsg("lifecycleScope.launch end")
        }
    }

    private suspend fun getUserInfo(): String {
        val result = withContext(Dispatchers.IO) {
            logMsg("getUserInfo withContext")
            delay(2000L)
            return@withContext "getUserInfo"
        }
        logMsg("getUserInfo result:$result")
        return result
    }

    private suspend fun getFriendList(user: String): String {
        val result = withContext(Dispatchers.IO) {
            logMsg("getFriendList withContext")
            delay(2000L)
            return@withContext "getFriendList"
        }
        logMsg("getFriendList result:$result")
        return result
    }

    private suspend fun getChatRecord(list: String): String {
        val result = withContext(Dispatchers.IO) {
            logMsg("getChatRecord withContext")
            delay(2000L)
            return@withContext "getChatRecord"
        }
        logMsg("getChatRecord result:$result")
        return result
    }

    private fun logMsg(msg: String) {
        Log.d("TAG", "${Thread.currentThread().name}:$msg")
    }
}