package com.myshopdemo.fi.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myshopdemo.fi.R;
//登录页面
public class LoginActivity extends Activity {

    private EditText mEt_login_user;
    private EditText mEt_login_password;
    private Button   mBtn_login_regist;
    private Button mBtn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化控件
        initView();

        //控件的点击事件
        initListener();
    }

    private void initListener() {
        //注册按钮的点击事件处理
        mBtn_login_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"注册",Toast.LENGTH_SHORT).show();
            }
        });
        //登录按钮的点击事件处理
        mBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"登录",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        mEt_login_user = (EditText) findViewById(R.id.et_login_user);
        mEt_login_password = (EditText) findViewById(R.id.et_login_password);
        mBtn_login_regist = (Button) findViewById(R.id.btn_login_regist);
        mBtn_login = (Button) findViewById(R.id.btn_login);
    }
}
