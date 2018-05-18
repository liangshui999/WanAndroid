package com.example.asus_cp.wanandroid.presenter.project;

import com.example.asus_cp.wanandroid.base.presenter.AbstractPresenter;
import com.example.asus_cp.wanandroid.contract.project.ProjectContract;
import com.example.asus_cp.wanandroid.di.scope.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class ProjectPresenter extends AbstractPresenter<ProjectContract.View>
        implements ProjectContract.Presenter{

    @Inject
    public ProjectPresenter() {
    }

    @Override
    public void destroy() {

    }
}
