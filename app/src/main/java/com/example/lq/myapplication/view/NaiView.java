package com.example.lq.myapplication.view;

import com.example.lq.myapplication.base.BaseView;
import com.example.lq.myapplication.bean.NaviBean;

/**
 * Created by 003 on 2019/5/7.
 */

public interface NaiView extends BaseView{
    void onShow(NaviBean bean);

    void onFail(String str);
}
