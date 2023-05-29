package com.yang.study_compose.ui.theme.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yang.study_compose.ui.theme.JetPackTheme


/**
 * Create by Yang Yang on 2023/5/29
 */
class ButtonViewActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ButtonView()
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ButtonView() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState()) //可滚动
        ) {
            Button(onClick = {}) {
                Text(text = "简单例子")
            }

            Button(onClick = {
                Toast.makeText(baseContext, "水平添加图标", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Text(text = "水平添加图标")
            }

            val interactionSource = remember {
                MutableInteractionSource()
            }
            val pressState = interactionSource.collectIsPressedAsState()
            val borderColor = if (pressState.value) Color.Green else Color.Gray

            Button(
                onClick = {},
                border = BorderStroke(2.dp, borderColor),
                interactionSource = interactionSource
            ) {
                Text(text = "Long Press")
            }

            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            }

            FloatingActionButton(onClick = {  }, modifier = Modifier.size(70.dp)) {
                Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null)
            }

            ExtendedFloatingActionButton(
                text = {
                    Text(text = "带有文字扩展的FAB")

                },
                onClick = {  },
                icon = {
                    Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null)
                }
            )
        }
    }

}