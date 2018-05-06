package com.example.asus_cp.wanandroid.di.component;

import android.app.Application;

import com.example.asus_cp.wanandroid.app.APP;
import com.example.asus_cp.wanandroid.base.adapter.BaseAdapter;
import com.example.asus_cp.wanandroid.di.module.ActivityBindingModule;
import com.example.asus_cp.wanandroid.di.module.ApplicationModule;
import com.example.asus_cp.wanandroid.di.module.GlobleModule;
import com.example.asus_cp.wanandroid.net.http.datamanager.BaseDataManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ActivityBindingModule.class,
        ApplicationModule.class,
        GlobleModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<APP>{

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder{

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

    void inject(BaseDataManager dataManager);


}
