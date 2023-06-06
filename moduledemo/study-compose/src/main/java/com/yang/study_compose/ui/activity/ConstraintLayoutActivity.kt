package com.yang.study_compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.yang.study_compose.R
import com.yang.study_compose.ui.theme.JetPackTheme


/**
 * Create by Yang Yang on 2023/5/30
 */
class ConstraintLayoutActivity : ComponentActivity() {

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

                    ConstraintLayoutView()
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ConstraintLayoutView() {

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()) //可滚动
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .padding(10.dp)
            ) {
                val (portraitImageRef, titleRef, descRef) = remember {
                    createRefs()
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier.constrainAs(portraitImageRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
                )

                Text(
                    text = "ConstraintLayout简单使用",
                    modifier = Modifier.constrainAs(titleRef) {
                        top.linkTo(portraitImageRef.top)
                        start.linkTo(portraitImageRef.end, 10.dp)
                    },
                    fontSize = 20.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Left
                )

                Text(
                    text = "这里是详情详情详情详情详情详情详情详情详情详情详情详情",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.constrainAs(descRef) {
                        top.linkTo(titleRef.bottom, 5.dp)
                        start.linkTo(portraitImageRef.end, 10.dp)

                        width = Dimension.preferredWrapContent
                    }
                )
            }


            ConstraintLayout(modifier = Modifier) {
                val (userNameTextRef, passwordTextRef, userNameEditRef, passwordEditRef) = remember {
                    createRefs()
                }
                val barrier = createEndBarrier(userNameTextRef, passwordTextRef)

                Text(
                    text = "用户名",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .constrainAs(userNameTextRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                        .padding(10.dp, 10.dp, 0.dp, 10.dp)
                )

                Text(
                    text = "密码",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .constrainAs(passwordTextRef) {
                            start.linkTo(parent.start)
                            top.linkTo(userNameTextRef.bottom, 10.dp)
                        }
                        .padding(10.dp, 20.dp, 0.dp, 10.dp)
                )

                OutlinedTextField(
                    value = "", onValueChange = {},
                    modifier = Modifier.constrainAs(userNameEditRef) {
                        start.linkTo(barrier, 10.dp)
                        top.linkTo(parent.top, 10.dp)
                        bottom.linkTo(userNameTextRef.bottom)
                        height = Dimension.wrapContent
                    },
                    label = { Text(text = "Barrier分界线") }
                )

                OutlinedTextField(
                    value = "", onValueChange = {},
                    modifier = Modifier.constrainAs(passwordEditRef) {
                        start.linkTo(barrier, 10.dp)
                        top.linkTo(userNameEditRef.bottom, 10.dp)
                        bottom.linkTo(passwordTextRef.bottom)
                        height = Dimension.wrapContent
                    },
                    label = { Text(text = "Barrier分界线") }
                )
            }

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(10.dp)
            ) {

                val guideLine = createGuidelineFromTop(0.2f)

                val (
                    userPortraitBackgroundRef,
                    userPortraitBackgroundBottomRef,
                    userPortraitImgRef,
                    userPortraitDescRef
                ) = remember {
                    createRefs()
                }

                Box(
                    modifier = Modifier
                        .constrainAs(userPortraitBackgroundRef) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            bottom.linkTo(guideLine)
                            height = Dimension.fillToConstraints
                            width = Dimension.matchParent
                        }
                        .background(Color(0xFF1E9FFF))
                )

                Box(
                    modifier = Modifier
                        .constrainAs(userPortraitBackgroundBottomRef) {
                            top.linkTo(guideLine)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                            width = Dimension.matchParent
                        }
                        .background(Color(0xFF009688))
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(userPortraitImgRef) {
                            top.linkTo(guideLine)
                            bottom.linkTo(guideLine)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(width = 2.dp, color = Color(0xFF5FB878), shape = CircleShape)
                )

                Text(
                    text = "Guideline引导线",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.constrainAs(userPortraitDescRef) {
                        top.linkTo(userPortraitImgRef.bottom, 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }

            Row (modifier = Modifier.height(500.dp).fillMaxWidth()) {

                /**
                 * Compose提供了三种Chain Style
                 *  • Spread：链条中每个元素平分整个parent空间。
                 *  • SpreadInside：链条中首尾元素紧贴边界，剩下每个元素评分整个parent空间。
                 *  • Packed：链条中所有元素聚集到中间。
                 */

                ConstraintLayout(modifier = Modifier.weight(1f).fillMaxHeight().background(Color.DarkGray)) {
                    val (firstRef, secondRef, thirdRef, fourthRef) = remember {
                        createRefs()
                    }

                    createVerticalChain(firstRef, secondRef, thirdRef, fourthRef, chainStyle = ChainStyle.Spread)

                    Text(
                        text = "使用Chain链接 --- ChainStyle.Spread",
                        color = Color.Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(firstRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )

                    Text(
                        text = "使用Chain链接 --- ChainStyle.Spread",
                        color = Color.Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(secondRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )

                    Text(
                        text = "使用Chain链接 --- ChainStyle.Spread",
                        color = Color.Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(thirdRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )

                    Text(
                        text = "使用Chain链接 --- ChainStyle.Spread",
                        color = Color.Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(fourthRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )
                }

                ConstraintLayout(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    val (firstRef, secondRef, thirdRef, fourthRef) = remember {
                        createRefs()
                    }

                    createVerticalChain(firstRef, secondRef, thirdRef, fourthRef, chainStyle = ChainStyle.SpreadInside)

                    Text(
                        text = "使用Chain链接 --- ChainStyle.SpreadInside",
                        color = Color.Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(firstRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )

                    Text(
                        text = "使用Chain链接 --- ChainStyle.SpreadInside",
                        color = Color.Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(secondRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )

                    Text(
                        text = "使用Chain链接 --- ChainStyle.SpreadInside",
                        color = Color.Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(thirdRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )

                    Text(
                        text = "使用Chain链接 --- ChainStyle.Spread",
                        color = Color.Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(fourthRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )
                }

                ConstraintLayout(modifier = Modifier.weight(1f).fillMaxHeight().background(Color.DarkGray)) {
                    val (firstRef, secondRef, thirdRef, fourthRef) = remember {
                        createRefs()
                    }

                    createVerticalChain(firstRef, secondRef, thirdRef, fourthRef, chainStyle = ChainStyle.Packed)

                    Text(
                        text = "使用Chain链接 --- ChainStyle.Packed",
                        color = Color.Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(firstRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )

                    Text(
                        text = "使用Chain链接 --- ChainStyle.Packed",
                        color = Color.Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(secondRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )

                    Text(
                        text = "使用Chain链接 --- ChainStyle.Packed",
                        color = Color.Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(thirdRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )

                    Text(
                        text = "使用Chain链接 --- ChainStyle.Packed",
                        color = Color.Cyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(fourthRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                    )
                }
            }

        }

    }
}