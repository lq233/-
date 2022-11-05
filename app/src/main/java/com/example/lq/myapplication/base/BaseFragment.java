package com.example.lq.myapplication.base;

import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 003 on 2019/4/30.
 */

public abstract class BaseFragment<V, P extends BasePresnter<V>> extends Fragment {

    private Unbinder mUnbinder;
    public P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), null);
        mUnbinder = ButterKnife.bind(this, view);
        presenter = initPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
        initView();
        initListener();
        initData();
        return view;
    }

    public void initListener() {

    }

    public void initView() {

    }

    public void initData() {

    }

    protected abstract P initPresenter();

    protected abstract int getLayout();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        presenter.deachView();
        presenter = null;
    }
}
