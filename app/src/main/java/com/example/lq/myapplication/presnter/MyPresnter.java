package com.example.lq.myapplication.presnter;

import android.util.Log;

import com.example.lq.myapplication.base.BasePresnter;
import com.example.lq.myapplication.bean.BannerBean;
import com.example.lq.myapplication.bean.ListData;
import com.example.lq.myapplication.model.MyModel;
import com.example.lq.myapplication.view.MyView;

import java.util.List;


/**
 * Created by 003 on 2019/4/29.
 */
public class MyPresnter<V extends MyView> extends BasePresnter<V> implements MyModel.Callback, MyModel.Callback2 {
    private static final String TAG = "MyPresnter";
    public MyModel mMyModel = new MyModel();

    public void getData(int page) {
        Log.e(TAG, "getData: "+"运行了1" );
        mMyModel.getData(this,page);
        Log.e(TAG, "getData: "+"运行了2" );
        mMyModel.getBannerData(this);

    }

    @Override
    public void getData(ListData listData) {
        mView.show(listData);
        Log.e(TAG, "getData: " + listData.toString());
        mView.hideProgressBar();
    }

    @Override
    public void getBannerData(List<BannerBean> bannerBean) {

        mView.showBanner(bannerBean);
        mView.hideProgressBar();
    }
}
