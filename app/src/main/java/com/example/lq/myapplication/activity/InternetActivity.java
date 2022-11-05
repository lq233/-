package com.example.lq.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.lq.myapplication.R;

public class InternetActivity extends AppCompatActivity {

    private WebView mIntertWeb;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        initView();
    }

    private void initView() {
        mIntertWeb = (WebView) findViewById(R.id.intert_web);
        WebSettings settings = mIntertWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mIntertWeb.loadUrl(mUrl);
    }
}
