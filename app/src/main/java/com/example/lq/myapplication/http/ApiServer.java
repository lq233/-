package com.example.lq.myapplication.http;

import com.example.lq.myapplication.bean.AlwaysBean;
import com.example.lq.myapplication.bean.BannerBean;
import com.example.lq.myapplication.bean.ListData;
import com.example.lq.myapplication.bean.NaviBean;
import com.example.lq.myapplication.bean.ProjectItemBean;
import com.example.lq.myapplication.bean.ProjectTabBean;
import com.example.lq.myapplication.bean.SearchBean;
import com.liangxq.callback.BaseResponce;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by 003 on 2019/4/29.
 */

public interface ApiServer {
    String URL = "https://www.wanandroid.com/";
    String Url2 = "http://www.wanandroid.com/";

    //https://www.wanandroid.com/
    //project/list/1/json?cid=294
    @GET()
    Observable<BaseResponce<ListData>> getArticleList(@Url String page);

    @GET("banner/json")
    Observable<BaseResponce<List<BannerBean>>> getBanner();

    @GET("project/tree/json")
    Observable<ProjectTabBean> getProjectTab();

    @GET
    Observable<BaseResponce<ProjectItemBean>> getProjectItem(@Url String url2);

    @GET("navi/json")
    Observable<NaviBean> getNaiBean();
    //https://www.wanandroid.com/hotkey/json

    @GET("hotkey/json")
    Observable<SearchBean> getSearch();
    //https://www.wanandroid.com/
    @GET("friend/json")
    Observable<AlwaysBean> getAlways();
}
