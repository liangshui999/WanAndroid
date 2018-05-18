package com.example.asus_cp.wanandroid.adapter.knowledge_architecture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.app.APP;
import com.example.asus_cp.wanandroid.bean.knowledge_architecture.KnowledgeArchitectureBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KnowledgeTagAdapter extends TagAdapter<KnowledgeArchitectureBean.DataBean.ChildrenBean> {

    private Context context;

    public KnowledgeTagAdapter(List<KnowledgeArchitectureBean.DataBean.ChildrenBean> datas) {
        super(datas);
        context = APP.getContext();
    }

    @Override
    public View getView(FlowLayout parent, int position, KnowledgeArchitectureBean.DataBean.ChildrenBean childrenBean) {
        TextView textView = (TextView) LayoutInflater.from(context).inflate(
                R.layout.item_tag_layout, parent,false);
        textView.setText(childrenBean.getName());
        return textView;
    }

}
