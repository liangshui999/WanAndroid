package com.example.asus_cp.wanandroid.base.presenter;

import com.example.asus_cp.wanandroid.base.view.BaseView;
import com.example.asus_cp.wanandroid.bean.main.MainPagerBannerBean;

public abstract class AbstractPresenter<V extends BaseView> implements BasePresenter<V> {

    protected String TAG = this.getClass().getSimpleName();

    protected V view;

    @Override
    public void attachView(V v) {
        this.view = v;
    }

    @Override
    public void dettachView() {
        this.view = null;
    }


}
