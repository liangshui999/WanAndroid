package com.example.asus_cp.wanandroid.callback;

import android.view.View;

public interface OnItemClickListener {

    /**
     * 小项被点击
     * @param view 被点击的view
     * @param position 被点击的位置
     */
    void onItemClick(View view, int position);
}
