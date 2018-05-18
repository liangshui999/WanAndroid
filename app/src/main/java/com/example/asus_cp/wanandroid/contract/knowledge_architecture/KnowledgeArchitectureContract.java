package com.example.asus_cp.wanandroid.contract.knowledge_architecture;

import android.view.View;

import com.example.asus_cp.wanandroid.base.presenter.BasePresenter;
import com.example.asus_cp.wanandroid.base.view.BaseView;
import com.example.asus_cp.wanandroid.bean.knowledge_architecture.KnowledgeArchitectureBean;
import com.example.asus_cp.wanandroid.callback.OnGetHttpResultListener;

import java.util.List;

public interface KnowledgeArchitectureContract {

    interface View extends BaseView{

        /**
         * 展示列表
         * @param dataBeanList 列表的数据
         */
        void showList(List<KnowledgeArchitectureBean.DataBean> dataBeanList);
    }

    interface Presenter extends BasePresenter<View>{

        /**
         * 获取数据
         */
        void loadData();

    }

}
