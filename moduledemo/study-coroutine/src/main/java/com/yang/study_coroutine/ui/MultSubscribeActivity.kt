package com.yang.study_coroutine.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.yang.study_coroutine.R
import com.yang.study_coroutine.model.SimpleFlowClient
import com.yang.study_coroutine.utils.log

class MultSubscribeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mult_subscribe)

        System.setProperty("kotlinx.coroutines.debug", "on")

        val button = findViewById<Button>(R.id.btn_mul_subscribe)
        button.setOnClickListener {
            Intent(this, MultSubscribeActivity2::class.java).apply {
                startActivity(this)
            }
            Handler(Looper.getMainLooper()).postDelayed({
                SimpleFlowClient.getInstance().setFlowValue("online")
            }, 2000)
        }

        SimpleFlowClient.getInstance().getFlowValue(lifecycleScope) {
            log("MultSubscribeActivity:flow:$it")
        }
        SimpleFlowClient.getInstance().getFlowValueAsLiveData(this) {
            log("MultSubscribeActivity:asLiveData:$it")
        }
    }
}