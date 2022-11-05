package com.example.lq.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.bean.AlwaysBean;
import com.example.lq.myapplication.bean.SearchBean;
import com.example.lq.myapplication.http.ApiServer;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

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

public class AlwaysActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBack1;
    private TagFlowLayout mAlwaysFlow;
    private ArrayList<String> mTitle;
    private AlwaysBean Bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_always);
        initView();
    }

    private void initView() {
        mBack1 = (ImageView) findViewById(R.id.back1);
        mBack1.setOnClickListener(this);
        mAlwaysFlow = (TagFlowLayout) findViewById(R.id.always_flow);
        mTitle = new ArrayList<>();
        initData();
        mAlwaysFlow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String link = Bean.getData().get(position).getLink();
                String name = Bean.getData().get(position).getName();
                Intent intent = new Intent(AlwaysActivity.this, WebActivity.class);
                intent.putExtra("url", link);
                intent.putExtra("title", name);
                startActivity(intent);
                return false;
            }
        });
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiServer.URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiServer server = retrofit.create(ApiServer.class);
        Observable<AlwaysBean> observable = server.getAlways();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AlwaysBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AlwaysBean searchBean) {
                        for (int i = 0; i < searchBean.getData().size(); i++) {
                            mTitle.add(searchBean.getData().get(i).getName());
                        }
                        Bean = searchBean;
                        mAlwaysFlow.setAdapter(new TagAdapter<String>(mTitle) {

                            @Override
                            public View getView(FlowLayout parent, int position, String s) {
                                int[] array = {R.color.Amber, R.color.arrow_color, R.color.colorPrimary
                                        , R.color.colorPrimaryDark, R.color.Blue, R.color.color_title_bg, R.color.Green
                                        , R.color.Deep_Orange, R.color.Lime, R.color.Teal, R.color.Indigo, R.color.Pink
                                        , R.color.Yellow, R.color.Amber, R.color.Purple, R.color.Light_Green
                                        , R.color.Light_Blue};

                                int random = (int) (Math.random() * (array.length - 1));
                                View inflate = LayoutInflater.from(AlwaysActivity.this).inflate(R.layout.tag_textview1, parent, false);
                                TextView tv = inflate.findViewById(R.id.tag_textview);
                                tv.setText(s);
                                tv.setBackgroundColor(AlwaysActivity.this.getResources().getColor(array[random]));
                                return inflate;
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back1:
                finish();
                break;
        }
    }
}
