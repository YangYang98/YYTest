package java.com.example.jetpackdemo.ui.activity.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.com.example.jetpackdemo.data.Student
import java.com.example.jetpackdemo.interfaces.StudentDao


/**
 * entities : 包含的表
 * version ：数据库版本
 * exportSchema ：是否导出schema
 *
 * Create by Yang Yang on 2023/4/23
 */

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class MyDatabase2 : RoomDatabase() {

    companion object {
        //private const val DATABASE_NAME = "my_database.db"
        var instance: MyDatabase? = null

        /**
         * allowMainThreadQueries() ：是否允许在主线程中查询
         * TODO Room不允许在主线程中执行操作
         * 如果非要在主线程中执行使用allowMainThreadQueries()
         */
        @Synchronized
        fun getInstance(context: Context): MyDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "my_database.db")
                    //.allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

    abstract fun getStudentDao(): StudentDao
}