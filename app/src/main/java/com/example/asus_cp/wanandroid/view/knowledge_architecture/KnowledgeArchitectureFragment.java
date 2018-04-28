package com.example.asus_cp.wanandroid.view.knowledge_architecture;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.fragment.BaseFragment;
import com.example.asus_cp.wanandroid.contract.knowledge_architecture.KnowledgeArchitectureContract;

import javax.inject.Inject;

import dagger.Binds;

public class KnowledgeArchitectureFragment extends BaseFragment<KnowledgeArchitectureContract.Presenter> implements KnowledgeArchitectureContract.View {
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_knowledge_architecture;
    }

    @Inject
    public KnowledgeArchitectureFragment() {
    }
}
