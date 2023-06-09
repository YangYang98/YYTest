package com.yang.study_compose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yang.study_compose.data.City
import com.yang.study_compose.model.CounterViewModel


/**
 * Create by Yang Yang on 2023/6/8
 */
class StateManagerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                CounterScreen()

                CounterScreen2()

                CounterScreen3()
            }
        }
    }
}

@Composable
fun CounterScreen() {

    /**
     * TODO 状态上提最佳实践
     *
     * 当为了方便Composable共享而上提状态时，应该将状态上提到这几个Composable的最小共同父Composable。
     * 实现共享的同时，避免Scope无意义的扩大。
     */

    Column {

        Text(text = "状态上提")
        var counter by remember {
            mutableStateOf(0)
        }

        CounterComponent(
            counter = counter, { counter++ }
        ) {
            if (counter > 0) {
                counter --
            }
        }
    }

}

@Composable
fun CounterScreen2() {

    /**
     * TODO rememberSavable
     *
     * rememberSavable，它可以像Activity的onSaveInstanceState那样在进程被杀死时自动保存状态，
     * 同时像onRestoreInstanceState一样随进程重建而自动恢复。
     * 能跨越Activity或者跨越进程存在。比如当横竖屏等ConfigurationChanged事件发生时，状态会发生丢失。
     */

    Column {

        Text(text = "rememberSavable自动保存状态")
        var counter by rememberSaveable {
            mutableStateOf(0)
        }

        CounterComponent(
            counter = counter, { counter++ }
        ) {
            if (counter > 0) {
                counter --
            }
        }
    }
}

@Composable
fun CounterScreen3() {

    /**
     * TODO viewModel()
     *
     * viewModel()会从当前最近的ViewModelStore中获取ViewModel实例，
     * 这个ViewModelStore可能是一个Activity，也可能是一个Fragment。
     * 如果ViewModel不存在，就创建一个新的并存入ViewModelStore。
     * 只要ViewModelStore不销毁，其内部的ViewModel将一直存活。
     * ex: 例如一个Activity中的Composable通过viewModel()创建的ViewModel被当前的Activity持有。
     *      在Activity销毁之前，ViewModel将一直存在，viewModel()每次调用将返回同一个实例，所以此时可以不使用remember进行缓存。
     */

    Column {
        
        Text(text = "ViewModel管理状态")
        val counterViewModel: CounterViewModel = viewModel()
        CounterComponent(
            counter = counterViewModel.counter.value,
            counterViewModel::increment,
            counterViewModel::decrement
        )
    }
}


@Composable
fun CounterComponent(
    counter: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(text = "Click buttons to adjust your value:")

        Text(
            text = "$counter",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )

        Row {
            Button(
                onClick = onDecrement,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "-")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = onIncrement,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "+")
            }
        }
    }
}


@Composable
fun TestSaver() {

    val selectedCity = rememberSaveable(stateSaver = CitySaver) {
        mutableStateOf(City("Barcelona", "Spain"))
    }


    /**
     * TODO MapSaver
     *
     * MapSaver将对象转换为Map<String, Any>的结构进行保存，注意value是可保存到Bundle的类型(序列化)
     */
    val CitySaver2 = run {
        val nameKey = "Name"
        val countryKey = "Country"

        mapSaver(
            save = { mapOf(nameKey to it.name, countryKey to it.country) },
            restore = { City(it[nameKey] as String, it[countryKey] as String) }
        )
    }

    val selectedCity2 = rememberSaveable(stateSaver = CitySaver2) {
        mutableStateOf(City("Barcelona", "Spain"))
    }

    /**
     * TODO ListSaver
     *
     * ListSaver则是将对象转换为List<Any>的数据结构进行保存。
     */
    val CitySaver3 = listSaver<City, Any>(
        save = { listOf(it.name, it.country) },
        restore = { City(it[0] as String, it[1] as String) }
    )

    val selectedCity3 = rememberSaveable(stateSaver = CitySaver3) {
        mutableStateOf(City("Barcelona", "Spain"))
    }
}

/**
 * TODO Saver
 *
 * 有的数据结构可能无法添加Parcelable接口，比如定义在三方库的类等，
 * 此时可以通过自定义Saver为其实现保存和恢复的逻辑。只需要在调用rememberSavable时传入此Saver即可
 */
object CitySaver : Saver<City, Bundle> {
    override fun restore(value: Bundle): City? {
        return value.getString("name")?.let { name ->
            value.getString("country")?.let { country ->
                City(name, country)
            }
        }
    }

    override fun SaverScope.save(value: City): Bundle? {
        return Bundle().apply {
            putString("name", value.name)
            putString("country", value.country)
        }
    }

}