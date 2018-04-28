package com.example.asus_cp.wanandroid.view;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.activity.BaseActivity;
import com.example.asus_cp.wanandroid.contract.MainContract;
import com.example.asus_cp.wanandroid.util.BottomNavigationViewUtils;
import com.example.asus_cp.wanandroid.view.knowledge_architecture.KnowledgeArchitectureFragment;
import com.example.asus_cp.wanandroid.view.main.MainPagerFragment;
import com.example.asus_cp.wanandroid.view.navigation.NavigationFragment;
import com.example.asus_cp.wanandroid.view.project.ProjectFragment;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.Lazy;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.normal)
    FrameLayout mContainer;

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Inject
    MainPagerFragment mMainPagerFragment;

    @Inject
    KnowledgeArchitectureFragment mKnowledgeArchitectureFragment;

    @Inject
    NavigationFragment mNavigationFragment;

    @Inject
    ProjectFragment mProjectFragment;

    /**
     * 侧滑菜单是否打开，默认是false
     */
    private boolean mIsMenuOpen;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BottomNavigationViewUtils.disableShiftMode(mBottomNavigationView);
        initToolBar();
        initContent();
    }

    private void initContent() {
        mFragmentManager = getSupportFragmentManager();
        addFragment(mMainPagerFragment);
        mBottomNavigationView.setOnNavigationItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.bottom_nav_main:
                    replaceFragment(mMainPagerFragment);
                    break;
                case R.id.bottom_nav_knowledge_architecture:
                    replaceFragment(mKnowledgeArchitectureFragment);
                    break;
                case R.id.bottom_nav_navigation:
                    replaceFragment(mNavigationFragment);
                    break;
                case R.id.bottom_nav_project:
                    replaceFragment(mProjectFragment);
                    break;
            }
            return true;
        });
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.normal, fragment);
        transaction.commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.normal, fragment);
        transaction.commit();
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {
        mToolbar.setTitle("");
        mTitle.setText(R.string.main);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar == null){
            return;
        }
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,
                R.string.open, R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mIsMenuOpen = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mIsMenuOpen = false;
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = mDrawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        actionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        //不设置这个事件，点击toolbar是打不开侧滑菜单的
        mToolbar.setNavigationOnClickListener(v -> {
            if(mIsMenuOpen){
                mDrawerLayout.closeDrawer(Gravity.START);
            }else{
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }


}
