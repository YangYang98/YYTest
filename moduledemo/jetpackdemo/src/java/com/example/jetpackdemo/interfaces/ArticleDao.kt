package java.com.example.jetpackdemo.interfaces

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.com.example.jetpackdemo.data.Article


/**
 * Create by Yang Yang on 2023/6/12
 */
@Dao
interface ArticleDao {
    @Insert
    fun insertArticles(articles: List<Article>)

    @Query("DELETE FROM article")
    fun clear()

    @Query("SELECT * FROM article")
    fun getArticleList(): DataSource.Factory<Int, Article>
}