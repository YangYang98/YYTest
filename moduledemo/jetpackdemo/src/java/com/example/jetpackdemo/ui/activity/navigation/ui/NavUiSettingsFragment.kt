package java.com.example.jetpackdemo.ui.activity.navigation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.jetpackdemo.R


/**
 * Create by Yang Yang on 2023/5/18
 */
class NavUiSettingsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Setting页不显示菜单，确保onCreateOptionsMenu执行
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_nav_ui_settings, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Setting页不显示菜单
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}