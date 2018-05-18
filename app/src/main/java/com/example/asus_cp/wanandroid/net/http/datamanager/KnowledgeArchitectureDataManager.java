package com.example.asus_cp.wanandroid.net.http.datamanager;

import com.example.asus_cp.wanandroid.bean.knowledge_architecture.KnowledgeArchitectureBean;
import com.example.asus_cp.wanandroid.bean.main.MainPagerListBean;
import com.example.asus_cp.wanandroid.callback.OnGetHttpResultListener;
import com.example.asus_cp.wanandroid.net.http.api.KnowledgeArchitectureApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class KnowledgeArchitectureDataManager extends BaseDataManager {

    @Inject
    public KnowledgeArchitectureDataManager(){}

    /**
     * 获取数据
     */
    public void loadData(OnGetHttpResultListener<KnowledgeArchitectureBean> onGetHttpResultListener){
        Disposable disposable = retrofit.create(KnowledgeArchitectureApi.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(knowledgeArchitectureBean -> {
                    onGetHttpResultListener.onGetResult(knowledgeArchitectureBean);
                }, throwable -> {
                    onGetHttpResultListener.onError(throwable);
                });
        compositeDisposable.add(disposable);
    }

    /**
     * 例如查看Android Studio类别下所有的文章：http://www.wanandroid.com/article/list/0/json?cid=60
     * @param page 页数
     */
    public void getDetailList(int page, int cid, OnGetHttpResultListener<MainPagerListBean> listener){
        Disposable disposable = retrofit.create(KnowledgeArchitectureApi.class)
                .getDetailList(page, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((mainPagerListBean)->{
                    listener.onGetResult(mainPagerListBean);
                }, (throwable)->{
                    listener.onError(throwable);
                });
        compositeDisposable.add(disposable);
    }

}
