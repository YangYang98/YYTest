package java.com.example.jetpackdemo.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import java.com.example.jetpackdemo.data.Student


/**
 * Create by Yang Yang on 2023/4/23
 */
@Dao
interface StudentDao {
    @Insert
    fun insertStudent(vararg students: Student)

    @Delete
    fun deleteStudent(vararg students: Student)

    @Query("DELETE FROM student")
    fun deleteAllStudents()

    @Update
    fun updateStudent(vararg students: Student)

    @Query("SELECT * FROM student")
    fun getAllStudents(): List<Student>

    @Query("SELECT * FROM student")
    fun getAllStudents2(): LiveData<List<Student>>

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id: Int): List<Student>
}