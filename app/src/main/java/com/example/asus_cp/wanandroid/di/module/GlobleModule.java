package com.example.asus_cp.wanandroid.di.module;

import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 全局module，负责提供整个应用所需要的对象，比如Retrofit的
 */
@Module
public class GlobleModule {

    @Singleton
    @Provides
    LayoutInflater provideLayoutInflater(Context context){
        return LayoutInflater.from(context);
    }
}
