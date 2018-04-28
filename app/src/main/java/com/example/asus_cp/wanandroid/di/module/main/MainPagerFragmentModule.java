package com.example.asus_cp.wanandroid.di.module.main;

import com.example.asus_cp.wanandroid.contract.main.MainPagerContract;
import com.example.asus_cp.wanandroid.di.scope.FragmentScope;
import com.example.asus_cp.wanandroid.presenter.main.MainPagerPresenter;
import com.example.asus_cp.wanandroid.view.main.MainPagerFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainPagerFragmentModule {

    /*@FragmentScope
    @Binds
    abstract MainPagerContract.Presenter provideMainPagerPresenter(MainPagerPresenter mainPagerPresenter);*/

   /* @FragmentScope
    @Provides
    MainPagerContract.Presenter provideMainPagerPresenter(){
        return new MainPagerPresenter();
    }*/
}
