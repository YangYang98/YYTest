package com.example.jetpackdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.jetpackdemo.databinding.ActivityMainBinding
import java.com.example.jetpackdemo.data.Config
import java.com.example.jetpackdemo.listener.EventHandleListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.config = Config()
        activityMainBinding.eventHandle = EventHandleListener(this)


    }
}