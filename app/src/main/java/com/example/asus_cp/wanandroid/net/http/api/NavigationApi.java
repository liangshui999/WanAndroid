package com.example.asus_cp.wanandroid.net.http.api;

import com.example.asus_cp.wanandroid.bean.navigation.NavigationBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NavigationApi {

    @GET("navi/json")
    Observable<NavigationBean> getNavigationBean();

}
