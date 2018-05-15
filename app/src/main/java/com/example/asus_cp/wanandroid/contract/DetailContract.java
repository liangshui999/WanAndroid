package com.example.asus_cp.wanandroid.contract;

import com.example.asus_cp.wanandroid.base.presenter.BasePresenter;
import com.example.asus_cp.wanandroid.base.view.BaseView;

public interface DetailContract {

    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<DetailContract.View>{

    }
}
