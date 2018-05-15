package com.example.asus_cp.wanandroid.base.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.presenter.BasePresenter;
import com.example.asus_cp.wanandroid.base.view.BaseView;
import com.example.asus_cp.wanandroid.util.MyLog;

import javax.inject.Inject;

public abstract class BaseActivity<P extends BasePresenter> extends BaseInjectActivity implements BaseView{

    private ViewGroup normal;

    private ViewGroup loading;

    private ViewGroup error;

    private ViewGroup parent;

    @Inject
    LayoutInflater inflater;

    @Inject
    P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        normal = findViewById(R.id.normal);
        parent = (ViewGroup) normal.getParent();
        int index = parent.indexOfChild(normal);//获取normal的索引
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        loading = (ViewGroup) inflater.inflate(R.layout.loading, null);
        error = (ViewGroup) inflater.inflate(R.layout.error, null);
        parent.addView(loading,  index + 1, layoutParams);
        parent.addView(error, index + 2, layoutParams);
        showNormal();
        //showLoading();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettachView();
    }

    @Override
    public void showNormal() {
        normal.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
        normal.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        error.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        normal.setVisibility(View.GONE);
    }

    protected void log(String msg){
        MyLog.d(TAG, msg);
    }

    protected void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
