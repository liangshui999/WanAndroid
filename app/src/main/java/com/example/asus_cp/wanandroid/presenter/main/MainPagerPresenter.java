package com.example.asus_cp.wanandroid.presenter.main;

import com.example.asus_cp.wanandroid.base.presenter.AbstractPresenter;
import com.example.asus_cp.wanandroid.bean.main.MainPagerBannerBean;
import com.example.asus_cp.wanandroid.bean.main.MainPagerListBean;
import com.example.asus_cp.wanandroid.callback.OnGetHttpResultListener;
import com.example.asus_cp.wanandroid.constant.LoadDataMode;
import com.example.asus_cp.wanandroid.contract.main.MainPagerContract;
import com.example.asus_cp.wanandroid.di.scope.ActivityScope;
import com.example.asus_cp.wanandroid.net.http.datamanager.MainPagerDataManager;

import javax.inject.Inject;

@ActivityScope
public class MainPagerPresenter extends AbstractPresenter<MainPagerContract.View> implements MainPagerContract.Presenter {

    @Inject
    MainPagerDataManager dataManager;

    private int page;

    @Inject
    public MainPagerPresenter() {

    }

    @Override
    public void loadListData(LoadDataMode mode) {
        dataManager.loadListData(page, new OnGetHttpResultListener<MainPagerListBean>() {
            @Override
            public void onGetResult(MainPagerListBean result) {
                switch (mode){
                    case FIRST_LOAD:
                        view.firstLoadList(result.getData().getDatas());
                        break;
                    case PULL_TO_REFRESH:
                        view.refreshList(result.getData().getDatas());
                        break;
                    case LOAD_MORE:
                        view.loadMoreList(result.getData().getDatas());
                        break;
                }

            }

            @Override
            public void onError(Throwable e) {
                view.showError();
                e.printStackTrace();
            }
        });
    }

    @Override
    public void loadBannerData() {
        view.showLoading();
        dataManager.loadBannerData(new OnGetHttpResultListener<MainPagerBannerBean>() {
            @Override
            public void onGetResult(MainPagerBannerBean result) {
                view.showNormal();
                view.updateBanner(result.getData());
            }

            @Override
            public void onError(Throwable e) {
                view.showError();
                e.printStackTrace();
            }
        });
    }

    @Override
    public void destroy() {
        dataManager.destroy();
    }

    /**
     * 页数加1
     */
    public void addPage(){
        page++;
    }

    /**
     * 页数重置为0
     */
    public void resetPage(){
        page = 0;
    }
}
