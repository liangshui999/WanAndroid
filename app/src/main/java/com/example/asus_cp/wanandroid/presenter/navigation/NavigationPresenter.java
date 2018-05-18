package com.example.asus_cp.wanandroid.presenter.navigation;

import com.example.asus_cp.wanandroid.base.presenter.AbstractPresenter;
import com.example.asus_cp.wanandroid.contract.navigation.NavigationContract;
import com.example.asus_cp.wanandroid.di.scope.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class NavigationPresenter extends AbstractPresenter<NavigationContract.View>
        implements NavigationContract.Presenter{

    @Inject
    public NavigationPresenter() {
    }

    @Override
    public void destroy() {

    }
}
