package com.example.asus_cp.wanandroid.adapter.navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.app.APP;
import com.example.asus_cp.wanandroid.bean.navigation.NavigationBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

public class NavigationTagAdapter extends TagAdapter<NavigationBean.DataBean.ArticlesBean> {

    public NavigationTagAdapter(List<NavigationBean.DataBean.ArticlesBean> datas) {
        super(datas);
    }

    @Override
    public View getView(FlowLayout parent, int position, NavigationBean.DataBean.ArticlesBean articlesBean) {
        TextView textView = (TextView) LayoutInflater.from(APP.getContext()).inflate(R.layout.item_tag_layout, parent, false);
        textView.setText(articlesBean.getTitle());
        return textView;
    }
}
