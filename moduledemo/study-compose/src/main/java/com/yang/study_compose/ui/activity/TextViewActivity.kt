package com.yang.study_compose.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yang.study_compose.R
import com.yang.study_compose.ui.theme.JetPackTheme


/**
 * Create by Yang Yang on 2023/5/29
 */
class TextViewActivity: ComponentActivity() {

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
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.verticalScroll(rememberScrollState()) //可滚动
                    ) {
                        TextView()
                    }
                }
            }
        }
    }

    /**
     *
     */
    //TODO TextStyle中的大部分字段也可以在Text参数中直接设置，
    // 例如fonteSize、fontWeight、fontStyle等。
    // 注意Text参数会覆盖对TextStyle同名属性的设置
    @Preview(showBackground = true)
    @Composable
    fun TextView() {
        Text(text = "hello")
        Text(text = stringResource(id = R.string.app_name))

        Text(
            text = "基础\n样式",
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold, //字体粗细
                background = Color.Cyan,
                lineHeight = 50.sp
            )
        )
        Text(
            text = "字体间距",
            style = TextStyle(
                letterSpacing = 4.sp //字体间距
            )
        )
        Text(
            text = "删除线",
            style = TextStyle(
                textDecoration = TextDecoration.LineThrough // 删除线
            )
        )
        Text(
            text = "预置TextStyle",
            style = MaterialTheme.typography.h6.copy(fontStyle = FontStyle.Italic)
        )

        Text(
            modifier = Modifier.padding(20.dp),
            text = "普通  =========  长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本",
            style = MaterialTheme.typography.body1
        )

        Text(
            modifier = Modifier.padding(20.dp),
            text = "maxLines = 1  =========  长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本",
            style = MaterialTheme.typography.body1,
            maxLines = 1
        )

        Text(
            modifier = Modifier.padding(20.dp),
            text = "maxLines = 1 + 截断方式  =========  长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本",
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(text = "FontFamily", fontFamily = FontFamily.Cursive)

        //append用来添加子串的文本，withStyle为append的子串指定文字或段落样式
        /**
         * SpanStyle继承了TextStyle中关于文字样式相关的字段，
         * 而ParagraphStyle继承了TextStyle中控制段落的样式，
         * 例如textAlign、lineHeight等。某种意义上说SpanStyle与ParagraphStyle分拆了TextStyle，
         * 可以对子串分别进行文字以及段落样式的设置
         *
         * TODO SpanStyle或ParagraphStyle中的设置优先于整个TextStyle中的同名属性设置
         */
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 24.sp)) {
                append("多样式文字")
            }
            withStyle(style = SpanStyle(
                fontWeight = FontWeight.W900,
                fontSize = 22.sp
            )
            ) {
                append("FontWeight.W900")
            }
            append("\n")
            withStyle(style = ParagraphStyle(lineHeight = 25.sp)) {
                append("ParagraphStyle(lineHeight = 25.sp)")
            }
            append("\n")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.W900,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Red
                )
            ) {
                append("fontWeight && textDecoration && color")
            }
        })

        val clickAnnotatedString = buildAnnotatedString {
            withStyle(
                style = ParagraphStyle(lineHeight = 25.sp)
            ) {
                //为pushStringAnnotation与pop之间的区域添加标签
                pushStringAnnotation(tag = "URL", annotation = "https://www.baidu.com")
                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.W900,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue
                )
                ) {
                    append("可点击文本组件ClickableText，点我试试")
                }
                pop()

                append("不可点击文本组件，点我没用")
            }
        }
        ClickableText(text = clickAnnotatedString, onClick = {
            clickAnnotatedString.getStringAnnotations(tag = "URL", start = it, end = it).firstOrNull()?.let {
                Toast.makeText(baseContext, it.item, Toast.LENGTH_SHORT).show()
            }
        })

        SelectionContainer {
            Text(text = "可以被复制的文字")
        }
    }
}