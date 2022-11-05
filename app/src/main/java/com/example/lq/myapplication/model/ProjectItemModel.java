package com.example.lq.myapplication.model;

import com.example.lq.myapplication.bean.ProjectItemBean;
import com.example.lq.myapplication.http.ApiManager;
import com.liangxq.callback.BaseResponce;
import com.liangxq.callback.ObServerCallback;
import com.liangxq.utils.HttpUtils;

import java.util.List;

/**
 * Created by 003 on 2019/5/5.
 */

public class ProjectItemModel {
    public interface Callback {
        void getData(ProjectItemBean list);
    }

    public void getData(final Callback callback, String url) {
        ApiManager.getApiManager().getProjectItem()
                .getProjectItem(url)
                .compose(HttpUtils.<BaseResponce<ProjectItemBean>>rxShcdule())
                .compose(HttpUtils.<ProjectItemBean>handResult())
                .subscribe(new ObServerCallback<ProjectItemBean>() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onNext(ProjectItemBean list) {
                        callback.getData(list);
                    }
                });
    }
}
