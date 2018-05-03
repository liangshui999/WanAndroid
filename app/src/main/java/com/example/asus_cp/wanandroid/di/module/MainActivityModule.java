package com.example.asus_cp.wanandroid.di.module;

import com.example.asus_cp.wanandroid.contract.MainContract;
import com.example.asus_cp.wanandroid.contract.knowledge_architecture.KnowledgeArchitectureContract;
import com.example.asus_cp.wanandroid.contract.main.MainPagerContract;
import com.example.asus_cp.wanandroid.contract.navigation.NavigationContract;
import com.example.asus_cp.wanandroid.contract.project.ProjectContract;
import com.example.asus_cp.wanandroid.di.module.knowledge_architecture.KnowledgeArchitectureFragmentModule;
import com.example.asus_cp.wanandroid.di.module.main.MainPagerFragmentModule;
import com.example.asus_cp.wanandroid.di.module.navigation.NavigationFragmentModule;
import com.example.asus_cp.wanandroid.di.module.project.ProjectFragmentModule;
import com.example.asus_cp.wanandroid.di.scope.ActivityScope;
import com.example.asus_cp.wanandroid.di.scope.FragmentScope;
import com.example.asus_cp.wanandroid.presenter.MainPresenter;
import com.example.asus_cp.wanandroid.presenter.knowledge_architecture.KnowledgeArchitecturePresenter;
import com.example.asus_cp.wanandroid.presenter.main.MainPagerPresenter;
import com.example.asus_cp.wanandroid.presenter.navigation.NavigationPresenter;
import com.example.asus_cp.wanandroid.presenter.project.ProjectPresenter;
import com.example.asus_cp.wanandroid.view.knowledge_architecture.KnowledgeArchitectureFragment;
import com.example.asus_cp.wanandroid.view.main.MainPagerFragment;
import com.example.asus_cp.wanandroid.view.navigation.NavigationFragment;
import com.example.asus_cp.wanandroid.view.project.ProjectFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {



  @ActivityScope
  @Binds
  abstract MainPagerContract.Presenter provideMainPagerPresenter(MainPagerPresenter mainPagerPresenter);

  @ActivityScope
  @Binds
  abstract KnowledgeArchitectureContract.Presenter provideKnowledgeArchitecturePresenter(KnowledgeArchitecturePresenter knowledgeArchitecturePresenter);

  @ActivityScope
  @Binds
  abstract NavigationContract.Presenter provideNavigationPresenter(NavigationPresenter navigationPresenter);

  @ActivityScope
  @Binds
  abstract ProjectContract.Presenter provideProjectPresenter(ProjectPresenter projectPresenter);


  @FragmentScope
  @ContributesAndroidInjector
  abstract MainPagerFragment mainPagerFragment();

  @FragmentScope
  @ContributesAndroidInjector
  abstract KnowledgeArchitectureFragment knowledgeArchitectureFragment();

  @FragmentScope
  @ContributesAndroidInjector
  abstract NavigationFragment navigationFragment();

  @FragmentScope
  @ContributesAndroidInjector
  abstract ProjectFragment projectFragment();

}
