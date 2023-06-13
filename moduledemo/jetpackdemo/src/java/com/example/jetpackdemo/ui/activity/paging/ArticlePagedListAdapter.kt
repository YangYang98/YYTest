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
import java.com.example.jetpackdemo.data.Article


/**
 * Create by Yang Yang on 2023/6/12
 */
class ArticlePagedListAdapter
    : PagedListAdapter<Article, ArticlePagedListAdapter.ArticlePagedListViewHolder>(object : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

})
{

    override fun onBindViewHolder(holder: ArticlePagedListViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticlePagedListViewHolder {
        return ArticlePagedListViewHolder(
            View.inflate(parent.context, R.layout.list_item_paging_positional, null)
        )
    }

    inner class ArticlePagedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: ImageView = itemView.findViewById(R.id.image_avatar)
        val title: TextView = itemView.findViewById(R.id.text_title)
        val desc: TextView = itemView.findViewById(R.id.text_desc)


        fun bindData(data: Article?) {
            if (data != null) {
                //avatar.setImageResource(data.avatar)
                title.text = data.title
                desc.text = data.author

                Picasso.get().load("https://pics4.baidu.com/feed/d439b6003af33a87b2deb4ebffc458345243b530.jpeg@f_auto?token=9466b9e1e1b8ef64da6797bc3905f421")
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground).into(avatar)
            }
        }
    }
}