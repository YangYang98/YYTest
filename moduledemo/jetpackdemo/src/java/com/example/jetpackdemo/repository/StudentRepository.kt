package java.com.example.jetpackdemo.repository

import android.content.Context
import androidx.lifecycle.LiveData
import java.com.example.jetpackdemo.data.Student
import java.com.example.jetpackdemo.interfaces.StudentDao
import java.com.example.jetpackdemo.tasks.DeleteAllStudentsTask
import java.com.example.jetpackdemo.tasks.DeleteStudentTask
import java.com.example.jetpackdemo.tasks.InsertStudentTask
import java.com.example.jetpackdemo.tasks.UpdateStudentTask
import java.com.example.jetpackdemo.ui.activity.room.MyDatabase


/**
 * Create by Yang Yang on 2023/4/23
 */
class StudentRepository constructor(val context: Context) {

    val studentDao: StudentDao = MyDatabase.getInstance(context).getStudentDao()

    fun insertStudents(vararg students: Student) {
        InsertStudentTask(studentDao).execute(*students)
    }

    fun updateStudents(vararg students: Student) {
        UpdateStudentTask(studentDao).execute(*students)
    }

    fun deleteStudents(vararg students: Student) {
        DeleteStudentTask(studentDao).execute(*students)
    }

    fun deleteAllStudents() {
        DeleteAllStudentsTask(studentDao).execute()
    }

    fun getAllStudents(): LiveData<List<Student>> {
        return studentDao.getAllStudents2()
    }
}