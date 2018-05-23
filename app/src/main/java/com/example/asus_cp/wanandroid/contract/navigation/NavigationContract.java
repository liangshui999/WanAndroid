package com.example.asus_cp.wanandroid.contract.navigation;

import com.example.asus_cp.wanandroid.base.presenter.BasePresenter;
import com.example.asus_cp.wanandroid.base.view.BaseView;
import com.example.asus_cp.wanandroid.bean.navigation.NavigationBean;

import java.util.List;

public interface NavigationContract {

    interface View extends BaseView{

        /**
         * 刷新列表
         */
        void refreshList(List<NavigationBean.DataBean> dataBeans);

    }


    interface Presenter extends BasePresenter<View>{

        /**
         * 加载导航数据
         */
        void loadNavigationData();
    }

}
