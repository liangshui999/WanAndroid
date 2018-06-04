package com.example.asus_cp.wanandroid.itemDeraction;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.app.APP;
import com.example.asus_cp.wanandroid.util.DensityUtils;

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private int decorationSize;

    private Paint paint;

    public MyItemDecoration(){
        Context context = APP.getContext();
        decorationSize = (int) DensityUtils.dp2px(10f);
        paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.decoration));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int count = parent.getChildCount();
        c.drawRect(0, 0, parent.getWidth(), decorationSize, paint);
        for(int i = 0; i < count; i++){
            View v = parent.getChildAt(i);
            c.drawRect(0, v.getBottom(), v.getRight() + decorationSize,
                    v.getBottom() + decorationSize, paint);//画下边
            c.drawRect(0, v.getTop() - decorationSize, decorationSize, v.getBottom(), paint);//画左边
            c.drawRect(v.getRight(), v.getTop() - decorationSize, v.getRight() + decorationSize, v.getBottom(), paint);//画右边
        }
    }

    /**
     * 不复写这个，只有onDraw（）的时候，分割线会被item所覆盖
     * 这个设置的是留一些可以用于onDraw的空白地方
     * 这儿是上下左右都留了画的地方
     * 类似于margin的用法
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
       outRect.set(decorationSize, decorationSize, decorationSize, decorationSize);
    }
}
