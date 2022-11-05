package com.example.lq.myapplication.fragment;


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
import com.example.lq.myapplication.adapter.HomeRlvAdaopter;
import com.example.lq.myapplication.base.BaseFragment;
import com.example.lq.myapplication.bean.BannerBean;
import com.example.lq.myapplication.bean.ListData;
import com.example.lq.myapplication.presnter.MyPresnter;
import com.example.lq.myapplication.utils.HindMain;
import com.example.lq.myapplication.view.MyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<MyView, MyPresnter<MyView>> implements MyView {

    private static final String TAG = "HomeFragment";
    @BindView(R.id.home_rlv)
    RecyclerView mHomeRlv;
    @BindView(R.id.home_smart)
    SmartRefreshLayout mHomeSmart;
    @BindView(R.id.floatbutton)
    FloatingActionButton mFloatbutton;
    Unbinder unbinder;
    private HomeRlvAdaopter mAdaopter;
    private int mPage = 1;


    @Override
    public void initData() {

        presenter.getData(mPage);


    }

    @Override
    public void initView() {
        //滑动隐藏
        RadioGroup rg = getActivity().findViewById(R.id.rp);
        // FloatingActionButton flb = getActivity().findViewById(R.id.floatbutton);
        HindMain.hind(mHomeRlv, rg, mFloatbutton);

        mHomeRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<ListData.DatasBean> beans = new ArrayList<>();
        ArrayList<BannerBean> banner = new ArrayList<>();
        mAdaopter = new HomeRlvAdaopter(getContext(), beans, banner);
        mHomeRlv.setAdapter(mAdaopter);
        mFloatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomeRlv.smoothScrollToPosition(0);
            }
        });
/*        mHomeSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
               mAdaopter.mBeans.clear();
               mAdaopter.mBanners.clear();
               initData();
               mAdaopter.notifyDataSetChanged();
            }
        });*/
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void show(ListData listData) {
        Log.e(TAG, "show: " + listData.toString());
        mAdaopter.addData(listData);

    }

    @Override
    public void showBanner(List<BannerBean> bannerBean) {
        mAdaopter.addBanner(bannerBean);
        Log.d("ii", "showBanner: " + bannerBean.get(0).toString());

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void errorBanner(String error) {

    }


    @Override
    protected MyPresnter<MyView> initPresenter() {
        return new MyPresnter<>();
    }

    @Override
    public void initListener() {

    }

    @Override
    protected int getLayout() {

        return R.layout.fragment_home;
    }
}
