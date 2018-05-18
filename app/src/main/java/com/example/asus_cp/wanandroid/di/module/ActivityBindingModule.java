package com.example.asus_cp.wanandroid.di.module;

import com.example.asus_cp.wanandroid.contract.DetailContract;
import com.example.asus_cp.wanandroid.contract.MainContract;
import com.example.asus_cp.wanandroid.contract.knowledge_architecture.KnowledgeArchitectureContract;
import com.example.asus_cp.wanandroid.contract.knowledge_architecture.KnowledgeArchitectureDetailContract;
import com.example.asus_cp.wanandroid.presenter.DetailPresenter;
import com.example.asus_cp.wanandroid.presenter.MainPresenter;
import com.example.asus_cp.wanandroid.presenter.knowledge_architecture.KnowledgeArchitectureDetailPresenter;
import com.example.asus_cp.wanandroid.view.DetailActivity;
import com.example.asus_cp.wanandroid.view.MainActivity;
import com.example.asus_cp.wanandroid.di.scope.ActivityScope;
import com.example.asus_cp.wanandroid.view.knowledge_architecture.KnowledgeArchitectureDetailActivity;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @Binds
    abstract MainContract.Presenter provideMainPresenter(MainPresenter mainPresenter);

    @Binds
    abstract DetailContract.Presenter provideDetailPresenter(DetailPresenter detailPresenter);

    @Binds
    abstract KnowledgeArchitectureDetailContract.Presenter provideKnowledgeArchitectureDetailPresenter(KnowledgeArchitectureDetailPresenter knowledgeArchitectureDetailPresenter);

    /**
     * 抽象方法加上ContributesAndroidInjector注解，最终会生成一个MainActivitySubcomponent
     * ActivityScope注解是修饰在SubComponent上面的
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity mainActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract DetailActivity detailActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract KnowledgeArchitectureDetailActivity knowledgeArchitectureDetailActivity();

}
