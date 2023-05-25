package java.com.example.jetpackdemo.ui.activity.navigation.deeplink

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = NavigationDeepLinkHomeFragmentArgs.fromBundle(arguments ?: Bundle())

        val button = view?.findViewById<Button>(R.id.button_detail)
        button?.text = "ToHomeFragment ${args.userName}"
        button?.setOnClickListener {

        }

    }
}