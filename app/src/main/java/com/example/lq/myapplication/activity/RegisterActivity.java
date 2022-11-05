package com.example.lq.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lq.myapplication.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBack;
    /**
     * 注册
     */
    private Button mRegisterZc;
    /**
     * 邮箱/手机号
     */
    private EditText mRegisPhone;
    /**
     * 输入密码
     */
    private EditText mRegisPsw;
    /**
     * 确认密码
     */
    private EditText mRegisAgainpsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mRegisPhone = (EditText) findViewById(R.id.regis_phone);
        mRegisPsw = (EditText) findViewById(R.id.regis_psw);
        mRegisAgainpsw = (EditText) findViewById(R.id.regis_againpsw);
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mRegisterZc = (Button) findViewById(R.id.register_zc);
        mRegisterZc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.register_zc:
                login();
                break;
        }
    }

    private void login() {
        String phone = mRegisPhone.getText().toString();
        String psw = mRegisPsw.getText().toString();
        String agianpsw = mRegisAgainpsw.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("phone", phone);
        intent.putExtra("psw", psw);
        setResult(200, intent);
        finish();
    }
}
