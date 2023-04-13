package com.example.changeskin

import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.permissionx.guolindev.PermissionX
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    companion object {
        val PATH = Environment.getExternalStorageDirectory().absolutePath + File.separator + "themeRes-debug.apk"
    }

    @SuppressLint("DiscouragedPrivateApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val file = File(PATH)
        if (!file.isFile) {
            showDialog("themeRes-debug.apk不存在")
        }

        initPermission(ArrayList<String>().apply {
            add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        })

        imageView = findViewById<ImageView>(R.id.image_view)
        textView = findViewById<TextView>(R.id.text_view)

        Environment.getExternalStorageDirectory()

        try {
            val assetManager = AssetManager::class.java.newInstance()
            val method = AssetManager::class.java.getDeclaredMethod("addAssetPath", String::class.java)
            val path = applicationContext.packageResourcePath
            Log.e("BBBBBBB", path)

            method.invoke(assetManager, path)
            val resources = Resources(assetManager, createDisplayMetrics(), createConfiguration())

            val drawable = resources.getDrawable(R.drawable.ic_launcher_background, null)

            imageView.setImageDrawable(drawable)

        } catch (e: Exception) {
            e.printStackTrace()
            showDialog("找不到shark ${e.message}")
        }
    }

    private fun createDisplayMetrics(): DisplayMetrics {
        return resources.displayMetrics
    }

    private fun createConfiguration(): Configuration {
        return resources.configuration
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun btnClick(view: View) {
        try {
            val assetManager = AssetManager::class.java.newInstance()
            val method = AssetManager::class.java.getDeclaredMethod("addAssetPath", String::class.java)
            method.isAccessible = true
            Log.e("BBBBBBB", PATH)
            method.invoke(assetManager, PATH)
            val resources = Resources(assetManager, createDisplayMetrics(), createConfiguration())

            val drawableId: Int = resources.getIdentifier("shark", "drawable", "com.example.themeres")
            val stringId: Int = resources.getIdentifier("hello_skin", "string", "com.example.themeres")
            val colorId: Int = resources.getIdentifier("global_background", "color", "com.example.themeres")

            imageView.setImageDrawable(resources.getDrawable(drawableId, null))
            textView.text = resources.getString(stringId)
            textView.setBackgroundColor(resources.getColor(colorId, null))

        } catch (e: Exception) {
            e.printStackTrace()
            showDialog("btnClick找不到shark ${e.message}")
        }
    }

    fun resetClick(view: View) {
        try {
            val assetManager = AssetManager::class.java.newInstance()
            val method = AssetManager::class.java.getDeclaredMethod("addAssetPath", String::class.java)
            val path = applicationContext.packageResourcePath
            Log.e("BBBBBBB", path)

            method.invoke(assetManager, path)
            val resources = Resources(assetManager, createDisplayMetrics(), createConfiguration())

            val drawableId = resources.getIdentifier("shark", "drawable", "com.example.changeskin")

            imageView.setImageDrawable(resources.getDrawable(drawableId, null))

        } catch (e: Exception) {
            e.printStackTrace()
            showDialog("resetClick找不到shark ${e.message}")
        }
    }

    private fun initPermission(permissions: List<String>) {
        PermissionX.init(this).permissions(
            permissions
        ).request { allGranted, _, _ ->
            if (!allGranted) {
                Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show()
            }
        }

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val uri = Uri.parse("package:${BuildConfig.APPLICATION_ID}")
            startActivity(
                Intent(
                    Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                    uri
                )
            )
        }*/
    }

    private fun showDialog(text: String) {
        AlertDialog.Builder(this).setTitle("提示").setMessage(text).show()
    }
}