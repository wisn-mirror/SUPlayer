package com.wisn.suvideo.helper;

import android.util.Log;

import com.wisn.suvideo.manager.VideoViewManager;

/**
 * Created by Wisn on 2019-07-23 19:21.
 */
public class L {
    private static final String TAG = "SUPlayer";

    private static boolean isDebug = VideoViewManager.getConfig().mIsEnableLog;


    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void w(Throwable e) {
        if (isDebug) {
            Log.w(TAG, e);
        }
    }

    public static void setDebug(boolean isDebug) {
        L.isDebug = isDebug;
    }

}
