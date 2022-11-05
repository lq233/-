package com.example.lq.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lq.myapplication.R;
import com.example.lq.myapplication.bean.Bean;
import com.example.lq.myapplication.fragment.GongZhongFragment;
import com.example.lq.myapplication.fragment.HomeFragment;
import com.example.lq.myapplication.fragment.KonwFragment;
import com.example.lq.myapplication.fragment.NavFragment;
import com.example.lq.myapplication.fragment.ProjectFragment;
import com.example.lq.myapplication.fragment.SettingsFragment;
import com.example.lq.myapplication.utils.SaoActivity;
import com.example.lq.myapplication.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    /**
     * My Application
     */
    private TextView mToolbarTitle;
    private Toolbar mToolBay;
    private FrameLayout mFrame;
    /**
     * 首页
     */
    private RadioButton mRdHome;
    /**
     * 知识体系
     */
    private RadioButton mRdKonwledge;
    /**
     * 公众号
     */
    private RadioButton mRdGong;
    /**
     * 导航
     */
    private RadioButton mRdDaohang;
    /**
     * 项目
     */
    private RadioButton mRdProject;
    private RadioGroup mRp;
    private FragmentManager mManager;
    private RelativeLayout mLl;
    private DrawerLayout mDl;
    private HomeFragment mHomeFragment;
    private KonwFragment mKonwFragment;
    private GongZhongFragment mGongZhongFragment;
    private NavFragment mNavFragment;
    private ProjectFragment mProjectFragment;
    private NavigationView mNav;
    private FloatingActionButton mFloatbutton;
    private SettingsFragment mSettingsFragment;
    /**
     * dong
     */
    private TextView mOne;
    /**
     * dong
     */
    private TextView mTwo;
    private TextView mLogin;
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mManager = getSupportFragmentManager();
        initView();
        initLinstener();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initLinstener() {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                item.setChecked(true);
                switch (itemId) {
                    case R.id.item1:
                        FragmentTransaction fragmentTransaction1 = mManager.beginTransaction();
                        fragmentTransaction1.show(mHomeFragment)
                                .hide(mSettingsFragment)
                                .hide(mKonwFragment)
                                .hide(mGongZhongFragment)
                                .hide(mNavFragment)
                                .hide(mProjectFragment)
                                .commit();
                        mToolbarTitle.setText("首页");
                        mRp.setVisibility(View.VISIBLE);
                        break;
                    case R.id.item2:
                        startActivity(new Intent(MainActivity.this, CollectActivity.class));
                        break;
                    case R.id.item3:
                        FragmentTransaction fragmentTransaction = mManager.beginTransaction();
                        fragmentTransaction.show(mSettingsFragment)
                                .hide(mHomeFragment)
                                .hide(mKonwFragment)
                                .hide(mGongZhongFragment)
                                .hide(mNavFragment)
                                .hide(mProjectFragment)
                                .commit();
                        mToolbarTitle.setText("设置");
                        mRp.setVisibility(View.GONE);
                        break;
                    case R.id.item4:
                        startActivity(new Intent(MainActivity.this, AbuoutUsActivity.class));
                        break;
                }
                mDl.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }


    private void initView() {
        mDl = (DrawerLayout) findViewById(R.id.dl);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolBay = (Toolbar) findViewById(R.id.toolBay);
        mFrame = (FrameLayout) findViewById(R.id.frame);
        mRdHome = (RadioButton) findViewById(R.id.rd_home);
        mRdKonwledge = (RadioButton) findViewById(R.id.rd_konwledge);
        mRdGong = (RadioButton) findViewById(R.id.rd_gong);
        mRdDaohang = (RadioButton) findViewById(R.id.rd_daohang);
        mRdProject = (RadioButton) findViewById(R.id.rd_project);
        mRp = (RadioGroup) findViewById(R.id.rp);
        mNav = (NavigationView) findViewById(R.id.nav);
        mLl = (RelativeLayout) findViewById(R.id.ll);
        mOne = (TextView) findViewById(R.id.one);

        mTwo = (TextView) findViewById(R.id.two);
        //获得头部布局
        View headerView = mNav.getHeaderView(0);
        mImg = headerView.findViewById(R.id.nv_img);
        mLogin = headerView.findViewById(R.id.header_login);
  /*      String param = (String) SpUtil.getParam("name", "登录");
        login.setText(param);*/
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        mToolBay.setTitle("");
        setSupportActionBar(mToolBay);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        mRp.check(R.id.rd_home);
        mToolbarTitle.setText("首页");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolBay, R.string.settings, R.string.settings);
        mDl.addDrawerListener(toggle);
        toggle.syncState();
        mDl.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
