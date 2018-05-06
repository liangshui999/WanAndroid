package com.example.asus_cp.wanandroid.net.http.datamanager;

import com.example.asus_cp.wanandroid.app.APP;
import com.example.asus_cp.wanandroid.di.component.DaggerAppComponent;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

public class BaseDataManager {

    protected String TAG = this.getClass().getSimpleName();

    protected CompositeDisposable compositeDisposable;

    @Inject
    protected Retrofit retrofit;

    public BaseDataManager(){
        compositeDisposable = new CompositeDisposable();
        APP.getAppComponent().inject(this);
    }

    public void destroy(){
        compositeDisposable.clear();
    }
}
