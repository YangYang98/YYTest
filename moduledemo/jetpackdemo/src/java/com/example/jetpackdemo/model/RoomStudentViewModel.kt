package java.com.example.jetpackdemo.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.com.example.jetpackdemo.data.Student
import java.com.example.jetpackdemo.repository.StudentRepository


/**
 * Create by Yang Yang on 2023/4/23
 */
class RoomStudentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = StudentRepository(application)

    fun insertStudents(vararg students: Student) {
        repository.insertStudents(*students)
    }

    fun updateStudents(vararg students: Student) {
        repository.updateStudents(*students)
    }

    fun deleteStudents(vararg students: Student) {
        repository.deleteStudents(*students)
    }

    fun deleteAllStudents() {
        repository.deleteAllStudents()
    }

    fun getAllStudents(): LiveData<List<Student>> {
        return repository.getAllStudents()
    }
}