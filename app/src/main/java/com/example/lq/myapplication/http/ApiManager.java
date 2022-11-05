package com.example.lq.myapplication.http;

import com.example.lq.myapplication.app.MyApp;
import com.liangxq.mylibrary.HttpManager;

/**
 * Created by 003 on 2019/4/29.
 */

public class ApiManager {
    private static volatile ApiManager sApiManager;

    private ApiManager() {

    }

    public static ApiManager getApiManager() {
        if (sApiManager == null) {
            synchronized (ApiManager.class) {
                if (sApiManager == null) {
                    sApiManager = new ApiManager();
                }
            }
        }
        return sApiManager;
    }

    public ApiServer getServer() {
        return HttpManager.getInstance().getRetrofitClient(ApiServer.URL, MyApp.getMyApp()).create(ApiServer.class);
    }

    public ApiServer getBannerServer() {
        return HttpManager.getInstance().getRetrofitClient(ApiServer.Url2, MyApp.getMyApp()).create(ApiServer.class);
    }

    public KonwServer getknow() {
        return HttpManager.getInstance().getRetrofitClient(KonwServer.Urls, MyApp.getMyApp()).create(KonwServer.class);
    }
    public ApiServer getProjectItem() {
        return HttpManager.getInstance().getRetrofitClient(ApiServer.Url2, MyApp.getMyApp()).create(ApiServer.class);
    }
    public KonwServer getKnowInfo() {
        return HttpManager.getInstance().getRetrofitClient(KonwServer.Urls, MyApp.getMyApp()).create(KonwServer.class);
    }
    public GgServer getGgServer() {
        return HttpManager.getInstance().getRetrofitClient(GgServer.url, MyApp.getMyApp()).create(GgServer.class);
    }

}
