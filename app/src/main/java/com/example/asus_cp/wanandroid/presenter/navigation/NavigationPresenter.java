package com.example.asus_cp.wanandroid.presenter.navigation;

import com.example.asus_cp.wanandroid.base.presenter.AbstractPresenter;
import com.example.asus_cp.wanandroid.bean.navigation.NavigationBean;
import com.example.asus_cp.wanandroid.callback.OnGetHttpResultListener;
import com.example.asus_cp.wanandroid.contract.navigation.NavigationContract;
import com.example.asus_cp.wanandroid.di.scope.ActivityScope;
import com.example.asus_cp.wanandroid.net.http.datamanager.NavigationDataManager;

import javax.inject.Inject;

@ActivityScope
public class NavigationPresenter extends AbstractPresenter<NavigationContract.View>
        implements NavigationContract.Presenter{

    @Inject
    NavigationDataManager navigationDataManager;

    @Inject
    public NavigationPresenter() {
    }

    @Override
    public void destroy() {
        navigationDataManager.destroy();
    }

    @Override
    public void loadNavigationData() {
        view.showLoading();
        navigationDataManager.getNavigationBean(new OnGetHttpResultListener<NavigationBean>() {
            @Override
            public void onGetResult(NavigationBean result) {
                view.showNormal();
                view.refreshList(result.getData());
            }

            @Override
            public void onError(Throwable e) {
                view.showError();
                e.printStackTrace();
            }
        });
    }
}
