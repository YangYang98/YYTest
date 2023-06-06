package java.com.example.jetpackdemo.ui.activity.paging


/**
 * Create by Yang Yang on 2023/6/5
 */
class PositionalResponse {
    var data: Positionals? = null
    var errorCode: Int = 0
    var errorMsg: String? = null
}

class Positionals {


    var size: Int = 0
    var curPage: Int = 0
    var total: Int = 0
    var datas: List<Positional>? = null


}

class Positional {

    var title: String? = null
    var envelopePic: String? = null
    var desc: String? = null
}