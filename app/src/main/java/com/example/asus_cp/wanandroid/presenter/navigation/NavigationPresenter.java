package com.example.asus_cp.wanandroid.presenter.navigation;

import com.example.asus_cp.wanandroid.base.presenter.AbstractPresenter;
import com.example.asus_cp.wanandroid.contract.navigation.NavigationContract;

import javax.inject.Inject;

public class NavigationPresenter extends AbstractPresenter<NavigationContract.View>
        implements NavigationContract.Presenter{

    @Inject
    public NavigationPresenter() {
    }
}
