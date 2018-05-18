package com.example.asus_cp.wanandroid.adapter.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.adapter.BaseAdapter;
import com.example.asus_cp.wanandroid.bean.main.MainPagerBannerBean;
import com.example.asus_cp.wanandroid.bean.main.MainPagerListBean;
import com.example.asus_cp.wanandroid.callback.OnItemClickListener;
import com.example.asus_cp.wanandroid.util.DensityUtils;
import com.example.asus_cp.wanandroid.util.MyLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.BindsInstance;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPagerAdapter extends BaseAdapter<MainPagerAdapter.ViewHolder, MainPagerListBean.DataBean.DatasBean> {

    private static final int VIEW_TYPE_BANNER = 1;

    private static final int VIEW_TYPE_NORMAL = 2;

    private int viewType;

    private boolean first = true;

    private Disposable disposable;

    private int position = 0;

    private List<MainPagerBannerBean.DataBean> banners;

    private OnItemClickListener onItemClickListener;

    public MainPagerAdapter(List<MainPagerListBean.DataBean.DatasBean> datas, List<MainPagerBannerBean.DataBean> banners) {
        super(datas);
        this.banners = banners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyLog.d(TAG, "onCreateViewHolder.....viewType = " + viewType);
        View v = null;
        switch (viewType){
            case VIEW_TYPE_BANNER:
                v = inflater.inflate(R.layout.item_banner_main_pager, parent, false);
                break;
            case VIEW_TYPE_NORMAL:
                v = inflater.inflate(R.layout.item_layout_main_pager, parent, false);
                break;
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyLog.d(TAG, "onBindViewHolder.....viewType = " + viewType);
        switch (viewType){
            case VIEW_TYPE_NORMAL:
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
                break;
            case VIEW_TYPE_BANNER:
                if(! first){
                    return;
                }
                first = false;
                initBanner(holder);
                break;
        }
    }

    /**
     * 在这个方法里面设置viewType，不要在onCreateViewHolder（）方法里面设置
     * onCreateViewHolder（）不一定会被调用
     */
    @Override
    public int getItemViewType(int position) {
        //MyLog.d(TAG, "getItemViewType................position = " + position);
        if(position == 0){
            viewType = VIEW_TYPE_BANNER;
            return VIEW_TYPE_BANNER;
        }
        viewType = VIEW_TYPE_NORMAL;
        return VIEW_TYPE_NORMAL;
    }

    /**
     * 初始化banner
     */
    @SuppressLint({"CheckResult", "ClickableViewAccessibility"})
    private void initBanner(@NonNull ViewHolder holder) {
        List<ImageView> imageViews = new ArrayList<>();
        for(int i = 0; i < banners.size(); i++){
            //为viewpager添加view
            ImageView imageView = new ImageView(context);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageViews.add(imageView);

            //添加指示点
            ImageView imgIndicate = new ImageView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = (int) DensityUtils.dp2px(10);
            imgIndicate.setLayoutParams(lp);
            imgIndicate.setImageResource(R.drawable.shape_indicate_point_not_selected);
            assert holder.llIndicate != null;
            holder.llIndicate.addView(imgIndicate);
        }
        assert holder.llIndicate != null;
        assert holder.viewPager != null;
        ImageView imageView = (ImageView) holder.llIndicate.getChildAt(0);
        imageView.setImageResource(R.drawable.shape_indicate_point_selected);
        BannerAdapter bannerAdapter = new BannerAdapter(imageViews, banners);
        holder.viewPager.setAdapter(bannerAdapter);

        //为viewpager设置监听器
        holder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                changeIndicate(position, holder);
                MainPagerAdapter.this.position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case ViewPager.SCROLL_STATE_DRAGGING://开始拖曳的时候就停止定时器
                        MyLog.d(TAG, "SCROLL_STATE_DRAGGING.........");
                        stopTimer();
                        break;
                }
            }
        });

        holder.viewPager.setOnTouchListener((view, event)->{
            switch (event.getAction()){
                case MotionEvent.ACTION_UP:
                    startTimer(holder);//开始定时器
                    break;
            }
            return false;
        });

        //定时更换banner
        startTimer(holder);
    }

    /**
     * 开启定时任务,定时更换banner
     */
    private void startTimer(@NonNull ViewHolder holder) {
        Observable.interval(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        //MyLog.d(TAG, "accept............");
                        assert holder.viewPager != null;
                        holder.viewPager.setCurrentItem(position);
                        changeIndicate(position, holder);
                        position++;
                        if(position % banners.size() == 0){
                            position = 0;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }

    /**
     * 停止定时任务
     */
    private void stopTimer(){
        if(disposable == null){
            return;
        }
        if(! disposable.isDisposed()){
            disposable.dispose();
        }
    }

    /**
     * 销毁资源
     */
    public void destroy(){
        stopTimer();
    }

    /**
     * 切换指示点的颜色
     * @param position 位置
     */
    private void changeIndicate(int position, @NonNull ViewHolder holder) {
        LinearLayout ll = holder.llIndicate;
        assert ll != null;
        for(int i = 0; i < ll.getChildCount(); i++){
            ImageView imageView = (ImageView) ll.getChildAt(i);
            imageView.setImageResource(R.drawable.shape_indicate_point_not_selected);
        }
        ImageView imageView = (ImageView) ll.getChildAt(position);
        imageView.setImageResource(R.drawable.shape_indicate_point_selected);
    }


    /**
     * 设置onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends BaseAdapter.BaseViewHolder{

        @Nullable
        @BindView(R.id.item_container)
        public ConstraintLayout constraintLayout;

        @Nullable
        @BindView(R.id.img_head)
        public ImageView imgHead;

        @Nullable
        @BindView(R.id.text_author)
        public TextView textAuthor;

        @Nullable
        @BindView(R.id.text_category)
        public TextView textCategory;

        @Nullable
        @BindView(R.id.text_title)
        public TextView textTitle;

        @Nullable
        @BindView(R.id.img_collect)
        public ImageView imgCollect;

        @Nullable
        @BindView(R.id.text_time)
        public TextView textTime;

        @Nullable
        @BindView(R.id.viewpager)
        ViewPager viewPager;

        @Nullable
        @BindView(R.id.ll_indicate)
        LinearLayout llIndicate;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "imgHead=" + imgHead +
                    ", textAuthor=" + textAuthor +
                    ", textCategory=" + textCategory +
                    ", textTitle=" + textTitle +
                    ", imgCollect=" + imgCollect +
                    ", textTime=" + textTime +
                    ", viewPager=" + viewPager +
                    ", llIndicate=" + llIndicate +
                    '}';
        }
    }
}
