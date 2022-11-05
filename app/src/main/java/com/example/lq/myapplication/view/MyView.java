package com.example.lq.myapplication.view;

import com.example.lq.myapplication.base.BaseView;
import com.example.lq.myapplication.bean.BannerBean;
import com.example.lq.myapplication.bean.ListData;

import java.util.List;

/**
 * Created by 003 on 2019/4/29.
 */

public interface MyView extends BaseView {
    void show(ListData listData);

    void showBanner(List<BannerBean> bannerBean);

    void showError(String error);

    void errorBanner(String error);
}
