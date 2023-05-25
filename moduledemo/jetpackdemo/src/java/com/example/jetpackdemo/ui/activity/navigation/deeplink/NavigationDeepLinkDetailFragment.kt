package java.com.example.jetpackdemo.ui.activity.navigation.deeplink

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.R


/**
 * Create by Yang Yang on 2023/4/28
 */
class NavigationDeepLinkDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_deep_link_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val button = view?.findViewById<Button>(R.id.button_detail)

        val args = NavigationDeepLinkHomeFragmentArgs.fromBundle(arguments ?: Bundle())

        if (arguments != null) {
            val params = requireArguments().getString("params")
            button?.text = "ToHomeFragment ${params}"
        }

        //button?.text = "ToHomeFragment ${args.userName}"
        button?.setOnClickListener {

        }

    }
}