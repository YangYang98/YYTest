package java.com.example.jetpackdemo.ui.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.jetpackdemo.R


/**
 * Create by Yang Yang on 2023/4/28
 */
class NavigationHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val button = view?.findViewById<Button>(R.id.button_home)
        button?.setOnClickListener {
            /*val args = Bundle().apply {
                putString("userName", "yangyang")
            }*/

            val args = NavigationHomeFragmentArgs.Builder()
                //.setUserName("yang-yang")
                .build().toBundle()
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_homeFragment_to_detailFragment, args)
        }

    }
}