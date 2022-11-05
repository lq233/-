package com.example.lq.myapplication.presnter;

import android.util.Log;

import com.example.lq.myapplication.base.BasePresnter;
import com.example.lq.myapplication.bean.KnowBean;
import com.example.lq.myapplication.bean.ListData;
import com.example.lq.myapplication.model.KnowModel;
import com.example.lq.myapplication.model.MyModel;
import com.example.lq.myapplication.view.KonwView;
import com.example.lq.myapplication.view.MyView;

import java.util.List;


/**
 * Created by 003 on 2019/4/29.
 */
public class KnowPresnter<V extends KonwView> extends BasePresnter<V> implements KnowModel.Callback {
    private static final String TAG = "MyPresnter";
    public KnowModel mKnowModel = new KnowModel();

    public void getData() {
        mKnowModel.getData(this);
        mView.showProgressbar();
    }


    @Override
    public void getData(List<KnowBean> knowBean) {
        mView.show(knowBean);
        mView.hideProgressBar();
    }
}
