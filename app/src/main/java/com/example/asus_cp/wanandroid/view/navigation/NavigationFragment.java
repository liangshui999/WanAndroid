package com.example.asus_cp.wanandroid.view.navigation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.adapter.navigation.NavigationLeftAdapter;
import com.example.asus_cp.wanandroid.adapter.navigation.NavigationRightAdapter;
import com.example.asus_cp.wanandroid.base.fragment.BaseFragment;
import com.example.asus_cp.wanandroid.bean.navigation.NavigationBean;
import com.example.asus_cp.wanandroid.contract.navigation.NavigationContract;
import com.example.asus_cp.wanandroid.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class NavigationFragment extends BaseFragment<NavigationContract.Presenter>
        implements NavigationContract.View {

    @BindView(R.id.recyclerView_left)
    RecyclerView recyclerViewLeft;

    @BindView(R.id.recyclerView_right)
    RecyclerView recyclerViewRight;

    private List<NavigationBean.DataBean> dataBeans;

    private NavigationLeftAdapter leftAdapter;

    private NavigationRightAdapter rightAdapter;

    private LinearLayoutManager leftLinearLayoutManager;

    private LinearLayoutManager rightLinearLayoutManager;

    private boolean isRightMoveBottom = false;

    private boolean isRightMoveCenter = false;

    private boolean isRightMoveUp = false;

    private boolean isLeftMove = false;

    private int rightPosition;

    private int leftPosition;


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Inject
    public NavigationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        init();
        //加载数据
        presenter.loadNavigationData();
        return rootView;
    }

    private void init() {
        dataBeans = new ArrayList<>();
        leftAdapter = new NavigationLeftAdapter(dataBeans);
        rightAdapter = new NavigationRightAdapter(dataBeans);
        leftLinearLayoutManager = new LinearLayoutManager(this.getActivity());
        rightLinearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerViewLeft.setAdapter(leftAdapter);
        recyclerViewRight.setAdapter(rightAdapter);
        recyclerViewLeft.setLayoutManager(leftLinearLayoutManager);
        recyclerViewRight.setLayoutManager(rightLinearLayoutManager);

        //给左边的recyclerview设置点击事件
        leftAdapter.setOnItemClickListener((view, position)->{
            //将左边点击的小项移动到中间
            moveLeftClickItemToCenter(position);
            //将isRightMoveUp，isRightMoveCenter，isRightMoveBottom设置为false
            setIsRightFalse();
            int rightFirstVisiblePosition = rightLinearLayoutManager.findFirstVisibleItemPosition();
            int rightLastVisiblePosition = rightLinearLayoutManager.findLastVisibleItemPosition();
            if(position < rightFirstVisiblePosition){ //position在firstVisiblePosition的上面直接滚动即可
                isRightMoveUp = true;
                recyclerViewRight.smoothScrollToPosition(position);
            }else if(position >= rightFirstVisiblePosition && position <= rightLastVisiblePosition){ //可视的范围内
                isRightMoveCenter = true;
                //直接用下面这句话会有bug，来回切换2个项目的时候会出现问题
                //int top = recyclerViewRight.getChildAt(position - firstVisiblePosition).getTop();
                int top = rightLinearLayoutManager.findViewByPosition(position).getTop();
                recyclerViewRight.smoothScrollBy(0, top);
            }else{ //position在lastVisiblePosition的下面
                isRightMoveBottom = true;
                //这个并不能立即就滚到到位，必须监听右边的滚动
                recyclerViewRight.smoothScrollToPosition(position);
            }
            rightPosition = position;

        });

        //监听右侧recyclerView的滚动
        recyclerViewRight.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                log("isRightMoveBottom = " + isRightMoveBottom + "....." + "isRightMoveCenter = "
                        + isRightMoveCenter + "......" + "isRightMoveUp = " + isRightMoveUp);
                //跟随左边的滑动改变右边的位置(这部分逻辑是被动滚动，非手指触摸造成的滚动)
                if(isRightMoveBottom){
                    if(isItemVisible(rightLinearLayoutManager, rightPosition)){
                        isRightMoveBottom = false;//停止该部分逻辑的回调
                        int top = rightLinearLayoutManager.findViewByPosition(rightPosition).getTop();
                        recyclerView.smoothScrollBy(0, top);
                        //将isRightMoveUp，isRightMoveCenter，isRightMoveBottom设置为false
                        setIsRightFalse();
                        isRightMoveCenter = true;
                    }
                }else if(isRightMoveCenter){
                    if(isItemVisible(rightLinearLayoutManager, rightPosition)){
                        log("isRightMoveCenter....top = " + rightLinearLayoutManager.findViewByPosition(rightPosition).getTop());
                        if(rightLinearLayoutManager.findViewByPosition(rightPosition).getTop() == 0){
                            isRightMoveCenter = false;
                        }
                    }
                }else if(isRightMoveUp){
                    if(isItemVisible(rightLinearLayoutManager, rightPosition)){
                        log("isRightMoveUp......top = " + rightLinearLayoutManager.findViewByPosition(rightPosition).getTop());
                        log("isRightMoveUp......DensityUtils.dp2px(10) = " + DensityUtils.dp2px(10));
                        if(rightLinearLayoutManager.findViewByPosition(rightPosition).getTop() == DensityUtils.dp2px(10)){
                            isRightMoveUp = false;
                        }
                    }
                }else{
                    //右边滚动改变左边位置（主动的滚动，手指触摸造成的滚动）
                    int firstVisiblePosition = rightLinearLayoutManager.findFirstVisibleItemPosition();
                    ViewGroup viewGroup = (ViewGroup) leftLinearLayoutManager.findViewByPosition(firstVisiblePosition);
                    if(viewGroup == null){
                        isLeftMove = true;
                        //这个并不能使左边立即滚动到位，必须监听左边的滚动
                        recyclerViewLeft.scrollToPosition(firstVisiblePosition);
                        leftPosition = firstVisiblePosition;
                    }else{ //可视
                        moveLeftClickItemToCenter(firstVisiblePosition);
                        TextView textView = (TextView) viewGroup.getChildAt(0);
                        leftAdapter.selectItem(firstVisiblePosition, textView);
                    }
                }

            }
        });

        //监听左侧recyclerview的滚动
        recyclerViewLeft.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(isLeftMove){
                    int firstVisiblePosition = leftLinearLayoutManager.findFirstVisibleItemPosition();
                    if(isItemVisible(leftLinearLayoutManager, leftPosition)){
                        ViewGroup viewGroup = (ViewGroup) leftLinearLayoutManager.findViewByPosition(leftPosition);
                        moveLeftClickItemToCenter(leftPosition);
                        TextView textView = (TextView) viewGroup.getChildAt(0);
                        leftAdapter.selectItem(firstVisiblePosition, textView);
                        isLeftMove = false;//停止监听
                    }
                }
            }
        });

    }

    private void setIsRightFalse(){
        isRightMoveUp = false;
        isRightMoveCenter = false;
        isRightMoveBottom = false;
    }

    /**
     * 将左边点击的小项移动到中间
     */
    private void moveLeftClickItemToCenter(int position) {
        int leftFirstVisiblePosition = leftLinearLayoutManager.findFirstVisibleItemPosition();
        int leftLastVisiblePosition = leftLinearLayoutManager.findLastVisibleItemPosition();
        int centerPosition = (leftFirstVisiblePosition + leftLastVisiblePosition) / 2;
        int centerTop = leftLinearLayoutManager.findViewByPosition(centerPosition).getTop();
        int currentTop = leftLinearLayoutManager.findViewByPosition(position).getTop();
        recyclerViewLeft.smoothScrollBy(0, currentTop - centerTop);
    }


    /**
     * 指定位置的item是否可见
     * @param layoutManager LinearLayoutManager
     * @param position 位置，adapter中的位置
     */
    private boolean isItemVisible(LinearLayoutManager layoutManager, int position){
        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
        if(position >= firstVisiblePosition && position <= lastVisiblePosition){ //可视
            return true;
        }
        return false;
    }


    @Override
    public void refreshList(List<NavigationBean.DataBean> dataBeans) {
        this.dataBeans.clear();
        this.dataBeans.addAll(dataBeans);
        leftAdapter.notifyDataSetChanged();
        rightAdapter.notifyDataSetChanged();
    }
}
