package com.example.asus_cp.wanandroid.app;

import android.content.Context;

import com.example.asus_cp.wanandroid.di.component.AppComponent;
import com.example.asus_cp.wanandroid.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class APP extends DaggerApplication {

    private static AppComponent appComponent;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent = DaggerAppComponent.builder().application(this).build();
        return appComponent;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static Context getContext(){
        return context;
    }
}
