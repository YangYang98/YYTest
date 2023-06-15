package com.yang.study_compose.ui.activity.customer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yang.study_compose.ui.weight.Chip
import com.yang.study_compose.ui.weight.StaggeredGrid


/**
 * Create by Yang Yang on 2023/6/13
 */
class CustomerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                TestStaggeredGrid()

                TestIntrinsicRow()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextWithPaddingToBaselinePreview() {
    Text(text = "Hello World", Modifier.firstBaselineToTop(24.dp))
}

//TODO 自定义View
@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = Modifier.layout { measurable, constraints ->

    //TODO 测量 start
    val placeable = measurable.measure(constraints)

    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)

    val firstBaseLine = placeable[FirstBaseline]
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseLine
    val height = placeable.height + placeableY
    //TODO 测量 end

    layout(placeable.width, height) {
        placeable.placeRelative(
            0, placeableY
        )
    }
}

//TODO 自定义ViewGroup
@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Layout(
        modifier = modifier,
        content = content
    ) {measurables, constraints ->
        val placeables = measurables.map { measurable ->
            //测量每个子组件
            measurable.measure(constraints)
        }

        var yPosition = 0
        layout(constraints.maxWidth, constraints.maxHeight) {
            //布局每个子组件

            placeables.forEach { placeable ->
                placeable.placeRelative(0, yPosition)
                yPosition += placeable.height
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomLayoutPreview() {
    CustomLayout(modifier = Modifier.padding(8.dp)) {
        Text(text = "CustomLayout", color = Color.Blue)
        Text(text = "CustomLayout", color = Color.Blue)
        Text(text = "CustomLayout", color = Color.Blue)
        Text(text = "CustomLayout", color = Color.Blue)
    }
}


//TODO 固有特性测量Intrinsic
//TODO 固有特性测量的本质就是允许父组件预先获取到每个子组件宽高信息后，影响自身在测量阶段获取到的constraints宽高信息，从而间接影响子组件的测量过程。
@Composable
fun TwoText(
    modifier: Modifier = Modifier, text1: String, text2: String
) {
    //TODO 使用Row组件的固有特性测量，预先测量子组件，并根据子组件的高度，先确定了Row组件的高度。
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )
        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(1.dp))
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),
            text = text2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TwoTextPreview() {
    TwoText(text1 = "Hello", text2 = "World")
}

//TODO 自定义固有特性测量
@Composable
fun IntrinsicRow(modifier: Modifier, content: @Composable () -> Unit) {
    Layout(
        content = content,
        modifier = modifier,
        measurePolicy = object : MeasurePolicy {
            override fun MeasureScope.measure(
                measurables: List<Measurable>,
                constraints: Constraints
            ): MeasureResult {
                val divideConstraints = constraints.copy(minWidth = 0)
                val mainPlaceables = measurables.filter {
                    it.layoutId == "main"
                }.map {
                    it.measure(constraints)
                }
                val dividePlaceable = measurables.first {
                    it.layoutId == "divider"
                }.measure(divideConstraints)

                val midPos = constraints.maxWidth / 2

                return layout(constraints.maxWidth, constraints.maxHeight) {
                    mainPlaceables.forEach {
                        it.placeRelative(0, 0)
                    }
                    dividePlaceable.placeRelative(midPos, 0)
                }
            }

            override fun IntrinsicMeasureScope.minIntrinsicHeight(
                measurables: List<IntrinsicMeasurable>,
                width: Int
            ): Int {
                var maxHeight = 0
                measurables.forEach {
                    maxHeight = it.minIntrinsicHeight(width).coerceAtLeast(maxHeight)
                }
                return maxHeight
            }
        }
        )
}

@Preview(showBackground = true)
@Composable
fun TestIntrinsicRow() {

    IntrinsicRow(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start)
                .layoutId("main"),
            text = "Hello"
        )
        Divider(color = Color.Black, modifier = Modifier
            .fillMaxHeight()
            .width(4.dp)
            .layoutId("divider")
        )
        Text(
            modifier = Modifier
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End)
                .layoutId("main"),
            text = "World"
        )
    }
}

@Composable
fun SubcomposeRow(
    modifier: Modifier,
    text: @Composable () -> Unit,
    divider: @Composable (Int) -> Unit //传入高度
) {
    SubcomposeLayout(modifier = modifier) { constraints ->

        var maxHeight = 0
        val placeables = subcompose("text", text).map {
            val placeable = it.measure(constraints)
            maxHeight = placeable.height.coerceAtLeast(maxHeight)
            placeable
        }
        val dividerPlaceable = subcompose("divider") {
            divider(maxHeight)
        }.map {
            it.measure(constraints.copy(minWidth = 0))
        }

        assert(dividerPlaceable.size == 1) { "DividerScope Error!" }


        val midPos = constraints.maxWidth / 2
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach {
                it.placeRelative(0, 0)
            }

            dividerPlaceable.forEach {
                it.placeRelative(midPos, 0)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestSubcomposeRow() {
    SubcomposeRow(
        modifier = Modifier.fillMaxWidth(),
        text = {
            Text(
                modifier = Modifier
                    .wrapContentWidth(Alignment.Start),
                text = "Hello"
            )
            Text(
                modifier = Modifier
                    .wrapContentWidth(Alignment.End),
                text = "World"
            )
        }
    ) {
        val heightDp = with(LocalDensity.current) { it.toDp() }
        Divider(color = Color.Black, modifier = Modifier
            .height(heightDp)
            .width(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TestStaggeredGrid() {

    val topics = listOf(
        "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
        "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
        "Religion", "Social sciences", "Technology", "TV", "Writing"
    )

    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        StaggeredGrid() {
            topics.forEach {
                Chip(modifier = Modifier.padding(8.dp), text = it)
            }
        }
    }
}