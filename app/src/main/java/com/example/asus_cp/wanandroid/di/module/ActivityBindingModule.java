package com.example.asus_cp.wanandroid.di.module;

import com.example.asus_cp.wanandroid.view.MainActivity;
import com.example.asus_cp.wanandroid.di.scope.ActivityScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    /**
     * 抽象方法加上ContributesAndroidInjector注解，最终会生成一个MainActivitySubcomponent
     * ActivityScope注解是修饰在SubComponent上面的
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity mainActivity();

}
