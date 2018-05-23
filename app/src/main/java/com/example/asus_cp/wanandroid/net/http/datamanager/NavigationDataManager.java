package com.example.asus_cp.wanandroid.net.http.datamanager;

import com.example.asus_cp.wanandroid.bean.navigation.NavigationBean;
import com.example.asus_cp.wanandroid.callback.OnGetHttpResultListener;
import com.example.asus_cp.wanandroid.net.http.api.NavigationApi;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class NavigationDataManager extends BaseDataManager {

    @Inject
    Retrofit retrofit;

    @Inject
    public NavigationDataManager(){}

    /**
     * 获取NavigationBean
     */
    public void getNavigationBean(OnGetHttpResultListener<NavigationBean> onGetHttpResultListener){
        Disposable disposable = retrofit.create(NavigationApi.class)
                .getNavigationBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((navigationBean)->{
                    onGetHttpResultListener.onGetResult(navigationBean);
                }, (throwable)->{
                    onGetHttpResultListener.onError(throwable);
                });
        compositeDisposable.add(disposable);
    }


}
