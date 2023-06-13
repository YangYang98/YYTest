package java.com.example.jetpackdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Create by Yang Yang on 2023/6/12
 */

class ArticleResponse {
    var data: ArticleWrapper? = null
    var errorCode: Int = 0
    var errorMsg: String? = null
}

class ArticleWrapper {
    var curPage: Int = 0
    var datas: List<Article>? = null
    var offset: Int = 0
    var over: Boolean = false
    var pageCount: Int = 0
    var size: Int = 0
    var total: Int = 0
}

@Entity
class Article {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "no", typeAffinity = ColumnInfo.INTEGER)
    var NO: Int = 0

    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var id: Long = 0
    @ColumnInfo(name = "title", typeAffinity = ColumnInfo.TEXT)
    var title: String? = null
    @ColumnInfo(name = "author", typeAffinity = ColumnInfo.TEXT)
    var author: String? = null
    override fun toString(): String {
        return "Article(NO=$NO, id=$id, title=$title, author=$author)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Article

        if (NO != other.NO) return false
        if (id != other.id) return false
        if (title != other.title) return false
        return author == other.author
    }

    override fun hashCode(): Int {
        var result = NO
        result = 31 * result + id.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        return result
    }


}