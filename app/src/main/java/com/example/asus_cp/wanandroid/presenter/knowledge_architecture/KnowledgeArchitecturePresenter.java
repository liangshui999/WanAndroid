package com.example.asus_cp.wanandroid.presenter.knowledge_architecture;

import com.example.asus_cp.wanandroid.base.presenter.AbstractPresenter;
import com.example.asus_cp.wanandroid.contract.knowledge_architecture.KnowledgeArchitectureContract;

import javax.inject.Inject;

public class KnowledgeArchitecturePresenter extends AbstractPresenter<KnowledgeArchitectureContract.View>
        implements KnowledgeArchitectureContract.Presenter {

    @Inject
    public KnowledgeArchitecturePresenter() {
    }
}
