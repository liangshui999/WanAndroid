package com.example.asus_cp.wanandroid.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.presenter.BasePresenter;
import com.example.asus_cp.wanandroid.base.view.BaseView;
import com.example.asus_cp.wanandroid.util.MyLog;

import javax.inject.Inject;

public abstract class BaseFragment<P extends BasePresenter> extends BaseInjectFragment implements BaseView{

    protected String TAG = this.getClass().getSimpleName();

    private ViewGroup normal;

    private ViewGroup loading;

    private ViewGroup error;

    private ViewGroup parent;

    @Inject
    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
        loading = (ViewGroup) inflater.inflate(R.layout.loading, container, false);
        error = (ViewGroup) inflater.inflate(R.layout.error, container, false);
        parent = viewGroup.findViewById(R.id.container);
        normal = viewGroup.findViewById(R.id.normal);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        parent.addView(loading, layoutParams);
        parent.addView(error, layoutParams);
        showNormal();
        presenter.attachView(this);
        return viewGroup;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dettachView();
    }

    @Override
    public void showNormal() {
        MyLog.d(TAG, "showNormal.........");
        normal.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        MyLog.d(TAG, "showLoading.........");
        loading.setVisibility(View.VISIBLE);
        normal.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        MyLog.d(TAG, "showError..........");
        error.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        normal.setVisibility(View.GONE);
    }
}
