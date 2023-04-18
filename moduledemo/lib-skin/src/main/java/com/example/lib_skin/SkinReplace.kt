package com.example.lib_skin

import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.lib_skin.data.SkinAttr


/**
 * Create by Yang Yang on 2023/4/13
 */
enum class SkinReplace constructor(val value: String) {
    ANDROID_SRC("src") {
        override fun loadResource(view: View, attr: SkinAttr) {
            if (view is ImageView) {
                try {
                    if ("drawable" == attr.attrType) {
                        view.setImageDrawable(SkinManager.getInstance().getDrawable(attr.attrValue))
                    } else if ("color" == attr.attrType) {
                        view.setBackgroundColor(SkinManager.getInstance().getColor(attr.attrValue))
                    } else {
                        view.setImageResource(SkinManager.getInstance().getIdentifier(attr.attrValue, attr.attrType))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    printLog(view, attr)
                }
            }
        }
    },
    ANDROID_TEXT("text") {
        override fun loadResource(view: View, attr: SkinAttr) {
            if (view is TextView) {
                try {
                    view.text = SkinManager.getInstance().getString(attr.attrValue)
                } catch (e: Exception) {
                    e.printStackTrace()
                    printLog(view, attr)
                }
            }
        }
    },
    ANDROID_TEXT_COLOR("textColor") {
        override fun loadResource(view: View, attr: SkinAttr) {
            if (view is TextView) {
                try {
                    if ("color" == attr.attrType) {
                        view.setTextColor(SkinManager.getInstance().getColor(attr.attrValue))
                    } else if ("drawable" == attr.attrType) {
                        view.setTextColor(SkinManager.getInstance().getIdentifier(attr.attrValue, attr.attrType))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    printLog(view, attr)
                }
            }
        }
    },
    ANDROID_TEXT_SIZE("textSize") {
        override fun loadResource(view: View, attr: SkinAttr) {
            if (view is TextView) {
                try {
                    view.setTextSize(TypedValue.COMPLEX_UNIT_PX, SkinManager.getInstance().getFontSize(attr.attrValue))
                } catch (e: Exception) {
                    e.printStackTrace()
                    printLog(view, attr)
                }
            }
        }
    },
    ANDROID_BACKGROUND("background") {
        override fun loadResource(view: View, attr: SkinAttr) {
            try {
                if ("color" == attr.attrType) {
                    view.setBackgroundColor(SkinManager.getInstance().getColor(attr.attrValue))
                } else if ("drawable" == attr.attrType) {
                    view.background = SkinManager.getInstance().getDrawable(attr.attrValue)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                printLog(view, attr)
            }
        }
    },
    CUSTOM_SKIN_VIEW_BACKGROUND("skin_background") {
        override fun loadResource(view: View, attr: SkinAttr) {
            val color = SkinManager.getInstance().getColor(attr.value)
            setCustomAttr(view, "setBackGround", SkinReflectionMethod(Int::class.java, color))
        }
    },
    CUSTOM_SKIN_VIEW_FONT_COLOR("skin_font_color") {
        override fun loadResource(view: View, attr: SkinAttr) {
            val color = SkinManager.getInstance().getColor(attr.value)
            setCustomAttr(view, "setTextColor", SkinReflectionMethod(Int::class.java, color))
        }
    },
    CUSTOM_SKIN_VIEW_FONT_SIZE("skin_font_size") {
        override fun loadResource(view: View, attr: SkinAttr) {
            val fontSize = SkinManager.getInstance().getFontSize(attr.value)
            setCustomAttr(view, "setTextSize", SkinReflectionMethod(Float::class.java, fontSize))
        }
    },
    CUSTOM_SKIN_VIEW_FONT_TEXT("skin_text") {
        override fun loadResource(view: View, attr: SkinAttr) {
            val text = SkinManager.getInstance().getString(attr.value)
            setCustomAttr(view, "setText", SkinReflectionMethod(String::class.java, text))
        }
    };

    abstract fun loadResource(view: View, attr: SkinAttr)

    fun printLog(view: View, attr: SkinAttr) {
        SkinLog.e("换肤失败${this.value} errorCode:${SkinConfig.SKIN_ERROR_9}:name:${view.javaClass.name}:attr:${attr}")
    }

    /**
     * @param view: 需要反射的对象
     * @param methodName: 反射的方法名字
     * @param SkinReflectionMethod: 反射具体数据 [类型和参数]
     */
    fun setCustomAttr(view: View, methodName: String, vararg data: SkinReflectionMethod) {
        try {
            /*val cls: Array<Class<*>> = arrayOfNulls(data.size)
            val objects = arrayOfNulls<Any>(data.size)

            view.javaClass.getDeclaredMethod(methodName, cls)*/
            val cls = arrayOfNulls<Class<*>>(data.size)
            val objects = arrayOfNulls<Any>(data.size)
            for (i in data.indices) {
                cls[i] = data[i].cls
                objects[i] = data[i].obj
            }
            val method = view.javaClass.getDeclaredMethod(methodName, *cls)
            method.isAccessible = true
            method.invoke(view, *objects)
        } catch (e: Exception) {
            e.printStackTrace()
            SkinLog.e("反射失败 meg:${e.message} errorCode:${SkinConfig.SKIN_ERROR_7}")
        }
    }
}