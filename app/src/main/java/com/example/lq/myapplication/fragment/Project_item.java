package com.example.lq.myapplication.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.adapter.ProItemRlvAdaopter;
import com.example.lq.myapplication.base.BaseFragment;
import com.example.lq.myapplication.bean.ProjectItemBean;
import com.example.lq.myapplication.presnter.ProjectItemPresenter;
import com.example.lq.myapplication.utils.HindMain;
import com.example.lq.myapplication.utils.Switchover;
import com.example.lq.myapplication.view.ProjectItemView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Project_item extends BaseFragment<ProjectItemView, ProjectItemPresenter<ProjectItemView>> implements ProjectItemView {


    private final int cid;
    @BindView(R.id.projectitem_rlv)
    RecyclerView mProjectitemRlv;
    Unbinder unbinder;
    @BindView(R.id.pro_smart)
    SmartRefreshLayout mProSmart;
    @BindView(R.id.floatbutton)
    FloatingActionButton mFloatbutton;
    private ProItemRlvAdaopter mAdaopter;
    private int page;

    public Project_item(int id) {
        // Required empty public constructor
        cid = id;
    }

    @Override
    public void initView() {
        //滑动隐藏
        RadioGroup rg = getActivity().findViewById(R.id.rp);
        //   FloatingActionButton flb = getActivity().findViewById(R.id.floatbutton);
        HindMain.hind(mProjectitemRlv, rg, mFloatbutton);

        mFloatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProjectitemRlv.smoothScrollToPosition(0);
            }
        });
        mProjectitemRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<ProjectItemBean.DatasBean> list = new ArrayList<>();
        mAdaopter = new ProItemRlvAdaopter(getContext(), list);
        mProjectitemRlv.setAdapter(mAdaopter);
        mProSmart.setRefreshHeader(new MaterialHeader(getContext()).setShowBezierWave(true));
        mProSmart.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        mProSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mAdaopter.mBeans.clear();
                mAdaopter.notifyDataSetChanged();
                initData();
            }
        });
    }


    @Override
    public void initData() {
        super.initData();
        presenter.getData("project/list/" + page + "/json?cid=" + cid);
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onSuccess(ProjectItemBean list) {
        Log.d("jj", "onSuccess: " + list.toString());
        mAdaopter.addData(list);
        mProSmart.finishRefresh();
        mProSmart.finishLoadMore();
    }

    @Override
    public void onFail(String error) {

    }

    @Override
    protected ProjectItemPresenter<ProjectItemView> initPresenter() {
        return new ProjectItemPresenter<>();
    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_project_item;
    }

}
