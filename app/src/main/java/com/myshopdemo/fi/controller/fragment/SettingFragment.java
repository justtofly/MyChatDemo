package com.myshopdemo.fi.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.myshopdemo.fi.R;
import com.myshopdemo.fi.controller.activity.LoginActivity;
import com.myshopdemo.fi.model.Model;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/13 0013 15:53
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class SettingFragment extends Fragment {

    private Button mBtn_setting_out;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*TextView textView=new TextView(getActivity());
        textView.setText("设置页面Fragment");*/
        View view=View.inflate(getActivity(), R.layout.fragment_setting,null);

        initView(view);

        return view;
    }

    private void initView(View view) {
        mBtn_setting_out = (Button) view.findViewById(R.id.btn_setting_out);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        //在button上显示当前用户名称
        mBtn_setting_out.setText("退出登录("+ EMClient.getInstance().getCurrentUser()+")");

        //退出登录的逻辑处理
        mBtn_setting_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        //登录环信服务器退出登录
                        EMClient.getInstance().logout(false, new EMCallBack() {

                            @Override
                            public void onSuccess() {//成功退出登录

                                //关闭DBHelper
                                Model.getInstance().getDBManager().close();

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //更新ui显示
                                        Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_SHORT).show();

                                        //回到登录页面
                                        Intent intent=new Intent(getActivity(), LoginActivity.class);
                                        startActivity(intent);

                                        getActivity().finish();
                                    }
                                });
                            }

                            @Override
                            public void onError(int i, final String s) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(),"退出失败"+s,Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                });
            }
        });
    }
}
