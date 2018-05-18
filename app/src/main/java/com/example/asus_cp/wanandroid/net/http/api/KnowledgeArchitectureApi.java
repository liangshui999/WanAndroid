package com.example.asus_cp.wanandroid.net.http.api;

import com.example.asus_cp.wanandroid.bean.knowledge_architecture.KnowledgeArchitectureBean;
import com.example.asus_cp.wanandroid.bean.main.MainPagerListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface KnowledgeArchitectureApi {

    /**
     * 获取知识体系的列表数据
     */
    @GET("tree/json")
    Observable<KnowledgeArchitectureBean> getData();

    /**
     * 例如查看Android Studio类别下所有的文章：http://www.wanandroid.com/article/list/0/json?cid=60
     * @param page 页数
     */
    @GET("article/list/{page}/json")
    Observable<MainPagerListBean> getDetailList(@Path("page") int page, @Query("cid") int cid);
}
