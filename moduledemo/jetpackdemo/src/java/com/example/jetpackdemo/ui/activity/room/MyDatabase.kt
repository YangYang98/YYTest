package java.com.example.jetpackdemo.ui.activity.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.com.example.jetpackdemo.data.Student
import java.com.example.jetpackdemo.interfaces.StudentDao


/**
 * entities : 包含的表
 * version ：数据库版本
 * exportSchema ：是否导出schema
 *
 * Create by Yang Yang on 2023/4/23
 */

@Database(entities = [Student::class], version = 4, exportSchema = true)
abstract class MyDatabase : RoomDatabase() {

    companion object {
        //private const val DATABASE_NAME = "my_database.db"
        var instance: MyDatabase? = null

        /**
         * 1.allowMainThreadQueries() ：是否允许在主线程中查询
         * TODO Room不允许在主线程中执行操作
         * 如果非要在主线程中执行使用allowMainThreadQueries()
         *
         * 2.fallbackToDestructiveMigration()
         * 在出现升级异常时,重建数据表,同时数据也会丢失。
         */
        @Synchronized
        fun getInstance(context: Context): MyDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "my_database.db")
                    //.allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                    .build()
            }
            return instance!!
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE student ADD COLUMN sex INTEGER NOT NULL DEFAULT 1")
            }
        }

        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE student ADD COLUMN grade INTEGER NOT NULL DEFAULT 1")
            }
        }

        /**
         * 变更：将sex类型由int变为Text
         */
        private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE temp_student (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "name TEXT, " +
                        "age INTEGER NOT NULL, " +
                        "sex TEXT DEFAULT 'M', " +
                        "grade INTEGER NOT NULL DEFAULT 1)")

                database.execSQL("INSERT INTO temp_student (name,age,sex,grade)" +
                        "SELECT name,age,sex,grade FROM student")
                database.execSQL("DROP TABLE student")
                database.execSQL("ALTER TABLE temp_student RENAME TO student")
            }
        }
    }

    abstract fun getStudentDao(): StudentDao
}