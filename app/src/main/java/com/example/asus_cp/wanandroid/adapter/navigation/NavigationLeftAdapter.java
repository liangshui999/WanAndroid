package com.example.asus_cp.wanandroid.adapter.navigation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.adapter.BaseAdapter;
import com.example.asus_cp.wanandroid.bean.navigation.NavigationBean;
import com.example.asus_cp.wanandroid.callback.OnItemClickListener;
import com.example.asus_cp.wanandroid.util.MyLog;

import java.util.List;

import butterknife.BindView;

public class NavigationLeftAdapter extends BaseAdapter<NavigationLeftAdapter.ViewHolder, NavigationBean.DataBean> {

    private OnItemClickListener onItemClickListener;

    private int selectedPosition = 0;

    private RecyclerView recyclerView;

    public NavigationLeftAdapter(List<NavigationBean.DataBean> datas) {
        super(datas);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_navigation_left, parent, false);
        this.recyclerView = (RecyclerView) parent;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //先把颜色还原，免得复用之前的view设置好的颜色
        unSelect(holder.textName);
        if(position == selectedPosition){
            select(holder.textName);
        }
        final int tempPosition = position;
        holder.textName.setText(datas.get(position).getName());
        holder.textName.setOnClickListener(v -> {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(holder.textName, position);
                selectItem(tempPosition, (TextView) v);
            }
        });
    }

    /**
     * 选中某一小项
     * @param position
     * @param v
     */
    public void selectItem(int position, TextView v) {
        selectedPosition = position;
        //先把所有的子view都不选中
        //注意recyclerView.getChildCount()的值只是复用的几个view的个数，
        //不等于adapter.getItemCount()返回的数据
        for(int i = 0; i < recyclerView.getChildCount(); i++){
            LinearLayout ll = (LinearLayout)recyclerView .getChildAt(i);
            TextView textView = (TextView) ll.getChildAt(0);
            unSelect(textView);
        }
        select(v);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void unSelect(TextView textView) {
        textView.setBackgroundResource(R.color.decoration);
        textView.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
    }

    private void select(TextView textView) {
        textView.setBackgroundResource(R.color.colorPrimary);
        textView.setTextColor(context.getResources().getColor(R.color.white));
    }

    class ViewHolder extends BaseAdapter.BaseViewHolder {

        @BindView(R.id.text_name)
        TextView textName;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}


