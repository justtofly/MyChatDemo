package com.myshopdemo.fi.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.myshopdemo.fi.R;
import com.myshopdemo.fi.model.Model;
import com.myshopdemo.fi.model.bean.UserInfo;

//登录页面
public class LoginActivity extends Activity {

    private EditText mEt_login_user;
    private EditText mEt_login_password;
    private Button   mBtn_login_regist;
    private Button   mBtn_login;

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
                //                Toast.makeText(LoginActivity.this,"注册",Toast.LENGTH_SHORT).show();
                regist();
            }
        });
        //登录按钮的点击事件处理
        mBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Toast.makeText(LoginActivity.this,"登录",Toast.LENGTH_SHORT).show();
                login();
            }
        });

    }

    /**
     * 登录按钮的业务逻辑处理
     */
    private void login() {
        //1.获取输入的用户名和密码
        final String loginName = mEt_login_user.getText().toString();
        final String loginPwd = mEt_login_password.getText().toString();

        //2.校验输入的用户名和密码
        if (TextUtils.isEmpty(loginName) || TextUtils.isEmpty(loginPwd)) {
            Toast.makeText(this, "您输入的用户名或者密码为空，请重新输入！", Toast.LENGTH_SHORT).show();
            return;
        }

        //3.登录逻辑处理
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //去环信服务器登录
                EMClient.getInstance().login(loginName, loginPwd, new EMCallBack() {
                    @Override
                    public void onSuccess() {//成功
                        //对模型层数据的处理
                        Model.getInstance().loginSuccess();

                        //保存用户帐号信息到本地数据库
                        Model.getInstance().getUserAccountDao().addAccount(new UserInfo(loginName));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //提示登录成功
                                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

                                //跳转到主页面
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onError(int i, final String s) {//失败
                        //提示登录失败
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登录失败"+s,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onProgress(int i, String s) {//登录过程中的处理

                    }
                });
            }
        });
    }

    /**
     * 注册的业务逻辑处理
     */
    private void regist() {
        //1.获取输入的用户名和密码
        final String registName = mEt_login_user.getText().toString();
        final String registPwd = mEt_login_password.getText().toString();

        //2.校验输入的用户名和密码
        if (TextUtils.isEmpty(registName) || TextUtils.isEmpty(registPwd)) {
            Toast.makeText(this, "您输入的用户名或者密码为空，请重新输入！", Toast.LENGTH_SHORT).show();
            return;
        }

        //3.去服务器注册帐号
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //去环信服务器注册帐号
                    EMClient.getInstance().createAccount(registName, registPwd);

                    //更新页面显示
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    //更新页面显示
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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
