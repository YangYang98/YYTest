package java.com.example.jetpackdemo.ui.activity.paging

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackdemo.R
import com.squareup.picasso.Picasso


/**
 * Create by Yang Yang on 2023/6/5
 */
class PositionalPagedListAdapter()
    : PagedListAdapter<Positional, PositionalPagedListAdapter.PositionalPagedListViewHolder>(object : DiffUtil.ItemCallback<Positional>() {
    override fun areItemsTheSame(oldItem: Positional, newItem: Positional): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Positional, newItem: Positional): Boolean {
        return oldItem.desc == newItem.desc
    }

})
{

    override fun onBindViewHolder(holder: PositionalPagedListViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PositionalPagedListViewHolder {
        return PositionalPagedListViewHolder(
            View.inflate(parent.context, R.layout.list_item_paging_positional, null)
        )
    }

    inner class PositionalPagedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: ImageView = itemView.findViewById(R.id.image_avatar)
        val title: TextView = itemView.findViewById(R.id.text_title)
        val desc: TextView = itemView.findViewById(R.id.text_desc)


        fun bindData(data: Positional?) {
            if (data != null) {
                //avatar.setImageResource(data.avatar)
                title.text = data.title
                desc.text = data.desc

                Picasso.get().load("https://pics4.baidu.com/feed/d439b6003af33a87b2deb4ebffc458345243b530.jpeg@f_auto?token=9466b9e1e1b8ef64da6797bc3905f421")
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground).into(avatar)
            }
        }
    }
}