package com.example.asus_cp.wanandroid.di.module;

import android.content.Context;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.example.asus_cp.wanandroid.net.image_loader.ImageLoader;
import com.example.asus_cp.wanandroid.net.image_loader.ImageLoaderImpl;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @Singleton
    @Provides
    ImageLoader provideImageLoader(){
        return ImageLoaderImpl.getInstance();
    }

    @Singleton
    @Provides
    Gson provideGson(){
        return new Gson();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }
}
