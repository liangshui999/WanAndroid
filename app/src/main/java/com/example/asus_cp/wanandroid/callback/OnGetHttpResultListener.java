package com.example.asus_cp.wanandroid.callback;

/**
 * http请求的回调
 */
public interface OnGetHttpResultListener<T> {

    void onGetResult(T result);

    void onError(Throwable e);
}
