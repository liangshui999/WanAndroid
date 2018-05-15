package com.example.asus_cp.wanandroid.presenter;

import com.example.asus_cp.wanandroid.base.presenter.AbstractPresenter;
import com.example.asus_cp.wanandroid.contract.DetailContract;
import com.example.asus_cp.wanandroid.view.DetailActivity;

import javax.inject.Inject;

public class DetailPresenter extends AbstractPresenter<DetailContract.View> implements DetailContract.Presenter {

    @Inject
    public DetailPresenter(){

    }

    @Override
    public void destroy() {

    }
}
