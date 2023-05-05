package com.yang.study_coroutine.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.yang.study_coroutine.R
import com.yang.study_coroutine.api.ApiClient
import com.yang.study_coroutine.api.WanAndroidApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
}