//获取抽屉的view
                View mContent = mDl.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

//设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
//设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

//设置内容界面水平和垂直方向偏转量
//在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
//设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
//设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
                super.onDrawerSlide(drawerView, slideOffset);
            }
        });
        final FragmentTransaction transaction = mManager.beginTransaction();
        initFragment();
        transaction(transaction);
        RgClickListener();


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBean(Bean bean) {
        mLogin.setText(bean.getName());
        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(this).load(bean.getIconurl()).apply(options).into(mImg);
    }

    private void transaction(FragmentTransaction transaction) {
        transaction.add(R.id.frame, mHomeFragment).show(mHomeFragment);
        transaction.add(R.id.frame, mKonwFragment).hide(mKonwFragment);
        transaction.add(R.id.frame, mGongZhongFragment).hide(mGongZhongFragment);
        transaction.add(R.id.frame, mNavFragment).hide(mNavFragment);
        transaction.add(R.id.frame, mProjectFragment).hide(mProjectFragment);
        transaction.add(R.id.frame, mSettingsFragment).hide(mSettingsFragment);
        transaction.commit();
    }

    private void RgClickListener() {
        mRp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_home:
                        mToolbarTitle.setText(R.string.home);
                        FragmentTransaction transaction1 = mManager.beginTransaction();
                        transaction1.show(mHomeFragment).hide(mKonwFragment).hide(mProjectFragment).hide(mNavFragment).hide(mGongZhongFragment);
                        transaction1.commit();
                        break;
                    case R.id.rd_konwledge:
                        mToolbarTitle.setText(R.string.konw);
                        FragmentTransaction transaction2 = mManager.beginTransaction();
                        transaction2.show(mKonwFragment).hide(mHomeFragment).hide(mGongZhongFragment).hide(mNavFragment).hide(mProjectFragment);
                        transaction2.commit();
                        break;
                    case R.id.rd_gong:
                        mToolbarTitle.setText(R.string.xianlu);
                        FragmentTransaction transaction3 = mManager.beginTransaction();
                        transaction3.show(mGongZhongFragment).hide(mKonwFragment).hide(mNavFragment).hide(mHomeFragment).hide(mProjectFragment);
                        transaction3.commit();
                        break;
                    case R.id.rd_daohang:
                        mToolbarTitle.setText(R.string.nav);
                        FragmentTransaction transaction4 = mManager.beginTransaction();
                        transaction4.show(mNavFragment).hide(mGongZhongFragment).hide(mProjectFragment).hide(mHomeFragment).hide(mKonwFragment);
                        transaction4.commit();
                        break;
                    case R.id.rd_project:
                        mToolbarTitle.setText(R.string.project);
                        FragmentTransaction transaction5 = mManager.beginTransaction();
                        transaction5.show(mProjectFragment).hide(mNavFragment).hide(mHomeFragment).hide(mKonwFragment).hide(mGongZhongFragment);
                        transaction5.commit();
                        break;
                }
            }
        });
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mKonwFragment = new KonwFragment();
        mGongZhongFragment = new GongZhongFragment();
        mNavFragment = new NavFragment();
        mProjectFragment = new ProjectFragment();
        mSettingsFragment = new SettingsFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Intent intent = new Intent();
                intent.setClass(this, SearchActivity.class);
                SaoActivity.startActivity(this, intent, mOne, R.color.fabbg);
                break;
            case R.id.home:
                Intent intent1 = new Intent();
                intent1.setClass(this, AlwaysActivity.class);
                SaoActivity.startActivity(this, intent1, mTwo, R.color.light_yellow);
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
