package com.example.changeskin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hjq.permissions.XXPermissions

class MainActivity2 : BasicActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main2
    }

    override fun initCreate(savedInstanceState: Bundle?) {
        initPermission(ArrayList<String>().apply {
            add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        })
    }

    fun fragmentClick(view: View) {

    }

    fun recyclerClick(view: View) {

    }

    fun customViewClick(view: View) {

    }

    fun dynamicClick(view: View) {

    }

    fun dialogClick(view: View) {

    }

    private fun initPermission(permissions: List<String>) {
        XXPermissions.with(this).permission(ArrayList<String>().apply {
            add(android.Manifest.permission.MANAGE_EXTERNAL_STORAGE)
            //add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }).request { permissions, allGranted ->
            if (!allGranted) {
                Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show()
            }
        }
    }
}