package com.example.lq.myapplication.model;

import android.util.Log;

import com.example.lq.myapplication.bean.BannerBean;
import com.example.lq.myapplication.bean.ListData;
import com.example.lq.myapplication.http.ApiManager;
import com.liangxq.callback.BaseResponce;
import com.liangxq.callback.ObServerCallback;
import com.liangxq.utils.HttpUtils;

import java.util.List;

/**
 * Created by 003 on 2019/4/29.
 */

public class MyModel {
    private String TAG = "MyModel";

    public interface Callback {
        void getData(ListData listData);
    }

    public interface Callback2 {
        void getBannerData(List<BannerBean> bannerBean);
    }

    public void getData(final Callback callback,int page) {
        ApiManager.getApiManager().getServer()
                .getArticleList("article/list/"+page+"/json")
                .compose(HttpUtils.<BaseResponce<ListData>>rxShcdule())
                .compose(HttpUtils.<ListData>handResult())
                .subscribe(new ObServerCallback<ListData>() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onNext(ListData listData) {

                        callback.getData(listData);
                    }
                });
    }

    public void getBannerData(final Callback2 callback2) {
        ApiManager.getApiManager().getBannerServer().getBanner()
                .compose(HttpUtils.<BaseResponce<List<BannerBean>>>rxShcdule())
                .compose(HttpUtils.<List<BannerBean>>handResult())
                .subscribe(new ObServerCallback<List<BannerBean>>() {
                    @Override
                    public void onError(String message) {
                        Log.e(TAG, "onError11111111111: " + message);
                    }

                    @Override
                    public void onNext(List<BannerBean> bannerBean) {
                        callback2.getBannerData(bannerBean);
                    }
                });
    }
}
