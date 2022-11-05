package com.example.lq.myapplication.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.base.BaseActivity;
import com.example.lq.myapplication.presnter.AboutP;
import com.example.lq.myapplication.view.AboutV;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.flyrefresh.FlyView;
import com.scwang.smartrefresh.header.flyrefresh.MountainSceneView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AbuoutUsActivity extends BaseActivity<AboutV, AboutP<AboutV>> implements AboutV {


    @BindView(R.id.about_us_mountain)
    MountainSceneView mAboutUsMountain;
    @BindView(R.id.about_us_toolbar)
    Toolbar mAboutUsToolbar;
    @BindView(R.id.about_us_toolbar_layout)
    CollapsingToolbarLayout mAboutUsToolbarLayout;
    @BindView(R.id.about_us_app_bar)
    AppBarLayout mAboutUsAppBar;
    @BindView(R.id.about_us_fly_refresh)
    FlyRefreshHeader mAboutUsFlyRefresh;
    @BindView(R.id.aboutVersion)
    TextView mAboutVersion;
    @BindView(R.id.aboutContent)
    TextView mAboutContent;
    @BindView(R.id.about_us_content)
    NestedScrollView mAboutUsContent;
    @BindView(R.id.about_us_refresh_layout)
    SmartRefreshLayout mAboutUsRefreshLayout;
    @BindView(R.id.about_us_fab)
    FloatingActionButton mAboutUsFab;
    @BindView(R.id.about_us_fly_view)
    FlyView mAboutUsFlyView;

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    protected AboutP<AboutV> createPresenter() {
        return new AboutP<>();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int creatLayout() {
        return R.layout.activity_abuout_us;
    }


}
