package java.com.example.jetpackdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.com.example.jetpackdemo.data.Article
import java.com.example.jetpackdemo.interfaces.ArticleDao


/**
 * Create by Yang Yang on 2023/6/12
 */
@Database(entities = [Article::class], version = 1, exportSchema = true)
abstract class ArticleDataBase : RoomDatabase() {

    companion object {
        private val DATABASE_NAME = "article_db.db"

        private var instance: ArticleDataBase? = null

        @Synchronized
        fun getInstance(context: Context): ArticleDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    ArticleDataBase::class.java,
                    DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }


    abstract fun getArticleDao(): ArticleDao
}