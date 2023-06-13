package com.yang.study_compose.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yang.study_compose.data.City
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Create by Yang Yang on 2023/6/13
 */
class RecombineActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                //TestDisposableEffect(context = this@RecombineActivity)

                TestRememberCoroutineScope()
            }
        }
    }
}

@Composable
fun TestDisposableEffect(context: Context) {
    backPressHandler {
        //返回键处理
        Toast.makeText(context, "再按一次", Toast.LENGTH_LONG).show()
    }
}

/**
 * backPressHandler这样与UI无关的Composable，这里可以当作普通Kotlin函数那样，函数名使用首字母小写即可。
 */
@Composable
fun backPressHandler(enable: Boolean = true, onBackPressed: () -> Unit) {
    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher
    val backCallback = remember {
        object : OnBackPressedCallback(enable) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
    }
    //TODO DisposableEffect可以感知Composable的onActive和onDispose，允许通过副作用完成一些预处理和收尾处理。

    DisposableEffect(backDispatcher) {//dispatcher 发生变化时执行
        backDispatcher.addCallback(backCallback)
        onDispose {
            //当Compose进入onDispose时进行
            //移除backCallback避免泄露
            backCallback.remove()
        }
    }
}

/**
 * TODO SideEffect在每次成功重组时都会执行，所以不能用来处理那些耗时或者异步的副作用逻辑。
 * 重组会触发Composable重新执行，但是重组不一定会成功结束，有的重组可能会中途失败。SideEffect仅在重组成功时才会执行
 */
@Composable
fun TestSideEffect(drawerTouchHandler: TouchHandler) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    //当drawState发生变化时，会将最新状态通知到外部的TouchHandler。如果不放到SideEffect中执行，则有可能会传出一个错误状态。
    SideEffect { //将drawerState通知外部
        drawerTouchHandler.enable =drawerState.isOpen
    }
}

class TouchHandler() {
    var enable: Boolean = false
}

//TODO 异步处理的副作用API

/**
 * LaunchedEffect
 * 当副作用中有处理异步任务的需求时，可以使用LaunchedEffect。
 * 在Composable进入OnActive时，LaunchedEffect会启动协程执行block中的内容，可以在其中启动子协程或者调用挂起函数。
 * 当Composable进入OnDispose时，协程会自动取消，因此LaunchedEffect不需要实现OnDispose{...}。
 *
 * LaunchedEffect支持观察参数key的设置，当key发生变化时，当前协程自动结束，同时开启新协程。
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestLaunchedEffect(
    state: UiState<List<City>>,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onClick: () -> Unit
) {

    //当state中包含错误时，SnackBar显示
    //如果state中的错误已经解除，LaunchedEffect协程结束，SnackBar消失
    if (state.hasError) {
        //当scaffoldState.snackbarHostState变化时，之前的Snakbar消失
        //新的Snakbar显示
        LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Error message",
                actionLabel = "Retry message"
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "主页面")

            Button(onClick = onClick) {
                Text(text = "产生错误")
            }
        }
    }
}

class UiState<T> {

    var hasError = true
}

/**
 * TODO rememberCoroutineScope
 * LaunchedEffect虽然可以启动协程，但是LaunchedEffect只能在Composable中调用，
 * 如果想在非Composable环境中使用协程，例如在Button的OnClick中使用协程显示SnackBar，
 * 并希望其在OnDispose时自动取消，此时可以使用rememberCoroutineScope。
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestRememberCoroutineScope(
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Column {

            Button(onClick = {
                scope.launch {
                    //在按钮单击事件中创建一个新的协程作用域,用于显示snackBar
                    scaffoldState.snackbarHostState.showSnackbar(
                        "Something happened!",
                        duration = SnackbarDuration.Long,
                        actionLabel = "Hide"
                    )
                }
            }) {
                Text(text = "点我")
            }
        }
    }
}

/**
 * TODO rememberUpdatedState
 * LaunchedEffect会在参数key变化时启动一个协程，但有时我们并不希望协程中断，
 * 只要能够实时获取最新状态即可，此时可以借助rememberUpdatedState实现。
 */
@Composable
fun TestRememberUpdatedState(onTimeout: () -> Unit) {
    val currentOnTimeOut by rememberUpdatedState(newValue = onTimeout)
    //此副作用的生命周期同MyScreen一致
    //不会因为MyScreen的重组重新执行

    //将LaunchedEffect的参数key设置为Unit, block一旦开始执行，就不会因为MyScreen的重组而中断。
    // 但是当它执行到currentOnTimeout()时，仍然可以获取最新的OnTimeout实例，这是由rememberUpdatedState确保的。
    LaunchedEffect(key1 = Unit) {
        delay(2000)
        currentOnTimeOut() //总是能取到最新的onTimeOut
    }
}

/**
 * TODO snapshotFlow
 * 在LaunchedEffect中可以通过rememberUpdatedState获取最新状态，
 * 但是当状态发生变化时，LaunchedEffect无法第一时间收到通知，
 * 如果通过改变观察参数key来通知状态变化，则会中断当前执行中的任务，成本太高。
 */
/**
 * 最佳实践：
 * 当一个LaunchedEffect中依赖的State会频繁变化时，不应该使用State的值作为key，
 * 而应该将State本身作为key，然后在LaunchedEffect内部使用snapshotFlow依赖状态。
 * 使用State作为key是为了当State对象本身变化时重启副作用。
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestSnapshotFlow() {

    val pagerState = rememberPagerState()

    LaunchedEffect(key1 = pagerState) {
        //pagerState转换为Flow
        snapshotFlow {
            pagerState.currentPage
        }.collect {
            //currentPage发生变化
        }
    }
}


// TODO 状态创建的副作用API

/**
 * TODO remember
 * 在Stateful Composable中创建状态时，需要使用remember包裹，
 * 状态只在OnActive时创建一次，不跟随重组反复创建，所以remember本质上也是一种副作用API。
 */


/**
 * TODO produceState
 */
@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun TestProduceState() {

}