package com.example.changeskin

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.lib_skin.SkinManager


/**
 * Create by Yang Yang on 2023/4/13
 */
abstract class BasicActivity : AppCompatActivity() {

    private lateinit var info: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        info = findViewById(R.id.tv_info)
        findViewById<TextView>(R.id.bt_re_skin).setOnClickListener {
            SkinManager.getInstance().loadSkin(Config.PATH, this)
            setInfoState()
        }
        findViewById<Button>(R.id.bt_reset).setOnClickListener {
            SkinManager.getInstance().reset()
            setInfoState()
        }

        val rootView = findViewById<FrameLayout>(R.id.root_view)

        val layoutId = getLayoutId()
        if (layoutId != 0) {
            LayoutInflater.from(this).inflate(layoutId, rootView)
        }
        initCreate(savedInstanceState)
    }

    private fun setInfoState() {
        info.text = String.format("当前皮肤状态:%s", SkinManager.getInstance().skinState)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initCreate(savedInstanceState: Bundle?)
}