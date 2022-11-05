package com.example.lq.myapplication.view;

import com.example.lq.myapplication.base.BaseView;
import com.example.lq.myapplication.bean.BannerBean;
import com.example.lq.myapplication.bean.KnowBean;

import java.util.List;

/**
 * Created by 003 on 2019/4/29.
 */

public interface KonwView extends BaseView{
    void show(List<KnowBean> knowBean);

    void showError(String error);

}
