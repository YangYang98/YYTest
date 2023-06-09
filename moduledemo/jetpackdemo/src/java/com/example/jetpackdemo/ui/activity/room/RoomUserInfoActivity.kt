package java.com.example.jetpackdemo.ui.activity.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityRoomUserInfoBinding
import com.example.jetpackdemo.databinding.ListItemUserInfoCrudBinding
import java.com.example.jetpackdemo.data.Student
import java.com.example.jetpackdemo.listener.RoomEventHandleListener
import java.com.example.jetpackdemo.model.RoomStudentViewModel

/**
 * 学生信息CRUD
 */

class RoomUserInfoActivity : AppCompatActivity() {
    private lateinit var adapter: StudentInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRoomUserInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_room_user_info)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentInfoAdapter(getStudents())
        binding.recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[RoomStudentViewModel::class.java]
        viewModel.getAllStudents().observe(this) {
            adapter.students = it
            adapter.notifyDataSetChanged()
        }
        binding.eventHandle = RoomEventHandleListener(this).apply {
            this.viewModel = viewModel
        }
        /*binding.btnQuery.setOnClickListener {
            GetAllStudentTask(studentDao, {
                adapter.students = it
            }, {
                adapter.notifyDataSetChanged()
            }) .execute()
        }*/

        binding.btnClear.setOnClickListener {
            viewModel.deleteAllStudents()
        }

    }

    private fun getStudents(): List<Student> {
        return listOf()
    }

    inner class StudentInfoAdapter constructor(var students: List<Student>) : RecyclerView.Adapter<StudentInfoAdapter.StudentViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val itemBinding: ListItemUserInfoCrudBinding
                = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_user_info_crud, parent, false)
            return StudentViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            holder.binding.student = students[position]
        }

        override fun getItemCount(): Int {
            return students.size
        }

        inner class StudentViewHolder(val binding: ListItemUserInfoCrudBinding) : RecyclerView.ViewHolder(binding.root) {

        }
    }
}