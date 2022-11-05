package com.example.lq.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.adapter.KnowVpAdapter;
import com.example.lq.myapplication.bean.KonwTabBean;
import com.example.lq.myapplication.fragment.KnowItemFragment;
import com.example.lq.myapplication.http.KonwServer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class KnowActivity extends AppCompatActivity {

    private static final String TAG = "jj";
    private TabLayout mKonwTab;
    private ViewPager mKonwVp;
    private int mPosition;
    // private ArrayList<KonwTabBean.DataBean.ChildrenBean> mList;
    private ArrayList<Fragment> mFragments;
    private TextView mKonwTv;
    private Toolbar mKnowToolbar;
    // private List<KonwTabBean.DataBean.ChildrenBean> mBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know);
        Intent intent = getIntent();
        mPosition = intent.getIntExtra("position", 0);
        initView();
        initData();
    }

    private void initView() {
        mKonwTab = (TabLayout) findViewById(R.id.konw_tab);
        mKonwVp = (ViewPager) findViewById(R.id.konw_vp);
        mKonwTv = (TextView) findViewById(R.id.konw_tv);
        mKnowToolbar = (Toolbar) findViewById(R.id.know_toolbar);
        //  mList = new ArrayList<>();
        mKnowToolbar.setTitle("");
        setSupportActionBar(mKnowToolbar);
        mFragments = new ArrayList<>();
        //mList.addAll(mBeans);


    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(KonwServer.Urls).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        KonwServer server = retrofit.create(KonwServer.class);
        Observable<KonwTabBean> observable = server.getTab();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KonwTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KonwTabBean konwTabBean) {
                        List<KonwTabBean.DataBean> data = konwTabBean.getData();
                        List<KonwTabBean.DataBean.ChildrenBean> children = data.get(mPosition).getChildren();
                        mKonwTv.setText(data.get(mPosition).getName());
                        for (int i = 0; i < children.size(); i++) {
                            mFragments.add(new KnowItemFragment(children.get(i).getId()));
                        }
                        KnowVpAdapter adapter = new KnowVpAdapter(getSupportFragmentManager(), children, mFragments);
                        mKonwVp.setAdapter(adapter);
                        mKonwTab.setupWithViewPager(mKonwVp);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
