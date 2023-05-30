package com.yang.study_compose.ui.theme.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yang.study_compose.ui.theme.JetPackTheme


/**
 * Create by Yang Yang on 2023/5/30
 */
class SelectorViewActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SelectorView()
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun SelectorView() {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState()) //可滚动
        ) {

            val checkedState = remember {
                mutableStateOf(true)
            }
            Text(text = "Checkbox复选框")
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = {checkedState.value = it},
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Magenta,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.Cyan
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "TriStateCheckbox三态选择框")
            val (state, onStateChange) = remember {
                mutableStateOf(true)
            }
            val (state2, onStateChange2) = remember {
                mutableStateOf(true)
            }
            val parentState = remember(state, state2) {
                if (state && state2) {
                    ToggleableState.On
                } else if (!state && !state2) {
                    ToggleableState.Off
                } else ToggleableState.Indeterminate
            }
            val onParentClick = {
                val s = parentState != ToggleableState.On
                onStateChange(s)
                onStateChange2(s)
            }
            TriStateCheckbox(
                state = parentState,
                onClick = onParentClick,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colors.primary
                )
            )
            Column(Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) {
                Checkbox(checked = state, onCheckedChange = onStateChange)
                Checkbox(checked = state2, onCheckedChange = onStateChange2)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Switch单选开关")
            val switchState = remember {
                mutableStateOf(true)
            }
            Switch(checked = switchState.value, onCheckedChange = { switchState.value = it })

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Slider滑竿组件")
            Column {
                var sliderPosition: Float by remember {
                    mutableStateOf(0f)
                }
                Text(text = "%.1f".format(sliderPosition * 100) + "%")
                Slider(value = sliderPosition, onValueChange = {sliderPosition = it})
            }

        }

    }
}