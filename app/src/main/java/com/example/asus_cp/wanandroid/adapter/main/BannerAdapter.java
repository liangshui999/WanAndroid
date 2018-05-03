package com.example.asus_cp.wanandroid.adapter.main;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asus_cp.wanandroid.bean.main.MainPagerBannerBean;
import com.example.asus_cp.wanandroid.net.image_loader.ImageLoader;
import com.example.asus_cp.wanandroid.net.image_loader.ImageLoaderImpl;

import java.util.List;

public class BannerAdapter extends PagerAdapter {

    private List<ImageView> views;

    private List<MainPagerBannerBean.DataBean> banners;

    private ImageLoader imageLoader;

    public BannerAdapter(List<ImageView> views, List<MainPagerBannerBean.DataBean> banners) {
        this.views = views;
        this.banners = banners;
        this.imageLoader = ImageLoaderImpl.getInstance();
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = views.get(position);
        imageLoader.load((ImageView) view, banners.get(position).getImagePath());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(views.get(position));
    }
}
