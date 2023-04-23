package java.com.example.jetpackdemo.ui.activity.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.com.example.jetpackdemo.data.Student;
import java.com.example.jetpackdemo.interfaces.StudentDao;

/**
 * Create by Yang Yang on 2023/4/23
 */
@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase instance;

    private static final String DATABASE_NAME = "my_database.db";

    public static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, MyDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract StudentDao getStudentDao();
}
