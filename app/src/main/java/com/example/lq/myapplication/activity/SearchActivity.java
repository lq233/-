package com.example.lq.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.bean.Bean;
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

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBack1;
    /**
     * 搜索
     */
    private Button mSearchBt;
    private TagFlowLayout mFlow;
    /**
     * 搜索历史
     */
    private TextView mTv;
    private ImageView mIvClear;
    private RecyclerView mRlv;
    /**
     * 发现更多干货
     */
    private EditText mSearchEd;
    private ArrayList<String> mList;
    private ArrayList<String> mTitles;
    private ArrayList<String> mTitle;
    private SearchBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        mBack1 = (ImageView) findViewById(R.id.back1);
        mSearchBt = (Button) findViewById(R.id.search_bt);
        mSearchBt.setOnClickListener(this);
        mTv = (TextView) findViewById(R.id.tv);
        mIvClear = (ImageView) findViewById(R.id.iv_clear);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mSearchEd = (EditText) findViewById(R.id.search_ed);
        mFlow = (TagFlowLayout) findViewById(R.id.flow);
        mTitle = new ArrayList<>();
        initLiu();
        mFlow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String link = bean.getData().get(position).getLink();
                String name = bean.getData().get(position).getName();
                Intent intent = new Intent(SearchActivity.this, WebActivity.class);
                intent.putExtra("url", link);
                intent.putExtra("title", name);
                startActivity(intent);
                return false;
            }
        });
        mBack1.setOnClickListener(this);
    }

    private void initLiu() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiServer.URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiServer server = retrofit.create(ApiServer.class);
        Observable<SearchBean> observable = server.getSearch();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        bean=searchBean;
                        for (int i = 0; i < searchBean.getData().size(); i++) {
                            mTitle.add(searchBean.getData().get(i).getName());
                        }
                        mFlow.setAdapter(new TagAdapter<String>(mTitle) {

                            @Override
                            public View getView(FlowLayout parent, int position, String s) {
                                int[] array = {R.color.Amber, R.color.arrow_color, R.color.colorPrimary
                                        , R.color.colorPrimaryDark, R.color.Blue, R.color.color_title_bg, R.color.Green
                                        , R.color.Deep_Orange, R.color.Lime, R.color.Teal, R.color.Indigo, R.color.Pink
                                        , R.color.Yellow, R.color.Amber, R.color.Purple, R.color.Light_Green
                                        , R.color.Light_Blue};

                                int random = (int) (Math.random() * (array.length - 1));
                                View inflate = LayoutInflater.from(SearchActivity.this).inflate(R.layout.tag_textview1, parent, false);
                                TextView tv = inflate.findViewById(R.id.tag_textview);
                                tv.setText(s);
                                tv.setTextColor(SearchActivity.this.getResources().getColor(array[random]));
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
            case R.id.search_bt:
                break;
            case R.id.back1:
                finish();
                break;
        }
    }
}
