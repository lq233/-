package com.example.lq.myapplication.view;

import com.example.lq.myapplication.base.BaseView;
import com.example.lq.myapplication.bean.ProjectItemBean;

import java.util.List;

/**
 * Created by 003 on 2019/5/5.
 */

public interface ProjectItemView extends BaseView{
    void onSuccess(ProjectItemBean list);
    void onFail(String error);
}
