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

import javax.inject.Inject;

public abstract class BaseFragment<P extends BasePresenter> extends BaseInjectFragment implements BaseView{

    private ViewGroup normal;

    private ViewGroup loading;

    private ViewGroup error;

    private ViewGroup parent;

    @Inject
    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        normal = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
        loading = (ViewGroup) inflater.inflate(R.layout.loading, container, false);
        error = (ViewGroup) inflater.inflate(R.layout.error, container, false);
        parent = (ViewGroup) normal.findViewById(R.id.container);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        parent.addView(loading, layoutParams);
        parent.addView(error, layoutParams);
        showNormal();
        //showLoading();
        presenter.attachView(this);
        return normal;
    }

    @Override
    public void onDestroy() {
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
}
