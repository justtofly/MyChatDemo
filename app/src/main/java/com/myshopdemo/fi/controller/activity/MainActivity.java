package com.myshopdemo.fi.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.myshopdemo.fi.R;

public class MainActivity extends Activity {

    private RadioGroup  mRg_main;
    private RadioButton mRb_message;
    private RadioButton mRb_friend;
    private RadioButton mRb_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }

    private void initData() {

    }

    private void initView() {
        mRg_main = (RadioGroup) findViewById(R.id.rg_main);
        mRb_message = (RadioButton) findViewById(R.id.rb_message);
        mRb_friend = (RadioButton) findViewById(R.id.rb_friend);
        mRb_setting = (RadioButton) findViewById(R.id.rb_setting);
    }
}
