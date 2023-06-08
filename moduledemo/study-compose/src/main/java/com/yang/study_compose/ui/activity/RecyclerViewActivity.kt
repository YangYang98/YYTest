package com.yang.study_compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yang.study_compose.ui.theme.JetPackTheme


/**
 * Create by Yang Yang on 2023/6/7
 */
class RecyclerViewActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetPackTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(10.dp),
                    elevation = 10.dp
                ) {
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        RecyclerView()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Preview(showBackground = true)
    @Composable
    fun RecyclerView() {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.DarkGray)
        ) {
            //在LazyColumn中添加一个Text
            item {
                Text(text = "item{}方式")
            }

            //在第一个Text之后添加五个Text
            items(5) { index ->
                Text(text = "items(Int){}方式 --> 第${index + 1}项内容")
            }

            //添加另一个Text
            item {
                Text(text = "item{}方式 --> 最后一项")
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .background(Color.Green)
        ) {
            items(
                listOf("内容一", "内容二", "内容三", "内容四", "内容五", "内容六", "内容七")
            ) { options ->
                ListItem(modifier = Modifier, text = { Text(text = "items(List<T>) 内容: $options") })
            }

            itemsIndexed(listOf("内容一", "内容二", "内容三", "内容四", "内容五", "内容六", "内容七")) { index, options->
                ListItem(modifier = Modifier, text = { Text(text = "itemsIndexed(List<T>) 第${index}项内容: $options") })
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue),
            contentPadding = PaddingValues(20.dp)
        ) {
            items(
                listOf("contentPadding", "contentPadding", "contentPadding", "contentPadding", "contentPadding", "contentPadding", "contentPadding")
            ) { options ->
                ContentCard(content = options)
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                listOf("contentPadding 和 verticalArrangement", "contentPadding 和 verticalArrangement", "contentPadding 和 verticalArrangement", "contentPadding 和 verticalArrangement", "contentPadding 和 verticalArrangement", "contentPadding 和 verticalArrangement", "contentPadding 和 verticalArrangement")
            ) { options ->
                ContentCard(content = options)
            }
        }
    }

    @Composable
    fun ContentCard(content: String) {
        Card(
            elevation = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box {
                Text(
                    text = "有${content}",
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}