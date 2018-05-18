package com.example.asus_cp.wanandroid.adapter.knowledge_architecture;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.adapter.main.MainPagerAdapter;
import com.example.asus_cp.wanandroid.base.adapter.BaseAdapter;
import com.example.asus_cp.wanandroid.bean.main.MainPagerListBean;
import com.example.asus_cp.wanandroid.callback.OnItemClickListener;

import java.util.List;

public class KnowledgeArchitectureDetailListAdapter extends BaseAdapter<MainPagerAdapter.ViewHolder, MainPagerListBean.DataBean.DatasBean> {

    private OnItemClickListener onItemClickListener;

    public KnowledgeArchitectureDetailListAdapter(List<MainPagerListBean.DataBean.DatasBean> datas) {
        super(datas);
    }

    @NonNull
    @Override
    public MainPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_layout_main_pager, parent, false);
        MainPagerAdapter.ViewHolder viewHolder = new MainPagerAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainPagerAdapter.ViewHolder holder, int position) {
        MainPagerListBean.DataBean.DatasBean data = datas.get(position);
        imageLoader.load(holder.imgHead, data.getApkLink());
        assert holder.textAuthor != null;
        assert holder.textCategory != null;
        assert holder.textTitle != null;
        assert holder.textTime != null;
        assert holder.constraintLayout != null;
        holder.textAuthor.setText(data.getAuthor());
        holder.textCategory.setText(data.getChapterName());
        holder.textTitle.setText(data.getTitle());
        holder.textTime.setText(data.getNiceDate());
        holder.constraintLayout.setOnClickListener((v)->{
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(v, position);
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
