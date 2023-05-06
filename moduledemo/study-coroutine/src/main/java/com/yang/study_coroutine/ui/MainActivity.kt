package com.yang.study_coroutine.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yang.study_coroutine.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_tradition).setOnClickListener {
            startActivity(Intent(this, TraditionActivity::class.java))
        }

        findViewById<Button>(R.id.btn_coroutine).setOnClickListener {
            startActivity(Intent(this, CoroutineActivity::class.java))
        }

        findViewById<Button>(R.id.btn_coroutine_mvvm).setOnClickListener {
            startActivity(Intent(this, CoroutineMVVMActivity::class.java))
        }
    }
}