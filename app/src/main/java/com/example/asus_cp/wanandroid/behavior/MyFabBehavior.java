package com.example.asus_cp.wanandroid.behavior;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;

import com.example.asus_cp.wanandroid.util.DensityUtils;
import com.example.asus_cp.wanandroid.util.MyLog;

import static android.animation.ValueAnimator.REVERSE;

/**
 * 自定义的一个简单的Behavior，
 * 属于：某个view监听CoordinatorLayout里的滑动状态
 * 在自定义Behavior的时候，我们需要关心的两组四个方法，为什么分为两组呢？看一下下面两种情况
 * 某个view监听另一个view的状态变化，例如大小、位置、显示状态等
 * 某个view监听CoordinatorLayout里的滑动状态
 * 对于第一种情况，我们关心的是：
 * layoutDependsOn和onDependentViewChanged方法，
 * 对于第二种情况，我们关心的是：
 * onStartNestedScroll和onNestedPreScroll方法。
 */
public class MyFabBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    private static final String TAG = "MyFabBehavior";

    private boolean isShow = true;

    /**
     * 如果不写这个构造函数，会报Could not inflate Behavior subclass错误
     */
    public MyFabBehavior(Context context, AttributeSet attrs){

    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        MyLog.d(TAG, "onStartNestedScroll........axes = " + axes);
        int temp = axes & ViewCompat.SCROLL_AXIS_VERTICAL;
        boolean result = temp != 0;
        return result;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        MyLog.d(TAG, "onNestedPreScroll........target = " + target);
        //super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        MyLog.d(TAG, "dy = " + dy);
        consumed[1] = (int) DensityUtils.dp2px(0);
        if(Math.abs(dy) < ViewConfiguration.getTouchSlop()){
            return;
        }
        if(dy > 0){ //向上滚动
            if(isShow){
                //隐藏
                ObjectAnimator animator = ObjectAnimator.ofFloat(child, "alpha", 1, 0);
                animator.setDuration(1000);
                animator.start();
                isShow = false;
            }
        }else{ //向下滚动
            if(! isShow){
                //显示
                ObjectAnimator animator = ObjectAnimator.ofFloat(child, "alpha", 0, 1);
                animator.setDuration(1000);
                animator.start();
                isShow = true;
            }
        }
    }
}
