package com.example.lq.myapplication.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.adapter.GgItemRlvAdaopter;
import com.example.lq.myapplication.base.BaseFragment;
import com.example.lq.myapplication.bean.GgItemBean;
import com.example.lq.myapplication.presnter.GgItemPresenter;
import com.example.lq.myapplication.utils.HindMain;
import com.example.lq.myapplication.view.GgItemView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 003 on 2019/5/5.
 */

@SuppressLint("ValidFragment")
public class GgFragment extends BaseFragment<GgItemView, GgItemPresenter<GgItemView>> implements GgItemView {
    private final int mCid;
    private final String mName;
    @BindView(R.id.gg_rlv)
    RecyclerView mGgRlv;
    @BindView(R.id.gg_smart)
    SmartRefreshLayout mGgSmart;
    @BindView(R.id.gg_ed)
    EditText mEdGg;
    @BindView(R.id.gg_button)
    Button mGgButton;
    Unbinder unbinder;
    @BindView(R.id.floatbutton)
    FloatingActionButton mFloatbutton;
    private GgItemRlvAdaopter mAdaopter;
    private String mType;
    private int page;

    //wxarticle/list/409/1/json?k=java

    public GgFragment(int id, String s) {
        mCid = id;
        mName = s;
    }

    @Override
    public void initView() {
        super.initView();
        //滑动隐藏
        RadioGroup rg = getActivity().findViewById(R.id.rp);
        // FloatingActionButton flb = getActivity().findViewById(R.id.floatbutton);
        HindMain.hind(mGgRlv, rg, mFloatbutton);

        mFloatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGgRlv.smoothScrollToPosition(0);
            }
        });

        mEdGg.setHint(mName + "带你发现更多干货");

        mGgRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<GgItemBean.DatasBean> beans = new ArrayList<>();
        mAdaopter = new GgItemRlvAdaopter(getContext(), beans);
        mGgRlv.setAdapter(mAdaopter);
        mGgSmart.setRefreshHeader(new MaterialHeader(getContext()).setShowBezierWave(true));
        mGgSmart.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        mGgSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
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
        presenter.getData("list/" + mCid + "/" + page + "/json");

    }

    @Override
    protected GgItemPresenter<GgItemView> initPresenter() {
        return new GgItemPresenter<>();
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onItemSuccess(GgItemBean itemBean) {
        mAdaopter.addData(itemBean);
        mGgSmart.finishRefresh();
        mGgSmart.finishLoadMore();
    }

    @Override
    public void onShow(GgItemBean itemBean) {
        mAdaopter.mBeans.clear();
        mAdaopter.notifyDataSetChanged();
        mAdaopter.addSecond(itemBean.getDatas());
    }

    @Override
    public void onItemFail() {

    }


    @Override
    protected int getLayout() {
        return R.layout.gg_item;
    }

    @OnClick(R.id.gg_button)
    public void onViewClicked() {
        mType = mEdGg.getText().toString();
        presenter.getSecondData("list/" + mCid + "/1/json?k=" + mType);
    }
}
