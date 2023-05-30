package com.yang.study_compose.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yang.study_compose.ui.theme.JetPackTheme

class CommonBaseViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            JetPackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.verticalScroll(rememberScrollState()) //可滚动
                    ) {
                        Button(onClick = {
                            startActivity(Intent(this@CommonBaseViewActivity, TextViewActivity::class.java))
                        }) {
                            Text(text = "Text文本组件")
                        }

                        Button(onClick = {
                            startActivity(Intent(this@CommonBaseViewActivity, EditTextActivity::class.java))
                        }) {
                            Text(text = "TextField输入框")
                        }

                        Button(onClick = {
                            startActivity(Intent(this@CommonBaseViewActivity, ImageViewActivity::class.java))
                        }) {
                            Text(text = "图片组件")
                        }

                        Button(onClick = {
                            startActivity(Intent(this@CommonBaseViewActivity, ButtonViewActivity::class.java))
                        }) {
                            Text(text = "按钮组件")
                        }

                        Button(onClick = {
                            startActivity(Intent(this@CommonBaseViewActivity, SelectorViewActivity::class.java))
                        }) {
                            Text(text = "选择器组件")
                        }

                        Button(onClick = {
                            startActivity(Intent(this@CommonBaseViewActivity, DialogViewActivity::class.java))
                        }) {
                            Text(text = "选择器组件")
                        }
                    }
                }
            }
        }
    }
}