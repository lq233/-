package com.example.lq.myapplication.http;

import com.example.lq.myapplication.bean.NaviBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 003 on 2019/5/6.
 */

public interface NavServer {
    public String Url = "https://www.wanandroid.com/navi/";

    @GET("json")
    Observable<NaviBean> getNav();
}
