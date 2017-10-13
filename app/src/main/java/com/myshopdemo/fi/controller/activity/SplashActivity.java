package com.myshopdemo.fi.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.hyphenate.chat.EMClient;
import com.myshopdemo.fi.R;
import com.myshopdemo.fi.model.Model;

/**
 * 欢迎页面
 */
public class SplashActivity extends Activity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //如果当前activity已经退出，那么就不处理handler中的消息
            if (isFinishing()) {
                return;
            }
            //判断进入主页面还是登录页面
            toMainOrLogin();
        }

    };

    private void toMainOrLogin() {
        /**
         * 使用全局线程池管理，开启线程
         */
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //判断当前帐号是否已经登录过
                if (EMClient.getInstance().isLoggedInBefore()) {//登录过
                    //获取当前用户的登录信息

                    //跳转到主页面
                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else {//没登录过
                    //跳转到主页面
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                //结束当前页面
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //发送2s延时消息
        mHandler.sendMessageDelayed(Message.obtain(), 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁消息
        mHandler.removeCallbacksAndMessages(null);
    }
}
