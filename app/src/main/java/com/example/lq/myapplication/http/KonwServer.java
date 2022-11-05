package com.example.lq.myapplication.http;

import com.example.lq.myapplication.bean.KnowBean;
import com.example.lq.myapplication.bean.KnowInfoBean;
import com.example.lq.myapplication.bean.KonwTabBean;
import com.liangxq.callback.BaseResponce;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by 003 on 2019/4/29.
 */

public interface KonwServer {
    public String Urls = "https://www.wanandroid.com/";
   //article/list/0/json?cid=60
    //wxarticle/list/409/1/json?k=java
    @GET("tree/json")
    Observable<BaseResponce<List<KnowBean>>> getKnow();

    @GET("tree/json")
    Observable<KonwTabBean> getTab();

    @GET()
    Observable<BaseResponce<KnowInfoBean>> getInfo(@Url String url);
}
