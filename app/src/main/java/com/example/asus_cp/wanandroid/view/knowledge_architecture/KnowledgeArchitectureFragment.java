package com.example.asus_cp.wanandroid.view.knowledge_architecture;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.adapter.knowledge_architecture.KnowledgeCategoryListAdapter;
import com.example.asus_cp.wanandroid.base.fragment.BaseFragment;
import com.example.asus_cp.wanandroid.bean.knowledge_architecture.KnowledgeArchitectureBean;
import com.example.asus_cp.wanandroid.callback.OnItemClickListener;
import com.example.asus_cp.wanandroid.constant.IntentConstant;
import com.example.asus_cp.wanandroid.contract.knowledge_architecture.KnowledgeArchitectureContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class KnowledgeArchitectureFragment extends BaseFragment<KnowledgeArchitectureContract.Presenter> implements KnowledgeArchitectureContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private KnowledgeCategoryListAdapter adapter;

    private List<KnowledgeArchitectureBean.DataBean> datas;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_knowledge_architecture;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        datas = new ArrayList<>();
        adapter = new KnowledgeCategoryListAdapter(datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        presenter.loadData();

        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(KnowledgeArchitectureFragment.this.getActivity(),
                    KnowledgeArchitectureDetailActivity.class);
            intent.putExtra(IntentConstant.KEY_KNOWLEDGE_ARCHITECTURE_DATA_BEAN, datas.get(position));
            startActivity(intent);
        });
        return v;
    }

    @Inject
    public KnowledgeArchitectureFragment() {

    }

    @Override
    public void showList(List<KnowledgeArchitectureBean.DataBean> dataBeanList) {
        datas.addAll(dataBeanList);
        adapter.notifyDataSetChanged();
    }
}
