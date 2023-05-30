package com.yang.study_compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yang.study_compose.R
import com.yang.study_compose.ui.theme.JetPackTheme


/**
 * Create by Yang Yang on 2023/5/30
 */
class FrameLayoutActivity : ComponentActivity() {

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
                    FrameLayoutView()
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun FrameLayoutView() {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()) //可滚动
        ) {
            Box {
                Box(modifier = Modifier
                    .size(150.dp)
                    .background(Color.Green))
                Box(modifier = Modifier
                    .size(80.dp)
                    .background(Color.Yellow))
                Text(text = "Box")
            }

            Spacer(modifier = Modifier.requiredHeight(20.dp))

            Surface(
                shape = RoundedCornerShape(8.dp),
                elevation = 10.dp,
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp).align(Alignment.CenterHorizontally)
            ) {
                Row(modifier = Modifier.clickable { }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 12.dp))

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "明天是几号",
                            style = MaterialTheme.typography.h6
                        )

                        Spacer(modifier = Modifier.padding(vertical = 12.dp))

                        Text(
                            text = "What's the date tomorrow",
                        )
                    }
                }
            }
        }
    }

}