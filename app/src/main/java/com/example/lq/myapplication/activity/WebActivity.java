package com.example.lq.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lq.myapplication.R;

import java.lang.reflect.Method;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWeb;
    private String mUrl;
    private String mTitle;
    /**
     * My Application
     */
    private TextView mWebText;
    private Toolbar mWebToolbar;
    private ImageView mWebImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mTitle = intent.getStringExtra("title");
        initView();
    }

    private void initView() {
        mWebText = (TextView) findViewById(R.id.web_text);
        mWebToolbar = (Toolbar) findViewById(R.id.web_toolbar);
        mWeb = (WebView) findViewById(R.id.web);
        mWebImg = (ImageView) findViewById(R.id.web_img);
        mWebToolbar.setTitle("");
        setSupportActionBar(mWebToolbar);
        mWebText.setText(mTitle);
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl(mUrl);
/*        ImageView view = mWebToolbar.findViewById(R.id.img_back);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        mWebImg.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.web_item2:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "掌上生活");
                intent.putExtra(Intent.EXTRA_TEXT, "文本");
                WebActivity.this.startActivity(Intent.createChooser(intent, "分享"));
                break;
            case R.id.web_item3:
                Intent intent1 = new Intent(WebActivity.this, InternetActivity.class);
                intent1.putExtra("url", mUrl);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.web_img:
                finish();
                break;
        }
    }
}
