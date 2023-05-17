package com.yang.study_coroutine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.yang.study_coroutine.R
import com.yang.study_coroutine.model.SimpleFlowClient
import com.yang.study_coroutine.utils.log

class MultSubscribeActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mult_subscribe2)

        SimpleFlowClient.getInstance().getFlowValue(lifecycleScope) {
            log("MultSubscribeActivity2:flow:$it")
        }
        SimpleFlowClient.getInstance().getFlowValueAsLiveData(this) {
            log("MultSubscribeActivity:asLiveData2:$it")
        }
    }
}