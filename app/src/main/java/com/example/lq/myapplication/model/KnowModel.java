package com.example.lq.myapplication.model;

import android.util.Log;

import com.example.lq.myapplication.bean.KnowBean;
import com.example.lq.myapplication.bean.ListData;
import com.example.lq.myapplication.http.ApiManager;
import com.liangxq.callback.BaseResponce;
import com.liangxq.callback.ObServerCallback;
import com.liangxq.utils.HttpUtils;

import java.util.List;

/**
 * Created by 003 on 2019/4/29.
 */

public class KnowModel {
    public interface Callback {
        void getData(List<KnowBean> knowBean);
    }

    public void getData(final Callback callback) {
        ApiManager.getApiManager().getknow()
                .getKnow()
                .compose(HttpUtils.<BaseResponce<List<KnowBean>>>rxShcdule())
                .compose(HttpUtils.<List<KnowBean>>handResult())
                .subscribe(new ObServerCallback<List<KnowBean>>() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onNext(List<KnowBean> listData) {
                        Log.d("kk", "onNext: "+listData.toString());
                        callback.getData(listData);
                    }
                });
    }
}
