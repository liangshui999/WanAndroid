package com.example.asus_cp.wanandroid.base.view;

import com.example.asus_cp.wanandroid.base.presenter.BasePresenter;

/**
 * 所有view的基类
 *
 */
public interface BaseView {

    /**
     * 显示正常加载的页面
     */
    void showNormal();

    /**
     * 显示正在加载的页面
     */
    void showLoading();

    /**
     * 显示加载出错的页面
     */
    void showError();

}
