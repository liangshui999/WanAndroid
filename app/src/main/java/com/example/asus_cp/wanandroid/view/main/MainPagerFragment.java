package com.example.asus_cp.wanandroid.view.main;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.fragment.BaseFragment;
import com.example.asus_cp.wanandroid.contract.main.MainPagerContract;
import com.example.asus_cp.wanandroid.presenter.main.MainPagerPresenter;

import javax.inject.Inject;

public class MainPagerFragment extends BaseFragment<MainPagerContract.Presenter> implements MainPagerContract.View{

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_main_pager;
    }

    @Inject
    public MainPagerFragment() {
    }
}
