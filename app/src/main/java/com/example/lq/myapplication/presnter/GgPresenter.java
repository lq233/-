package com.example.lq.myapplication.presnter;

import com.example.lq.myapplication.base.BasePresnter;
import com.example.lq.myapplication.bean.GgBean;
import com.example.lq.myapplication.model.GgModel;
import com.example.lq.myapplication.view.GgView;

import java.util.List;

/**
 * Created by 003 on 2019/5/5.
 */

public class GgPresenter<V extends GgView> extends BasePresnter<V> implements GgModel.CallBack {
    public GgModel mModel = new GgModel();

    public void getData(){
        mModel.getData(this);
        mView.showProgressbar();
    }

    @Override
    public void getData(List<GgBean> list) {
        mView.onSuccess(list);
        mView.hideProgressBar();
    }
}
