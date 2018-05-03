package com.example.asus_cp.wanandroid.util;

import android.support.v4.hardware.display.DisplayManagerCompat;
import android.util.DisplayMetrics;

import com.example.asus_cp.wanandroid.app.APP;

public class DensityUtils {

    private static float getDesity(){
        DisplayMetrics displayMetrics = APP.getContext().getResources().getDisplayMetrics();
        return displayMetrics.density;
    }

    public static float dp2px(float dp){
        return dp * getDesity();
    }

    public static float px2dp(float px){
        return px / getDesity();
    }

}
