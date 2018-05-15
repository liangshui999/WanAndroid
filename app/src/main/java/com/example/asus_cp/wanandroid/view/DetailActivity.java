package com.example.asus_cp.wanandroid.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.asus_cp.wanandroid.R;
import com.example.asus_cp.wanandroid.base.activity.BaseActivity;
import com.example.asus_cp.wanandroid.constant.IntentConstant;
import com.example.asus_cp.wanandroid.contract.DetailContract;
import com.example.asus_cp.wanandroid.util.MyLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity<DetailContract.Presenter> implements DetailContract.View {


    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.webview)
    WebView webview;

    private String url;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra(IntentConstant.KEY_TO_DETAIL_ACTIVITY_URL);
        setSupportActionBar(toolbar);

        //初始化webview
        initWebView();
    }


    /**
     * 初始化webview
     */
    private void initWebView() {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webview.canGoBack();
        webview.canGoForward();

        log("url = " + url);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                showLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                showNormal();
            }
        });

        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String titleStr) {
                log("titleStr = " + titleStr);
                DetailActivity.this.title.setText(titleStr);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(webview != null){
            webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webview.clearHistory();
            ViewGroup parent = (ViewGroup) webview.getParent();
            parent.removeView(webview);
            webview.destroy();
            webview = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()){
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
