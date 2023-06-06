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
 * Create by Yang Yang on 2023/5/30
 */
class CommonLayoutViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetPackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            startActivity(Intent(this@CommonLayoutViewActivity, LinearLayoutActivity::class.java))
                        }) {
                            Text(text = "线性布局")
                        }

                        Button(onClick = {
                            startActivity(Intent(this@CommonLayoutViewActivity, FrameLayoutActivity::class.java))
                        }) {
                            Text(text = "帧布局")
                        }

                        Button(onClick = {
                            startActivity(Intent(this@CommonLayoutViewActivity, ConstraintLayoutActivity::class.java))
                        }) {
                            Text(text = "约束布局")
                        }
                    }
                }
            }
        }
    }
}