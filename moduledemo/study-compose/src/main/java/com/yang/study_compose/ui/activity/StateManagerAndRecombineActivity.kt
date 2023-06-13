package com.yang.study_compose.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yang.study_compose.ui.theme.JetPackTheme


/**
 * Create by Yang Yang on 2023/6/8
 */
class StateManagerAndRecombineActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //JetPackTheme是由Android Studio根据项目名称自动生成，为界面提供默认主题
            JetPackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            startActivity(Intent(this@StateManagerAndRecombineActivity, StateManagerActivity::class.java))
                        }) {
                            Text(text = "状态管理")
                        }

                        Button(onClick = {
                            startActivity(Intent(this@StateManagerAndRecombineActivity, RecombineActivity::class.java))
                        }) {
                            Text(text = "重组")
                        }
                    }
                }
            }
        }
    }
}