package com.example.asus_cp.wanandroid.net.http.api;


import com.example.asus_cp.wanandroid.bean.main.MainPagerBannerBean;
import com.example.asus_cp.wanandroid.bean.main.MainPagerListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MainPagerApi {

    /**
     * 获取首页的列表数据
     * @param page 页数
     */
    @GET("article/list/{page}/json")
    Observable<MainPagerListBean> getListData(@Path("page") int page);

    /**
     * 获取首页的banner数据
     */
    @GET("banner/json")
    Observable<MainPagerBannerBean> getBannerData();

}
