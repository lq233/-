package com.example.lq.myapplication.presnter;

import com.example.lq.myapplication.base.BasePresnter;
import com.example.lq.myapplication.bean.ProjectItemBean;
import com.example.lq.myapplication.model.ProjectItemModel;
import com.example.lq.myapplication.view.ProjectItemView;

import java.util.List;

/**
 * Created by 003 on 2019/5/5.
 */

public class ProjectItemPresenter<V extends ProjectItemView> extends BasePresnter<V> implements ProjectItemModel.Callback {
    public ProjectItemModel mModel = new ProjectItemModel();

    public void getData(String url) {
        mModel.getData(this, url);
        mView.showProgressbar();
    }


    @Override
    public void getData(ProjectItemBean list) {
        mView.onSuccess(list);
        mView.hideProgressBar();
    }
}
