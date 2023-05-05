package com.yang.study_coroutine.ui

import android.app.ProgressDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import com.yang.study_coroutine.R
import com.yang.study_coroutine.api.ApiClient
import com.yang.study_coroutine.api.WanAndroidApi
import com.yang.study_coroutine.data.LoginRegisterResponse
import com.yang.study_coroutine.data.LoginRegisterResponseWrapper

class TraditionActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null

    private lateinit var content: TextView

    //第二大步骤:主线程更新UI(注意:本次所有代码 是Kotlin的简写,如果是Java代码量更多)
    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) {
        val result = it.obj as LoginRegisterResponseWrapper<LoginRegisterResponse>
        content.text = result.data.toString()
        progressDialog?.dismiss()
        false
    } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tradition)

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

        //开启异步线程
        //第一大步骤:异步线程请求服务器 & 把服务器响应结果转发给Handler
        object : Thread() {
            override fun run() {
                super.run()

                Thread.sleep(2000)

                val loginResult = ApiClient.instance
                    .instanceRetrofit(WanAndroidApi::class.java)
                    .login("Derry-vip", "123456")

                val result = loginResult.execute().body()

                val msg = handler.obtainMessage()
                msg.obj = result
                handler.sendMessage(msg)
            }
        }.start()
    }

    private fun startThreeRequest() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("请求中....")
        progressDialog?.show()

        requestLogin(object : ResponseCallback {
            override fun responseSuccess(serverResponseInfo: String) {
                val handler: Handler = object : Handler(Looper.getMainLooper()) {
                    override fun handleMessage(msg: Message) {
                        super.handleMessage(msg)

                        content.text = serverResponseInfo
                        content.setTextColor(Color.RED)
                    }
                }
                handler.sendEmptyMessage(0)

                requestLoadUserAssets(object : ResponseCallback {
                    override fun responseSuccess(serverResponseInfo: String) {

                        val handler: Handler = object : Handler(Looper.getMainLooper()) {
                            override fun handleMessage(msg: Message) {
                                super.handleMessage(msg)

                                content.text = serverResponseInfo
                                content.setTextColor(Color.GREEN)
                            }
                        }
                        handler.sendEmptyMessage(0)

                        requestLoadUserAssetsDetail(object : ResponseCallback {
                            override fun responseSuccess(serverResponseInfo: String) {
                                val handler: Handler = object : Handler(Looper.getMainLooper()) {
                                    override fun handleMessage(msg: Message) {
                                        super.handleMessage(msg)

                                        content.text = serverResponseInfo
                                        content.setTextColor(Color.BLUE)
                                        progressDialog?.dismiss()
                                    }
                                }
                                handler.sendEmptyMessage(0)
                            }

                            override fun responseFail(serverResponseInfo: String) {
                                progressDialog?.dismiss()
                            }
                        })
                    }

                    override fun responseFail(serverResponseInfo: String) {
                        progressDialog?.dismiss()
                    }
                })
            }

            override fun responseFail(serverResponseInfo: String) {
                progressDialog?.dismiss()
            }
        })
    }

    private fun requestLogin(responseCallback: ResponseCallback) {
        val isLoadSuccess = true

        object : Thread() {
            override fun run() {
                super.run()

                try {
                    sleep(3000)

                    if (isLoadSuccess) {
                        responseCallback.responseSuccess("加载[用户数据]成功")
                    } else {
                        responseCallback.responseSuccess("加载[用户数据]失败")
                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun requestLoadUserAssets(responseCallback: ResponseCallback) {
        val isLoadSuccess = true

        object : Thread() {
            override fun run() {
                super.run()

                try {
                    sleep(3000)

                    if (isLoadSuccess) {
                        responseCallback.responseSuccess("加载[用户资产数据]成功")
                    } else {
                        responseCallback.responseSuccess("加载[用户资产数据]失败")
                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun requestLoadUserAssetsDetail(responseCallback: ResponseCallback) {
        val isLoadSuccess = true

        object : Thread() {
            override fun run() {
                super.run()

                try {
                    sleep(3000)

                    if (isLoadSuccess) {
                        responseCallback.responseSuccess("加载[用户资产详情]成功")
                    } else {
                        responseCallback.responseSuccess("加载[用户资产详情]失败")
                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
}

interface ResponseCallback {

    fun responseSuccess(serverResponseInfo: String)

    fun responseFail(serverResponseInfo: String)
}