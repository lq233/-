package com.example.lq.myapplication.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.adapter.KnowItemRlvAdaopter;
import com.example.lq.myapplication.base.BaseFragment;
import com.example.lq.myapplication.bean.KnowInfoBean;
import com.example.lq.myapplication.presnter.KnowInfoPresenter;
import com.example.lq.myapplication.utils.HindMain;
import com.example.lq.myapplication.view.KnowInfoView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class KnowItemFragment extends BaseFragment<KnowInfoView, KnowInfoPresenter<KnowInfoView>> implements KnowInfoView {


    private final int mCid;
    @BindView(R.id.knowI_rlv)
    RecyclerView mKnowIRlv;
    @BindView(R.id.konw_smart)
    SmartRefreshLayout mKonwSmart;
    Unbinder unbinder;
    private String id;
    private KnowItemRlvAdaopter mAdaopter;

    public KnowItemFragment(int id) {
        // Required empty public constructor
        mCid = id;
    }

    @Override
    public void initView() {

        mKnowIRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<KnowInfoBean.DatasBean> beans = new ArrayList<>();
        mAdaopter = new KnowItemRlvAdaopter(getContext(), beans);
        mKnowIRlv.setAdapter(mAdaopter);
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getData("article/list/0/json?cid=" + mCid);
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onSuccess(KnowInfoBean list) {
        mAdaopter.addData(list);
    }

    @Override
    public void onFail(String error) {

    }

    @Override
    protected KnowInfoPresenter<KnowInfoView> initPresenter() {
        return new KnowInfoPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_know_item;
    }

}
