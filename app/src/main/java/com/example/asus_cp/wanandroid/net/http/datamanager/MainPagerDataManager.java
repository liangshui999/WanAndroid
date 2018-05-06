package com.example.asus_cp.wanandroid.net.http.datamanager;

import android.annotation.SuppressLint;

import com.example.asus_cp.wanandroid.bean.main.MainPagerBannerBean;
import com.example.asus_cp.wanandroid.bean.main.MainPagerListBean;
import com.example.asus_cp.wanandroid.callback.OnGetHttpResultListener;
import com.example.asus_cp.wanandroid.net.http.api.MainPagerApi;
import com.example.asus_cp.wanandroid.util.MyLog;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPagerDataManager extends BaseDataManager{

    @Inject
    MainPagerDataManager(){

    }

    public void loadListData(int page, OnGetHttpResultListener<MainPagerListBean> listener) {
        Disposable disposable = retrofit.create(MainPagerApi.class)
                .getListData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result)->{
                    listener.onGetResult(result);
                }, (ex)->{
                    listener.onError(ex);
                    MyLog.e(TAG, ex.toString());
                });
        compositeDisposable.add(disposable);
    }


    @SuppressLint("CheckResult")
    public void loadBannerData(OnGetHttpResultListener<MainPagerBannerBean> listener) {
        Disposable disposable = retrofit.create(MainPagerApi.class)
                .getBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result)->{
                    listener.onGetResult(result);
                }, (ex)->{
                    listener.onError(ex);
                    MyLog.e(TAG, ex.toString());
                });
        compositeDisposable.add(disposable);
    }


}
