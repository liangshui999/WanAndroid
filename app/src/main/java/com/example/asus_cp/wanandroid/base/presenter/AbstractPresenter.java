package com.example.asus_cp.wanandroid.base.presenter;

import com.example.asus_cp.wanandroid.base.view.BaseView;

public abstract class AbstractPresenter<V extends BaseView> implements BasePresenter<V> {

    private V view;

    @Override
    public void attachView(V v) {
        this.view = v;
    }

    @Override
    public void dettachView() {
        this.view = null;
    }
}
