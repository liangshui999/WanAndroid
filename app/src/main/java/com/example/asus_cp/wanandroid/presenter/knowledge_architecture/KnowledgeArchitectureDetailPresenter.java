package com.example.asus_cp.wanandroid.presenter.knowledge_architecture;

import com.example.asus_cp.wanandroid.base.presenter.AbstractPresenter;
import com.example.asus_cp.wanandroid.contract.knowledge_architecture.KnowledgeArchitectureDetailContract;

import javax.inject.Inject;


public class KnowledgeArchitectureDetailPresenter extends AbstractPresenter<KnowledgeArchitectureDetailContract.View> implements KnowledgeArchitectureDetailContract.Presenter {

    @Inject
    public KnowledgeArchitectureDetailPresenter(){}

    @Override
    public void destroy() {

    }

}
