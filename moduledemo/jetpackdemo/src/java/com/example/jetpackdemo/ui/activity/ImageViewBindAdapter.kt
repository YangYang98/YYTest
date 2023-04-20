package java.com.example.jetpackdemo.ui.activity

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.jetpackdemo.R
import com.squareup.picasso.Picasso


/**
 * Create by Yang Yang on 2023/4/20
 */
class ImageViewBindAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun setImage(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground).into(imageView)
            } else {
                imageView.setBackgroundColor(Color.BLUE)
            }
        }

        @JvmStatic
        @BindingAdapter("image")
        fun setImage(imageView: ImageView, resId: Int) {
            imageView.setImageResource(resId)
        }

        @JvmStatic
        @BindingAdapter(value = ["image", "defaultImageRes"], requireAll = false)
        fun setImage(imageView: ImageView, url: String?, resId: Int) {
            if (!url.isNullOrEmpty()) {
                Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground).into(imageView)
            } else {
                imageView.setImageResource(resId)
            }
        }
    }
}