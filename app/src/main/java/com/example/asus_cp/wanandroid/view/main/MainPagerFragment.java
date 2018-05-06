package com.example.asus_cp.wanandroid.view.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.adapter.main.MainPagerAdapter;
import com.example.asus_cp.wanandroid.base.fragment.BaseFragment;
import com.example.asus_cp.wanandroid.bean.main.MainPagerBannerBean;
import com.example.asus_cp.wanandroid.bean.main.MainPagerListBean;
import com.example.asus_cp.wanandroid.constant.LoadDataMode;
import com.example.asus_cp.wanandroid.contract.main.MainPagerContract;
import com.example.asus_cp.wanandroid.itemDeraction.MyItemDecoration;
import com.example.asus_cp.wanandroid.util.MyLog;
import com.example.asus_cp.wanandroid.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class MainPagerFragment extends BaseFragment<MainPagerContract.Presenter> implements MainPagerContract.View{

    @BindView(R.id.swipe_to_load_layout)
    SwipeToLoadLayout swipeToLoadLayout;

    @BindView(R.id.swipe_target)
    RecyclerView recyclerView;

    private MainPagerAdapter adapter;

    private List<MainPagerListBean.DataBean.DatasBean> listDatas;

    private List<MainPagerBannerBean.DataBean> bannerDatas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return view;
    }

    private void init() {
        listDatas = new ArrayList<>();
        bannerDatas = new ArrayList<>();
        adapter = new MainPagerAdapter(listDatas, bannerDatas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new MyItemDecoration());
        recyclerView.setAdapter(adapter);

        //加载数据
        presenter.loadBannerData();
        presenter.loadListData(LoadDataMode.FIRST_LOAD);

        //设置上拉加载和下拉刷新的监听器
        swipeToLoadLayout.setOnRefreshListener(()->{
            MyLog.d(TAG, "正在刷新.....");
            presenter.resetPage();
            presenter.loadListData(LoadDataMode.PULL_TO_REFRESH);

        });

        swipeToLoadLayout.setOnLoadMoreListener(() -> {
            MyLog.d(TAG, "正在加载.....");
            presenter.addPage();
            presenter.loadListData(LoadDataMode.LOAD_MORE);
        });

        //滚动到底部之后自动加载
       recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
               super.onScrollStateChanged(recyclerView, newState);
               if(newState == SCROLL_STATE_IDLE){
                   if(! ViewUtil.canChildScrollUp(recyclerView)){
                       swipeToLoadLayout.setLoadingMore(true);
                   }
               }
           }
       });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_main_pager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLog.d(TAG, "onDestroy()...........");
        adapter.destroy();
        presenter.destroy();

    }

    @Inject
    public MainPagerFragment() {

    }


    @Override
    public void firstLoadList(List<MainPagerListBean.DataBean.DatasBean> datas) {
        listDatas.addAll(datas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateBanner(List<MainPagerBannerBean.DataBean> datas) {
        bannerDatas.clear();
        bannerDatas.addAll(datas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshList(List<MainPagerListBean.DataBean.DatasBean> datas) {
        swipeToLoadLayout.setRefreshing(false);//隐藏刷新的部分
        listDatas.clear();
        listDatas.addAll(datas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreList(List<MainPagerListBean.DataBean.DatasBean> datas) {
        swipeToLoadLayout.setLoadingMore(false);//隐藏加载更多的部分
        int temp = listDatas.size();
        listDatas.addAll(datas);
        adapter.notifyItemRangeChanged(temp, datas.size());//防止图片闪烁
    }
}
