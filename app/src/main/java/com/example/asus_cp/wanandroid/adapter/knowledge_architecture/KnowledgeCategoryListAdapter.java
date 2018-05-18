package com.example.asus_cp.wanandroid.adapter.knowledge_architecture;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.adapter.BaseAdapter;
import com.example.asus_cp.wanandroid.bean.knowledge_architecture.KnowledgeArchitectureBean;
import com.example.asus_cp.wanandroid.callback.OnItemClickListener;
import com.example.asus_cp.wanandroid.util.MyLog;
import com.example.asus_cp.wanandroid.widget.MyConstraintLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;

public class KnowledgeCategoryListAdapter extends BaseAdapter<KnowledgeCategoryListAdapter.ViewHolder, KnowledgeArchitectureBean.DataBean> {


    private OnItemClickListener onItemClickListener;

    public KnowledgeCategoryListAdapter(List<KnowledgeArchitectureBean.DataBean> datas) {
        super(datas);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_knowledge_architecture_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KnowledgeArchitectureBean.DataBean dataBean = datas.get(position);
        holder.textTitle.setText(dataBean.getName());
        List<KnowledgeArchitectureBean.DataBean.ChildrenBean> childrenBeanList = new ArrayList<>(dataBean.getChildren());
        MyLog.d(TAG, "开始时的size = " + childrenBeanList.size());
        deleteSurplus(childrenBeanList);
        MyLog.d(TAG, "结束时childrenBeanList.size() = " + childrenBeanList.size());
        KnowledgeTagAdapter adapter = new KnowledgeTagAdapter(childrenBeanList);
        holder.tagFlowLayout.setAdapter(adapter);
        holder.constraintLayout.setOnClickListener((view)->{
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(view, position);
            }
        });

    }

    /**
     * 删除多余的
     */
    private void deleteSurplus(List<KnowledgeArchitectureBean.DataBean.ChildrenBean> childrenBeanList) {
        Iterator<KnowledgeArchitectureBean.DataBean.ChildrenBean> iterator = childrenBeanList.iterator();
        if(childrenBeanList.size() > 5){
            int count = 0;
            while (iterator.hasNext()) {
                if (count < 5) {
                    iterator.next();
                } else {
                    iterator.next();
                    iterator.remove();
                }
                count++;
                Log.d(TAG, "count = " + count);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends BaseAdapter.BaseViewHolder{

        @BindView(R.id.item_container)
        MyConstraintLayout constraintLayout;

        @BindView(R.id.text_title)
        TextView textTitle;

        @BindView(R.id.tagFlowLayout)
        TagFlowLayout tagFlowLayout;

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
