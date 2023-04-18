package com.example.lib_skin;

import android.util.Log;

/**
 * Create by Yang Yang on 2023/4/14
 */
public class SkinLog {
    public static boolean IS_DEBUG = true;

    public static void i(Object key, Object value) {
        if (IS_DEBUG) {
            Log.i(key.toString(), value.toString());
        }
    }

    public static void e(Object value) {
//        if (IS_DEBUG) {
        Log.e("", "******************************SKIN-ERROR-START********************************");
        Log.e("", "******************************************************************************");

        Log.e("出错了", value.toString());

        Log.e("", "******************************************************************************");
        Log.e("", "*******************************SKIN-ERROR-END*********************************");

//        }
    }
}
