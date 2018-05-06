package com.example.asus_cp.wanandroid.base.presenter;

import com.example.asus_cp.wanandroid.base.view.BaseView;

/**
 * 所有presenter的基类
 * @param <V>
 */
public interface BasePresenter<V extends BaseView> {

    /**
     * 绑定view
     */
    void attachView(V v);

    /**
     * 解绑view
     */
    void dettachView();

    /**
     * 销毁资源
     */
    void destroy();

}
