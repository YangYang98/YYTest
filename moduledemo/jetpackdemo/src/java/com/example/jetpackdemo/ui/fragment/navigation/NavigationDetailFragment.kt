package java.com.example.jetpackdemo.ui.fragment.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.jetpackdemo.R

/**
 * Create by Yang Yang on 2023/4/28
 */
class NavigationDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //val username = arguments?.getString("userName")

        val args = NavigationHomeFragmentArgs.fromBundle(arguments ?: Bundle())

        val button = view?.findViewById<Button>(R.id.button_detail)?.apply {
            text = "ToHomeFragment ${args.userName}"
        }
        button?.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_detailFragment_to_homeFragment)
        }

    }
}