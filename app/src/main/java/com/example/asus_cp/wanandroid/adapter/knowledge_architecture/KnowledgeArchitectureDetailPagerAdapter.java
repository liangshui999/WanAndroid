package com.example.asus_cp.wanandroid.adapter.knowledge_architecture;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.adapter.main.MainPagerAdapter;
import com.example.asus_cp.wanandroid.app.APP;
import com.example.asus_cp.wanandroid.bean.knowledge_architecture.KnowledgeArchitectureBean;
import com.example.asus_cp.wanandroid.bean.main.MainPagerListBean;
import com.example.asus_cp.wanandroid.callback.OnGetHttpResultListener;
import com.example.asus_cp.wanandroid.callback.OnItemClickListener;
import com.example.asus_cp.wanandroid.constant.IntentConstant;
import com.example.asus_cp.wanandroid.di.component.DaggerAppComponent;
import com.example.asus_cp.wanandroid.itemDeraction.MyItemDecoration;
import com.example.asus_cp.wanandroid.net.http.datamanager.KnowledgeArchitectureDataManager;
import com.example.asus_cp.wanandroid.view.DetailActivity;
import com.example.asus_cp.wanandroid.view.knowledge_architecture.KnowledgeArchitectureDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class KnowledgeArchitectureDetailPagerAdapter extends PagerAdapter {

    @Inject
    KnowledgeArchitectureDataManager knowledgeArchitectureDataManager;

    private Context context;

    private KnowledgeArchitectureBean.DataBean dataBean;

    private List<SwipeToLoadLayout> swipeToLoadLayoutList;

    private List<RecyclerView> recyclerViews;

    private List<KnowledgeArchitectureBean.DataBean.ChildrenBean> childrenBeans;


    public KnowledgeArchitectureDetailPagerAdapter(Context context, KnowledgeArchitectureBean.DataBean dataBean){
        //依赖注入
        APP.getAppComponent().inject(this);
        this.context = context;
        this.dataBean = dataBean;
        childrenBeans = dataBean.getChildren();
        swipeToLoadLayoutList = new ArrayList<>();
        recyclerViews = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(context);
        if(childrenBeans != null){
            int size = childrenBeans.size();
            for(int i = 0; i < size; i++){
                SwipeToLoadLayout layout = (SwipeToLoadLayout) inflater.inflate(
                        R.layout.item_viewpager_knowledge_architecture_detail, null);
                layout.setLoadMoreEnabled(false);
                layout.setRefreshEnabled(false);
                swipeToLoadLayoutList.add(layout);

                RecyclerView recyclerView = layout.findViewById(R.id.swipe_target);
                List<MainPagerListBean.DataBean.DatasBean> datasBeanLists = new ArrayList<>();
                KnowledgeArchitectureDetailListAdapter adapter = new KnowledgeArchitectureDetailListAdapter(datasBeanLists);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.addItemDecoration(new MyItemDecoration());
                recyclerView.setAdapter(adapter);
                recyclerViews.add(recyclerView);

                knowledgeArchitectureDataManager.getDetailList(0, childrenBeans.get(i).getId(), new OnGetHttpResultListener<MainPagerListBean>() {
                    @Override
                    public void onGetResult(MainPagerListBean result) {
                        datasBeanLists.addAll(result.getData().getDatas());
                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListener((view, position)->{
                            Intent intent = new Intent(context, DetailActivity.class);
                            intent.putExtra(IntentConstant.KEY_TO_DETAIL_ACTIVITY_URL,
                                    result.getData().getDatas().get(position).getLink());
                            context.startActivity(intent);
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        KnowledgeArchitectureDetailActivity activity = (KnowledgeArchitectureDetailActivity) context;
                        activity.showError();
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    @Override
    public int getCount() {
        return swipeToLoadLayoutList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = swipeToLoadLayoutList.get(position);
        /*RecyclerView recyclerView = v.findViewById(R.id.swipe_target);
        List<MainPagerListBean.DataBean.DatasBean> datasBeanLists = new ArrayList<>();
        KnowledgeArchitectureDetailListAdapter adapter = new KnowledgeArchitectureDetailListAdapter(datasBeanLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new MyItemDecoration());
        recyclerView.setAdapter(adapter);
        knowledgeArchitectureDataManager.getDetailList(0, childrenBeans.get(position).getId(), new OnGetHttpResultListener<MainPagerListBean>() {
            @Override
            public void onGetResult(MainPagerListBean result) {
                datasBeanLists.addAll(result.getData().getDatas());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                KnowledgeArchitectureDetailActivity activity = (KnowledgeArchitectureDetailActivity) context;
                activity.showError();
                e.printStackTrace();
            }
        });*/

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(swipeToLoadLayoutList.get(position));
        //knowledgeArchitectureDataManager.destroy();//停止网络加载
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return childrenBeans.get(position).getName();
    }
}
