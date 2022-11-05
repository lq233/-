package com.example.lq.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.example.lq.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView mWelImg;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 3:
                    break;
                case 2:
                    break;
                case 1:
                    break;
                case 0:
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();

    }

    private void initView() {
        mWelImg = (ImageView) findViewById(R.id.wel_img);
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(3000);
        mWelImg.setAnimation(animation);
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int i = 3;

            @Override
            public void run() {
                mHandler.sendEmptyMessage(i);
                i--;
                if (i == -1) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }
}

