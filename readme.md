/*********** remember作用 ****************/

var counter by remember {
    mutableStateOf(0)
}

Column中读取counter的值并传给Text显示。当单击Button后，counter的数值发生增减变化，此时读取状态counter的Column发生重组，
当Column再次执行时，到var counter by remember{mutableStateOf(0)}时获取的counter应该是更新后的数字，这样才能保证在Text中显示最新的数值。

此处的remember正是保证获取更新值的关键，如果移除包裹mutableStateOf的remember，此时会发现当单击Button时，counter数字没有更新。
状态counter的变化会触发Column的重组，当函数再次执行到mutableStateOf(0)时，会重新创建一个初始值为0的MutableState对象，无法继承前次组合时的状态。
而所谓的“状态”应该能跨越重组长期存在。

remember{}可以帮我们解决这个问题。在Composable首次执行时，remember中计算得到的数据会自动缓存，
当Composable重组再次执行到remember处会返回之前已缓存的数据，无须重新计算。
remember的这一特性非常重要，让一个函数式组件像一个面向对象组件一样持有自己的“成员变量”。