package com.example.lq.myapplication.view;

import com.example.lq.myapplication.base.BaseView;
import com.example.lq.myapplication.bean.GgBean;
import com.example.lq.myapplication.bean.GgItemBean;

import java.util.List;

/**
 * Created by 003 on 2019/5/5.
 */

public interface GgView extends BaseView {
    void onSuccess(List<GgBean> list);

    void onFail(String string);

}
