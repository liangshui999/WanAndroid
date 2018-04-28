package com.example.asus_cp.wanandroid.view.project;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.fragment.BaseFragment;
import com.example.asus_cp.wanandroid.contract.project.ProjectContract;

import javax.inject.Inject;

public class ProjectFragment extends BaseFragment<ProjectContract.Presenter>
        implements ProjectContract.View {

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_project;
    }

    @Inject
    public ProjectFragment() {
    }
}
