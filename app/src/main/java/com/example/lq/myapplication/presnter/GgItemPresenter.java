package com.example.lq.myapplication.presnter;

import com.example.lq.myapplication.base.BasePresnter;
import com.example.lq.myapplication.bean.GgBean;
import com.example.lq.myapplication.bean.GgItemBean;
import com.example.lq.myapplication.model.GgItemModel;
import com.example.lq.myapplication.model.GgModel;
import com.example.lq.myapplication.view.GgItemView;
import com.example.lq.myapplication.view.GgView;

import java.util.List;

/**
 * Created by 003 on 2019/5/5.
 */

public class GgItemPresenter<V extends GgItemView> extends BasePresnter<V> implements GgItemModel.CallBack2, GgItemModel.CallBack3 {
    public GgItemModel mModel = new GgItemModel();

    public void getData(String url) {
        mModel.getItem(this, url);
        mView.showProgressbar();
    }

    public void getSecondData(String url) {
        mModel.getSecondItem(this, url);
    }

    @Override
    public void getData(GgItemBean itemBean) {
        mView.onItemSuccess(itemBean);
        mView.hideProgressBar();
        mView.onShow(itemBean);
    }
}
