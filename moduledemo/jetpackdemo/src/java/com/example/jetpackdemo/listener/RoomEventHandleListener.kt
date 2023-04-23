package java.com.example.jetpackdemo.listener

import android.content.Context
import android.content.Intent
import android.view.View
import java.com.example.jetpackdemo.data.Student
import java.com.example.jetpackdemo.interfaces.StudentDao
import java.com.example.jetpackdemo.tasks.DeleteStudentTask
import java.com.example.jetpackdemo.tasks.InsertStudentTask
import java.com.example.jetpackdemo.tasks.UpdateStudentTask
import java.com.example.jetpackdemo.ui.activity.room.RoomUserInfoActivity


/**
 * Create by Yang Yang on 2023/4/23
 */
class RoomEventHandleListener constructor(val context: Context) {

    var studentDao: StudentDao? = null

    fun btnUserInfoOnCLick(view: View) {
        context.startActivity(Intent(context, RoomUserInfoActivity::class.java))
    }

    fun btnInsertStudentOnClick(view: View) {
        val s1 = Student("yy01", 22)
        val s2 = Student("yy02", 22)
        val s3 = Student("yy03", 22)
        InsertStudentTask(studentDao).execute(s1, s2, s3)
    }

    fun btnDeleteStudentOnClick(view: View) {
        DeleteStudentTask(studentDao).execute(Student(8))
    }

    fun btnUpdateStudentOnClick(view: View) {
        val s3 = Student(3, "yy03", 21)
        UpdateStudentTask(studentDao).execute(s3)
    }
}