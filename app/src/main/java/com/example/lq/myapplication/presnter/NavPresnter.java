package com.example.lq.myapplication.presnter;

import com.example.lq.myapplication.base.BasePresnter;
import com.example.lq.myapplication.bean.NaviBean;
import com.example.lq.myapplication.model.NavModel;
import com.example.lq.myapplication.view.NaiView;

/**
 * Created by 003 on 2019/5/7.
 */

public class NavPresnter<V extends NaiView> extends BasePresnter<V> implements NavModel.CallBack {

    public NavModel mModel = new NavModel();

    public void getData() {
        mModel.getNav(this);
    }


    @Override
    public void getNav(NaviBean bean) {
        mView.onShow(bean);
    }
}
