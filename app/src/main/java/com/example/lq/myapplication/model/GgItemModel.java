package com.example.lq.myapplication.model;

import com.example.lq.myapplication.bean.GgItemBean;
import com.example.lq.myapplication.http.ApiManager;
import com.liangxq.callback.BaseResponce;
import com.liangxq.callback.ObServerCallback;
import com.liangxq.utils.HttpUtils;

/**
 * Created by 003 on 2019/5/5.
 */

public class GgItemModel {
    public interface CallBack2 {
        void getData(GgItemBean itemBean);
    }
    public interface CallBack3 {
        void getData(GgItemBean itemBean);
    }

    public void getSecondItem(final CallBack3 callBack2, String url) {
        ApiManager.getApiManager().getGgServer().getGgItem(url)
                .compose(HttpUtils.<BaseResponce<GgItemBean>>rxShcdule())
                .compose(HttpUtils.<GgItemBean>handResult())
                .subscribe(new ObServerCallback<GgItemBean>() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onNext(GgItemBean itemBean) {
                        callBack2.getData(itemBean);
                    }
                });
    }
    public void getItem(final CallBack2 callBack2, String url) {
        ApiManager.getApiManager().getGgServer().getGgItem(url)
                .compose(HttpUtils.<BaseResponce<GgItemBean>>rxShcdule())
                .compose(HttpUtils.<GgItemBean>handResult())
                .subscribe(new ObServerCallback<GgItemBean>() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onNext(GgItemBean itemBean) {
                        callBack2.getData(itemBean);
                    }
                });
    }
}
