package com.example.asus_cp.wanandroid.contract.main;

import com.example.asus_cp.wanandroid.base.presenter.BasePresenter;
import com.example.asus_cp.wanandroid.base.view.BaseView;

public interface MainPagerContract {

    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<MainPagerContract.View>{

    }
}
