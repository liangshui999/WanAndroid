package com.example.asus_cp.wanandroid.presenter.knowledge_architecture;

import com.example.asus_cp.wanandroid.base.presenter.AbstractPresenter;
import com.example.asus_cp.wanandroid.bean.knowledge_architecture.KnowledgeArchitectureBean;
import com.example.asus_cp.wanandroid.callback.OnGetHttpResultListener;
import com.example.asus_cp.wanandroid.contract.knowledge_architecture.KnowledgeArchitectureContract;
import com.example.asus_cp.wanandroid.di.scope.ActivityScope;
import com.example.asus_cp.wanandroid.net.http.datamanager.KnowledgeArchitectureDataManager;

import javax.inject.Inject;

@ActivityScope
public class KnowledgeArchitecturePresenter extends AbstractPresenter<KnowledgeArchitectureContract.View>
        implements KnowledgeArchitectureContract.Presenter {

    @Inject
    KnowledgeArchitectureDataManager knowledgeArchitectureDataManager;

    @Inject
    public KnowledgeArchitecturePresenter() {
    }

    @Override
    public void destroy() {
        knowledgeArchitectureDataManager.destroy();
    }

    @Override
    public void loadData() {
        view.showLoading();
        knowledgeArchitectureDataManager.loadData(new OnGetHttpResultListener<KnowledgeArchitectureBean>() {
            @Override
            public void onGetResult(KnowledgeArchitectureBean result) {
                view.showNormal();
                view.showList(result.getData());
            }

            @Override
            public void onError(Throwable e) {
                view.showError();
                e.printStackTrace();
            }
        });
    }
}
