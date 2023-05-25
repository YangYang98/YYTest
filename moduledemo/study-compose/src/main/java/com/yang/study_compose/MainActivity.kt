package com.yang.study_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yang.study_compose.ui.theme.JetPackTheme

class MainActivity : ComponentActivity() {
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
                    Greeting("Android & YANGYANG")

                    Column {
                        ModifierSize()
                        ModifierBackground()
                        ModifierFillMaxSize()
                    }
                }
            }
        }
    }
}

/**
 * 传统布局有Margin和Padding之分，Compose中只有padding这一种修饰符，
 * 根据在调用链中的位置不同发挥不同作用，概念更加简洁，这也体现了Modifier中链式调用的特点。
 */
@Preview(showBackground = true)
@Composable
fun ModifierBorderAndPadding() {
    Box(
        modifier = Modifier
            .padding(8.dp) // 外间隙
            .border(2.dp, Color.Red, shape = RoundedCornerShape(2.dp)) //边框
            .padding(8.dp) //内间隙
    ) {
        Spacer(modifier = Modifier.size(width = 100.dp, height = 10.dp).background(Color.Blue))
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierFillMaxSize() {
    Column {
        Row(Modifier.height(200.dp)) {
            Box(modifier = Modifier
                .fillMaxHeight()
                .width(50.dp)
                .background(Color.Cyan))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue))
        }

        Spacer(modifier = Modifier
            .height(10.dp)
            .fillMaxWidth()
            .background(Color.Black))
        Row(Modifier.height(200.dp)) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow))
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun ModifierSize() {
    JetPackTheme {
        Row {
            Image(
                painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))

            //size同时提供了重载方法，支持单独设置组件的宽度与高度
            Image(
                painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp, 300.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierBackground() {

    val verticalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color.Blue,
            Color.Cyan,
            Color.Yellow
        )
    )
    JetPackTheme {
        Row {
            Box(modifier = Modifier
                .size(100.dp)
                .background(color = Color.Blue)
            ) {
                Text(text = "纯色", Modifier.align(Alignment.Center))
            }
            Spacer(modifier = Modifier.width(10.dp))

            Box(modifier = Modifier
                .size(100.dp)
                .background(brush = verticalGradientBrush)
            ) {
                Text(text = "渐变色", Modifier.align(Alignment.Center))
            }
        }
    }
}