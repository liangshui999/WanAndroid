package com.example.asus_cp.wanandroid.util;

import android.util.Log;

public class MyLog {

    private static final int DEBUG = 1;

    private static final int INFO = 2;

    private static final int WRAN = 3;

    private static final int ERROR = 4;

    private static final int CONTROL = 0;

    public static void d(String tag, String msg){
        if(CONTROL <= DEBUG){
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg){
        if(CONTROL <= ERROR){
            Log.e(tag, msg);
        }
    }

}
