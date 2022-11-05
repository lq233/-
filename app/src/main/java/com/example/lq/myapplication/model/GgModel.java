package com.example.lq.myapplication.model;

import com.example.lq.myapplication.bean.GgBean;
import com.example.lq.myapplication.bean.GgItemBean;
import com.example.lq.myapplication.http.ApiManager;
import com.liangxq.callback.BaseResponce;
import com.liangxq.callback.ObServerCallback;
import com.liangxq.utils.HttpUtils;

import java.util.List;

/**
 * Created by 003 on 2019/5/5.
 */

public class GgModel {
    public interface CallBack {
        void getData(List<GgBean> list);
    }


    public void getData(final CallBack callBack) {
        ApiManager.getApiManager().getGgServer()
                .getGg()
                .compose(HttpUtils.<BaseResponce<List<GgBean>>>rxShcdule())
                .compose(HttpUtils.<List<GgBean>>handResult())
                .subscribe(new ObServerCallback<List<GgBean>>() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onNext(List<GgBean> list) {
                        callBack.getData(list);
                    }
                });
    }


}
