package com.example.lq.myapplication.fragment;


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
import com.example.lq.myapplication.adapter.KnowRlvAdaopter;
import com.example.lq.myapplication.base.BaseFragment;
import com.example.lq.myapplication.bean.KnowBean;
import com.example.lq.myapplication.presnter.KnowPresnter;
import com.example.lq.myapplication.utils.HindMain;
import com.example.lq.myapplication.view.KonwView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class KonwFragment extends BaseFragment<KonwView, KnowPresnter<KonwView>> implements KonwView {

    private static final String TAG = "kk";
    @BindView(R.id.know_rlv)
    RecyclerView mKnowRlv;
    @BindView(R.id.floatbutton)
    FloatingActionButton mFloatbutton;
    Unbinder unbinder;
    private KnowRlvAdaopter mAdaopter;


    @Override
    public void initData() {
        mFloatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKnowRlv.smoothScrollToPosition(0);
            }
        });
        mKnowRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.getData();

        ArrayList<KnowBean> beans = new ArrayList<>();
        mAdaopter = new KnowRlvAdaopter(getContext(), beans);
        mKnowRlv.setAdapter(mAdaopter);
        //滑动隐藏
        RadioGroup rg = getActivity().findViewById(R.id.rp);
        //  FloatingActionButton flb = getActivity().findViewById(R.id.floatbutton);
        HindMain.hind(mKnowRlv, rg, mFloatbutton);
    }

    @Override
    public void showProgressbar() {
    }

    @Override
    public void hideProgressBar() {
    }

    @Override
    public void show(List<KnowBean> knowBean) {
        mAdaopter.addData(knowBean);
    }

    @Override
    public void showError(String error) {
    }

    @Override
    protected KnowPresnter<KonwView> initPresenter() {
        return new KnowPresnter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_know;
    }

}
