package com.example.lq.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 003 on 2019/4/29.
 */

public abstract class SimpleActivity extends AppCompatActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(creatLayout(), null);
        setContentView(view);
        mUnbinder = ButterKnife.bind(this);
        viewCreate(view);
        initData();
    }

    public void viewCreate(View view) {

    }

    protected abstract void initData();

    protected abstract int creatLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder == null) {
            mUnbinder.unbind();
        }
    }
}
