package com.yang.study_compose.ui.theme.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.yang.study_compose.ui.theme.JetPackTheme


/**
 * Create by Yang Yang on 2023/5/30
 */
class DialogViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetPackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DialogView()
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DialogView() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState()) //可滚动
        ) {

            val openDialog = remember {
                mutableStateOf(false)
            }
            val dialogWidth = 200.dp
            val dialogHeight = 50.dp
            if (openDialog.value) {
                //在Dialog组件显示过程中，当我们点击对话框以外区域时，onDismissRequest会触发执行，修改openDialog状态为false，
                // 触发DialogView重组，此时判断openDialog为false, Dialog无法被执行，对话框消失
                Dialog(onDismissRequest = { openDialog.value = false }) {
                    Box(modifier = Modifier
                        .size(dialogWidth, dialogHeight)
                        .background(color = MaterialTheme.colors.primary))
                }
            }
            Button(onClick = { openDialog.value = true }) {
                Text(text = "Dialog对话框")
            }


            val openAlertDialog = remember {
                mutableStateOf(false)
            }
            if (openAlertDialog.value) {
                AlertDialog(
                    onDismissRequest = { openAlertDialog.value = false },
                    title = {
                        Text(text = "标题")
                    },
                    text = {
                        Text(text = "这就是AlertDialog")
                    },
                    confirmButton = {
                        TextButton(onClick = { openAlertDialog.value = false }) {
                            Text(text = "Yes")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { openAlertDialog.value = false }) {
                            Text(text = "No")
                        }
                    }
                )
            }
            Button(onClick = { openAlertDialog.value = true }) {
                Text(text = "AlertDialog警告对话框")
            }

            var circleProgress by remember {
                mutableStateOf(0.1f)
            }
            val animatedCircleProgress by animateFloatAsState(
                targetValue = circleProgress,
                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
            )
            Column {
                CircularProgressIndicator(progress = animatedCircleProgress)
                Spacer(modifier = Modifier.requiredHeight(10.dp))
                OutlinedButton(onClick = {
                    if (circleProgress < 1f) {
                        circleProgress += 0.1f
                    }
                }) {
                    Text(text = "增加进度")
                }

                //当不设置progress时，就是无限加载的进度条
                CircularProgressIndicator()
                Spacer(modifier = Modifier.requiredHeight(10.dp))

                LinearProgressIndicator(progress = animatedCircleProgress)
                Spacer(modifier = Modifier.requiredHeight(10.dp))
                LinearProgressIndicator()
            }
        }
    }
}