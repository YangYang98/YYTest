package java.com.example.jetpackdemo.tasks

import android.os.AsyncTask
import java.com.example.jetpackdemo.data.Student
import java.com.example.jetpackdemo.interfaces.StudentDao


/**
 * Create by Yang Yang on 2023/4/23
 */
class InsertStudentTask(private val studentDao: StudentDao?) : AsyncTask<Student, Void, Void>() {

    override fun doInBackground(vararg students: Student): Void? {
        studentDao?.insertStudent(*students)
        return null
    }
}

class DeleteStudentTask(private val studentDao: StudentDao?) : AsyncTask<Student, Void, Void>() {
    override fun doInBackground(vararg params: Student): Void? {
        studentDao?.deleteStudent(*params)
        return null
    }
}

class UpdateStudentTask(private val studentDao: StudentDao?) : AsyncTask<Student, Void, Void>() {
    override fun doInBackground(vararg params: Student): Void? {
        studentDao?.updateStudent(*params)
        return null
    }
}

class GetAllStudentTask(private val studentDao: StudentDao?,
                        val backgroundCallback: (students: List<Student>) -> Unit,
                        val callback: () -> Unit) : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg params: Void?): Void? {
        backgroundCallback(studentDao?.getAllStudents() ?: emptyList())
        return null
    }

    override fun onPostExecute(result: Void?) {
        callback.invoke()
    }
}