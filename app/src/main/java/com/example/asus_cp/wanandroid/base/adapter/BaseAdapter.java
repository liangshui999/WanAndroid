package com.example.asus_cp.wanandroid.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus_cp.wanandroid.app.APP;
import com.example.asus_cp.wanandroid.di.component.DaggerAppComponent;
import com.example.asus_cp.wanandroid.net.image_loader.ImageLoader;
import com.example.asus_cp.wanandroid.net.image_loader.ImageLoaderImpl;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    protected String TAG = this.getClass().getSimpleName();

    protected List<T> datas;

    protected LayoutInflater inflater;

    protected ImageLoader imageLoader;

    protected Context context;

    public BaseAdapter(List<T> datas) {
        this.datas = datas;
        context = APP.getContext();
        inflater = LayoutInflater.from(context);
        imageLoader = ImageLoaderImpl.getInstance();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public static class BaseViewHolder extends RecyclerView.ViewHolder{
        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);//注意这里的写法
        }
    }
}
