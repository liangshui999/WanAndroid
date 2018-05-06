package com.example.asus_cp.wanandroid.contract.main;

import com.example.asus_cp.wanandroid.base.presenter.BasePresenter;
import com.example.asus_cp.wanandroid.base.view.BaseView;
import com.example.asus_cp.wanandroid.bean.main.MainPagerBannerBean;
import com.example.asus_cp.wanandroid.bean.main.MainPagerListBean;
import com.example.asus_cp.wanandroid.callback.OnGetHttpResultListener;
import com.example.asus_cp.wanandroid.constant.LoadDataMode;
import com.example.asus_cp.wanandroid.net.http.bean.Response;

import java.util.List;

public interface MainPagerContract {

    interface View extends BaseView{

        /**
         * 更新列表
         */
        void firstLoadList(List<MainPagerListBean.DataBean.DatasBean> datas);

        /**
         * 更新banner
         */
        void updateBanner(List<MainPagerBannerBean.DataBean> datas);

        /**
         * 下拉刷新列表
         */
        void refreshList(List<MainPagerListBean.DataBean.DatasBean> datas);

        /**
         * 上拉加载列表
         */
        void loadMoreList(List<MainPagerListBean.DataBean.DatasBean> datas);
    }

    interface Presenter extends BasePresenter<MainPagerContract.View>{

        /**
         * 获取首页的列表数据
         */
        void loadListData(LoadDataMode mode);

        /**
         * 获取首页的广告数据
         */
        void loadBannerData();

        /**
         * 页数加1
         */
        public void addPage();

        /**
         * 页数重置为0
         */
        public void resetPage();
    }
}
