package com.example.lq.myapplication.model;

import com.example.lq.myapplication.bean.KnowInfoBean;
import com.example.lq.myapplication.bean.ProjectItemBean;
import com.example.lq.myapplication.http.ApiManager;
import com.liangxq.callback.BaseResponce;
import com.liangxq.callback.ObServerCallback;
import com.liangxq.utils.HttpUtils;

/**
 * Created by 003 on 2019/5/5.
 */

public class KonwInfoModel {
    public interface Callback {
        void getData(KnowInfoBean list);
    }

    public void getData(final Callback callback, String url) {
        ApiManager.getApiManager().getKnowInfo()
                .getInfo(url)
                .compose(HttpUtils.<BaseResponce<KnowInfoBean>>rxShcdule())
                .compose(HttpUtils.<KnowInfoBean>handResult())
                .subscribe(new ObServerCallback<KnowInfoBean>() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onNext(KnowInfoBean list) {
                        callback.getData(list);
                    }
                });
    }
}
