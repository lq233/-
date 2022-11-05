package com.example.lq.myapplication.http;

import com.example.lq.myapplication.bean.GgBean;
import com.example.lq.myapplication.bean.GgItemBean;
import com.liangxq.callback.BaseResponce;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by 003 on 2019/5/5.
 */

public interface GgServer {
    public String url = "https://wanandroid.com/wxarticle/";

    //list/408/1/json
    @GET("chapters/json")
    Observable<BaseResponce<List<GgBean>>> getGg();

    @GET
    Observable<BaseResponce<GgItemBean>> getGgItem(@Url String url);

}
