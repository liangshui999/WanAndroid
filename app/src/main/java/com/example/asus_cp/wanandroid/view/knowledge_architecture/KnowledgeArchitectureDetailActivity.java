package com.example.asus_cp.wanandroid.view.knowledge_architecture;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.adapter.knowledge_architecture.KnowledgeArchitectureDetailPagerAdapter;
import com.example.asus_cp.wanandroid.base.activity.BaseActivity;
import com.example.asus_cp.wanandroid.bean.knowledge_architecture.KnowledgeArchitectureBean;
import com.example.asus_cp.wanandroid.constant.IntentConstant;
import com.example.asus_cp.wanandroid.contract.knowledge_architecture.KnowledgeArchitectureDetailContract;

import butterknife.BindView;

public class KnowledgeArchitectureDetailActivity extends BaseActivity<KnowledgeArchitectureDetailContract.Presenter>
        implements KnowledgeArchitectureDetailContract.View {

    @BindView(R.id.tablayout)
    TabLayout tablayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private KnowledgeArchitectureBean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBean = getIntent().getParcelableExtra(IntentConstant.KEY_KNOWLEDGE_ARCHITECTURE_DATA_BEAN);
        if (dataBean == null) {
            return;
        }
        KnowledgeArchitectureDetailPagerAdapter adapter = new KnowledgeArchitectureDetailPagerAdapter(this, dataBean);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_knowledge_architecture_detail;
    }

}
