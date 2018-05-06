package com.example.asus_cp.wanandroid.net.http.bean;

public class Response<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
