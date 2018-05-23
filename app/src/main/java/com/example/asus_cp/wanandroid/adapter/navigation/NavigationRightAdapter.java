package com.example.asus_cp.wanandroid.adapter.navigation;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.adapter.BaseAdapter;
import com.example.asus_cp.wanandroid.bean.navigation.NavigationBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;

public class NavigationRightAdapter extends BaseAdapter<NavigationRightAdapter.ViewHolder, NavigationBean.DataBean> {


    public NavigationRightAdapter(List<NavigationBean.DataBean> datas) {
        super(datas);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_navigation_right, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NavigationBean.DataBean dataBean = datas.get(position);
        holder.textName.setText(dataBean.getName());
        TagAdapter<NavigationBean.DataBean.ArticlesBean> tagAdapter = new NavigationTagAdapter(dataBean.getArticles());
        holder.tagFlowLayout.setAdapter(tagAdapter);
    }

    class ViewHolder extends BaseAdapter.BaseViewHolder{

        @BindView(R.id.text_name)
        TextView textName;

        @BindView(R.id.tag_flow_layout)
        TagFlowLayout tagFlowLayout;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
