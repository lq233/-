package com.example.lq.myapplication.model;

import android.util.Log;

import com.example.lq.myapplication.bean.NaviBean;
import com.example.lq.myapplication.http.ApiServer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 003 on 2019/5/7.
 */

public class NavModel {
    public interface CallBack {
        void getNav(NaviBean bean);
    }

    public void getNav(final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiServer.URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        ApiServer server = retrofit.create(ApiServer.class);
        Observable<NaviBean> bean = server.getNaiBean();
        bean.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<NaviBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NaviBean bean) {
                        callBack.getNav(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("pp", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
