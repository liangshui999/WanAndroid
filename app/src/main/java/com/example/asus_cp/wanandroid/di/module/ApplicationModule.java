package com.example.asus_cp.wanandroid.di.module;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import com.example.asus_cp.wanandroid.app.APP;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ApplicationModule {

    @Binds
    abstract Context provideContext(Application application);


}
