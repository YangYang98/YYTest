package java.com.example.jetpackdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ListItemRecyclerViewBindingBinding
import java.com.example.jetpackdemo.data.User2


/**
 * Create by Yang Yang on 2023/4/23
 */
class RecyclerViewAdapter constructor(var users: List<User2>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ListItemRecyclerViewBindingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_recycler_view_binding, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.user = users[position]
    }

    inner class ViewHolder(var itemBinding: ListItemRecyclerViewBindingBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    }
}