package com.example.asus_cp.wanandroid.view.navigation;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.fragment.BaseFragment;
import com.example.asus_cp.wanandroid.contract.navigation.NavigationContract;

import javax.inject.Inject;

public class NavigationFragment extends BaseFragment<NavigationContract.Presenter>
        implements NavigationContract.View {
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Inject
    public NavigationFragment() {
    }
}
