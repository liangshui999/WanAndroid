package com.example.asus_cp.wanandroid.base.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * 该类主要负责处理依赖注入相关的内容
 */
public abstract class BaseInjectActivity extends DaggerAppCompatActivity {

    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    /**
     * 获取内容区的布局view，子类必须实现该方法
     */
    protected abstract int getContentLayoutId();
}
