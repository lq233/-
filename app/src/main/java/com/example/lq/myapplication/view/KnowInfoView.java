package com.example.lq.myapplication.view;

import com.example.lq.myapplication.base.BaseView;
import com.example.lq.myapplication.bean.KnowInfoBean;
import com.example.lq.myapplication.bean.ProjectItemBean;

/**
 * Created by 003 on 2019/5/5.
 */

public interface KnowInfoView extends BaseView{
    void onSuccess(KnowInfoBean list);
    void onFail(String error);
}
