package com.myshopdemo.fi.controller.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.myshopdemo.fi.R;
import com.myshopdemo.fi.controller.fragment.ChatFragment;
import com.myshopdemo.fi.controller.fragment.ContactListFragment;
import com.myshopdemo.fi.controller.fragment.SettingFragment;

public class MainActivity extends FragmentActivity {

    private RadioGroup          mRg_main;
    private RadioButton         mRb_message;
    private RadioButton         mRb_friend;
    private RadioButton         mRb_setting;
    private ChatFragment        mChatFragment;
    private ContactListFragment mContactListFragment;
    private SettingFragment mSettingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initListener();
    }

    private void initListener() {
        //RadioGroup的选择事件
        mRg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //创建Fragment对象
                Fragment fragment = null;
                switch (checkedId) {
                    //消息列表页面
                    case R.id.rb_message:
                        fragment = mChatFragment;
                        break;
                    //联系人列表页面
                    case R.id.rb_friend:
                        fragment = mContactListFragment;
                        break;
                    //设置页面
                    case R.id.rb_setting:
                        fragment = mSettingFragment;
                        break;
                }

                //实现fragment切换的方法
                switchFragment(fragment);
            }
        });

        //默认选择会话页面
        mRg_main.check(R.id.rb_message);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();
    }

    private void initData() {
        //创建三个fragment对象
        mChatFragment = new ChatFragment();
        mContactListFragment = new ContactListFragment();
        mSettingFragment = new SettingFragment();
    }

    private void initView() {
        mRg_main = (RadioGroup) findViewById(R.id.rg_main);
        mRb_message = (RadioButton) findViewById(R.id.rb_message);
        mRb_friend = (RadioButton) findViewById(R.id.rb_friend);
        mRb_setting = (RadioButton) findViewById(R.id.rb_setting);
    }
}
