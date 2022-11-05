package com.example.lq.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.adapter.CollectRlvAdaopter;
import com.example.lq.myapplication.bean.DbBean;
import com.example.lq.myapplication.utils.DbUtils;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends AppCompatActivity {

    private RecyclerView mCollectRlv;
    private List<DbBean> mBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        mBeans = DbUtils.getDbUtils().query();
        initView();
    }





    private void initView() {
        mCollectRlv = (RecyclerView) findViewById(R.id.collect_rlv);
        mCollectRlv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<DbBean> beans = new ArrayList<>();
        beans.addAll(mBeans);
        CollectRlvAdaopter adaopter = new CollectRlvAdaopter(CollectActivity.this, beans);
        mCollectRlv.setAdapter(adaopter);
    }
}
