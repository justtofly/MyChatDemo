package com.myshopdemo.fi.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.myshopdemo.fi.R;
import com.myshopdemo.fi.controller.adapter.InviteAdapter;
import com.myshopdemo.fi.model.Model;
import com.myshopdemo.fi.model.bean.InvationInfo;

import java.util.List;

//邀请信息列表页面
public class InviteActivity extends Activity {

    private ListView mListView;

    //实例化接口对象
    private InviteAdapter.OnInviteListener mOnInviteListener = new InviteAdapter.OnInviteListener() {
        @Override
        public void onAccept(InvationInfo invationInfo) {

        }

        @Override
        public void onReject(InvationInfo invationInfo) {

        }
    };
    private InviteAdapter mInviteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        //初始化控件
        initView();

        initData();
    }

    private void initData() {
        //初始化适配器对象
        mInviteAdapter = new InviteAdapter(this, mOnInviteListener);
        //设置适配器
        mListView.setAdapter(mInviteAdapter);

        //刷新方法
        refresh();
    }

    private void refresh() {
        //给adapter传递数据,获取数据库中的所有邀请信息
        List<InvationInfo> invationInfos= Model.getInstance().getDBManager().getInviteTableDao().getInvitations();

        //刷新适配器
        mInviteAdapter.refresh(invationInfos);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.lv_invite);
    }
}
