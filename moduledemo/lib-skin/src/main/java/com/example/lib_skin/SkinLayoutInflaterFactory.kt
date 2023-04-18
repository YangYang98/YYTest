package com.example.lib_skin

import android.app.Activity
import android.content.Context
import android.util.ArrayMap
import android.util.AttributeSet
import android.view.View
import com.example.lib_skin.data.SkinAttr
import com.example.lib_skin.data.SkinView
import java.util.*


/**
 * Create by Yang Yang on 2023/4/13
 */
class SkinLayoutInflaterFactory constructor(private val activity: Activity): Observer, SkinCreateView(activity) {

    private val skinReplaceData = ArrayList<String>()

    private val skinViewCache = ArrayMap<Activity, List<SkinView>>()
    private val skinViews = ArrayList<SkinView>()

    init {
        SkinReplace.values().forEach {
            skinReplaceData.add(it.value)
        }
    }

    fun removeKey(activity: Activity) {
        if (skinViewCache.contains(activity)) {
            skinViewCache.remove(activity)
        }
    }

    override fun viewAttrs(context: Context, view: View, name: String, attrs: AttributeSet) {
        if (view.visibility == View.GONE) {
            return
        }

        val skinAttrs = ArrayList<SkinAttr>()

        for (i in 0 until attrs.attributeCount) {
            val attrName = attrs.getAttributeName(i)
            val attrValue = attrs.getAttributeValue(i)

            // 如果属性不存在，那么就直接执行下一次
            if (!skinReplaceData.contains(attrName)) {
                continue
            }

            SkinReplace.values().forEach {
                if (it.value == attrName) {
                    val skinAttr = SkinAttr(context, attrName, attrValue)
                    if (!skinAttrs.contains(skinAttr) && skinAttr.isAddValue()) {
                        skinAttrs.add(skinAttr)
                    }
                }
            }
        }

        val skinView = SkinView(view, skinAttrs)
        if (!skinViews.contains(skinView) && skinAttrs.size > 0) {
            skinViews.add(skinView)
            skinViewCache[activity] = skinViews
        }
    }

    override fun update(o: Observable?, arg: Any?) {

        var updateActivity: Activity = activity

        if (arg != null && arg is Activity) {
            updateActivity = arg
        }

        val skinViews = skinViewCache[updateActivity] ?: return

        skinViews.forEach { skinView ->
            skinView.skinAttrs.forEach { skinAttr ->
                SkinReplace.values().forEach { skinReplace ->
                    if (skinReplace.value == skinAttr.key) {
                        skinReplace.loadResource(skinView.view, skinAttr)
                    }
                }
            }
        }
    }
}