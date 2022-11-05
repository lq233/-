package com.example.lq.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.bean.Bean;
import com.example.lq.myapplication.utils.SpUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private ImageView mImgBack;
    /**
     * 请输入输入用户名
     */
    private EditText mLoginUser;
    /**
     * 请输入输入密码
     */
    private EditText mLoginPsw;
    /**
     * 登录
     */
    private Button mLoginBt;
    /**
     * 注册
     */
    private Button mRegisterBt;
    private ImageView mLoginQq;
    private ImageView mLoginWx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }


    private void initView() {
        mImgBack = (ImageView) findViewById(R.id.img_back);
        mLoginUser = (EditText) findViewById(R.id.login_user);
        mLoginPsw = (EditText) findViewById(R.id.login_psw);
        mLoginBt = (Button) findViewById(R.id.login_bt);
        mLoginBt.setOnClickListener(this);
        mRegisterBt = (Button) findViewById(R.id.register_bt);
        mRegisterBt.setOnClickListener(this);
        mImgBack.setOnClickListener(this);
        mLoginQq = (ImageView) findViewById(R.id.login_qq);
        mLoginQq.setOnClickListener(this);
        mLoginWx = (ImageView) findViewById(R.id.login_wx);
        mLoginWx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_bt:
                break;
            case R.id.register_bt:
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), 100);
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.login_qq:
                loginQQ(SHARE_MEDIA.QQ);
                break;
            case R.id.login_wx:
                break;
        }
    }

    private void loginQQ(SHARE_MEDIA type) {
        UMShareAPI umShareAPI = UMShareAPI.get(this);
        umShareAPI.getPlatformInfo(LoginActivity.this, type, umAuthListener);
    }
    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Log.d(TAG, "key: " + key + ",value:" + value);
            }
            String name = data.get("name");
            String iconurl = data.get("iconurl");

            EventBus.getDefault().post(new Bean(name, iconurl));
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            finish();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    //5cd54c3f4ca357542900081f 三方登录AppKey
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            String phone = data.getStringExtra("phone");
            String psw = data.getStringExtra("psw");
            SpUtil.setParam("name", phone);
            mLoginUser.setText(phone);
            mLoginPsw.setText(psw);
        }
    }
}
