package com.example.lq.myapplication.activity;

import android.util.Log;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.base.BaseActivity;
import com.example.lq.myapplication.bean.BannerBean;
import com.example.lq.myapplication.bean.ListData;
import com.example.lq.myapplication.presnter.MyPresnter;
import com.example.lq.myapplication.view.MyView;

import java.util.List;

public class TextActivity extends BaseActivity<MyView, MyPresnter<MyView>> implements MyView {

    private int page=1;
    private static final String TAG = "TextActivity";

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void show(ListData listData) {
        Log.e(TAG, "show: "+listData.toString() );
    }

    @Override
    public void showBanner(List<BannerBean> bannerBean) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void errorBanner(String error) {

    }

    @Override
    protected MyPresnter<MyView> createPresenter() {
        return new MyPresnter<>();
    }

    @Override
    protected void initData() {
        presenter.getData(page);
    }

    @Override
    protected int creatLayout() {
        return R.layout.activity_text;
    }
}
