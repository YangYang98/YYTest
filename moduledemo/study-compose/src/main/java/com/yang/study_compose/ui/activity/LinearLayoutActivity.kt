package com.yang.study_compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yang.study_compose.ui.theme.JetPackTheme


/**
 * Create by Yang Yang on 2023/5/30
 */
class LinearLayoutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetPackTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    color = MaterialTheme.colors.background
                ) {

                    VerticalLinearLayoutView()
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun VerticalLinearLayoutView() {
        Column (
            modifier = Modifier.verticalScroll(rememberScrollState()) //可滚动
        ){
            Column {
                Text(
                    text = "Hello, World!",
                    style = MaterialTheme.typography.h6
                )
                Text(text = "Column -- 默认情况下")
            }

            Spacer(modifier = Modifier
                .requiredHeight(10.dp)
                .background(Color.LightGray))

            Column(
                modifier = Modifier.border(1.dp, Color.Black),
            ) {
                Text(
                    text = "在不给Column指定高度、宽度、大小的情况下，Column组件默认会包裹里面的子项。在这个时候，我们无法使用Column参数中的verticalArrangement或horizontalAlignment来定位子项在Column中的整体位置",
                    style = TextStyle(fontSize = 7.sp)
                )
                Text(
                    text = "Column -- 添加边框",
                    style = TextStyle(fontSize = 7.sp)
                )
            }

            Spacer(modifier = Modifier
                .requiredHeight(10.dp)
                .background(Color.LightGray))

            Column(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(250.dp, 150.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "只有指定了高度或者宽度，才能使用verticalArrangement或horizontalAlignment来定位子项在Column中的位置",
                    style = TextStyle(fontSize = 7.sp)
                )
                Spacer(modifier = Modifier
                    .requiredHeight(10.dp))
                Text(
                    text = "Column -- verticalArrangement = Arrangement.Center",
                    style = TextStyle(fontSize = 7.sp)
                )
            }

            Spacer(modifier = Modifier
                .requiredHeight(10.dp))

            Column(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .size(250.dp, 150.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hello, World!",
                    style = TextStyle(fontSize = 7.sp)
                )
                Spacer(modifier = Modifier
                    .requiredHeight(10.dp))

                //在对齐效果的影响下，Modifier.align修饰符会优先于Column的horizontalAlignment参数
                Text(
                    text = "Column -- Text设置Modifier.align",
                    style = TextStyle(fontSize = 7.sp),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            HorizontalLinearLayoutView()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun HorizontalLinearLayoutView() {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            elevation = 10.dp
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Jetpack Compose 是什么？",
                    style = MaterialTheme.typography.h6
                )
                Spacer(Modifier.padding(vertical = 5.dp))
                Text(
                    text = "Jetpack Compose是用于构建原生Android界面的新工具包。它可简化并加快Android上的界" +
                            "面开发,使用更少的代码、强大的工具和直观的KotlinAPI,让后应用生动而精彩。",
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Face, contentDescription = null)
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Share, contentDescription = null)
                    }
                }
            }
        }
    }
}