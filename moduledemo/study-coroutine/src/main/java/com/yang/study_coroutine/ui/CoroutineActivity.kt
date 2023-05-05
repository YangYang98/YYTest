package com.yang.study_coroutine.ui

import android.app.ProgressDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.yang.study_coroutine.R
import com.yang.study_coroutine.api.ApiClient
import com.yang.study_coroutine.api.WanAndroidApi
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null

    private lateinit var content: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)


        content = findViewById(R.id.tv_content)

        findViewById<Button>(R.id.btn_request).setOnClickListener {
            startRequest()
        }

        findViewById<Button>(R.id.btn_three_login).setOnClickListener {
            startThreeRequest()
        }
    }

    private fun startRequest() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("请求中....")
        progressDialog?.show()

        GlobalScope.launch(Dispatchers.Main) { //在Android中默认是异步线程, 也就是Dispatchers.IO

            //自动回被挂起 -- 内部会切换异步线程 请求服务器 ---->   自动切出去执行异步线程
            val loginResult = ApiClient.instance
                .instanceRetrofit(WanAndroidApi::class.java)
                .loginCoroutine("Derry-vip", "123456")
            //执行完成后,自动切换回安卓主线程,所以下面就可以安心的更新UI了

            content.text = loginResult.data.toString()
            progressDialog?.dismiss()
        }
    }

    private fun startThreeRequest() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("请求中....")
        progressDialog?.show()


        GlobalScope.launch(Dispatchers.Main) {
            var serverResponseInfo = requestLoadUser()
            content.text = serverResponseInfo
            content.setTextColor(Color.RED)
            serverResponseInfo = requestLoadUserAssets()
            content.text = serverResponseInfo
            content.setTextColor(Color.GREEN)
            serverResponseInfo = requestLoadUserAssetsDetail()
            content.text = serverResponseInfo
            content.setTextColor(Color.BLUE)
            progressDialog?.dismiss()
        }

    }

    private suspend fun requestLoadUser(): String {
        val isLoadSuccess = true

        withContext(Dispatchers.IO) {
            delay(6000)
        }

        return if (isLoadSuccess) {
            "加载[用户数据]成功"
        } else {
            "加载[用户数据]失败"
        }
    }

    private suspend fun requestLoadUserAssets(): String {
        val isLoadSuccess = true

        withContext(Dispatchers.IO) {
            delay(3000)
        }

        return if (isLoadSuccess) {
            "加载[用户资产数据]成功"
        } else {
            "加载[用户资产数据]失败"
        }
    }

    private suspend fun requestLoadUserAssetsDetail(): String {
        val isLoadSuccess = true

        withContext(Dispatchers.IO) {
            delay(1000)
        }

        return if (isLoadSuccess) {
            "加载[用户资产详情]成功"
        } else {
            "加载[用户资产详情]失败"
        }
    }
}