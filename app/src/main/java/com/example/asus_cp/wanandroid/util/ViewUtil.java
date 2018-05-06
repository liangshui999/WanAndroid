package com.example.asus_cp.wanandroid.util;

import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.AbsListView;

public class ViewUtil {

    public static boolean canChildScrollDown(View targetView) {
        if (Build.VERSION.SDK_INT >= 14) {
            return targetView.canScrollVertically(-1);
        } else if (targetView instanceof AbsListView) {
            AbsListView absListView = (AbsListView)targetView;
            return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
        } else {
            return targetView.canScrollVertically(-1) || targetView.getScrollY() > 0;
        }
    }

    public static boolean canChildScrollUp(View targetView) {
        if (Build.VERSION.SDK_INT >= 14) {
            return targetView.canScrollVertically(1);
        } else if (targetView instanceof AbsListView) {
            AbsListView absListView = (AbsListView)targetView;
            return absListView.getChildCount() > 0 && (absListView.getLastVisiblePosition() < absListView.getChildCount() - 1 || absListView.getChildAt(absListView.getChildCount() - 1).getBottom() > absListView.getPaddingBottom());
        } else {
            return targetView.canScrollVertically(1) || targetView.getScrollY() < 0;
        }
    }


}
