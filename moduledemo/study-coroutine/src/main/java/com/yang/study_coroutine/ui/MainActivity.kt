package com.yang.study_coroutine.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.yang.study_coroutine.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivity2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_tradition).setOnClickListener {
            startActivity(Intent(this, TraditionActivity::class.java))
        }

        /*findViewById<Button>(R.id.btn_coroutine).setOnClickListener {
            mainScope.launch {
                val job = launch {
                    Toast.makeText(this@MainActivity, "等一下啊", Toast.LENGTH_LONG).show()
                    delay(3000)
                }.join()
                startActivity(Intent(this@MainActivity, CoroutineActivity::class.java))
            }
        }*/

        findViewById<Button>(R.id.btn_coroutine).onClickSuspend {
            val job = launch {
                Toast.makeText(this@MainActivity, "等一下啊", Toast.LENGTH_LONG).show()
                delay(3000)
            }.join()
            startActivity(Intent(this@MainActivity, CoroutineActivity::class.java))
        }

        findViewById<Button>(R.id.btn_coroutine_mvvm).setOnClickListener {
            startActivity(Intent(this, CoroutineMVVMActivity::class.java))
        }

        findViewById<Button>(R.id.btn_flow).setOnClickListener {
            startActivity(Intent(this, FlowActivity::class.java))
        }
    }
}