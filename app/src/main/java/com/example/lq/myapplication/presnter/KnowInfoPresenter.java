package com.example.lq.myapplication.presnter;

import com.example.lq.myapplication.base.BasePresnter;
import com.example.lq.myapplication.bean.KnowInfoBean;
import com.example.lq.myapplication.bean.ProjectItemBean;
import com.example.lq.myapplication.model.KonwInfoModel;
import com.example.lq.myapplication.model.ProjectItemModel;
import com.example.lq.myapplication.view.KnowInfoView;
import com.example.lq.myapplication.view.ProjectItemView;

/**
 * Created by 003 on 2019/5/5.
 */

public class KnowInfoPresenter<V extends KnowInfoView> extends BasePresnter<V> implements KonwInfoModel.Callback {
    public KonwInfoModel mModel = new KonwInfoModel();

    public void getData(String url) {
        mModel.getData(this, url);
        mView.showProgressbar();
    }


    @Override
    public void getData(KnowInfoBean list) {
        mView.onSuccess(list);
        mView.hideProgressBar();
    }
}
