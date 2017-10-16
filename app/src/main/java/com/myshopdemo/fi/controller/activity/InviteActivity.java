package com.myshopdemo.fi.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
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
        public void onAccept(final InvationInfo invationInfo) {
            //通知环信服务器，点击了接受按钮
            Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().contactManager().acceptInvitation(invationInfo.getUser().getHxid());

                        //数据库更新
                        Model.getInstance().getDBManager().getInviteTableDao().updateInvitationStatus(InvationInfo.InvitationStatus.INVITE_ACCEPT, invationInfo.getUser().getHxid());

                        //页面发生变化
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //页面发生变化
                                Toast.makeText(InviteActivity.this, "接受了邀请", Toast.LENGTH_SHORT).show();

                                //刷新页面,,要考虑网络数据的变化，本地数据的变化，内存数据的变化
                                refresh();
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void onReject(final InvationInfo invationInfo) {
            Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().contactManager().declineInvitation(invationInfo.getUser().getHxid());

                        //数据库变化
                        Model.getInstance().getDBManager().getInviteTableDao().removeInvitation(invationInfo.getUser().getHxid());

                        //页面变化
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(InviteActivity.this, "拒绝成功了", Toast.LENGTH_SHORT).show();

                                //刷新页面
                                refresh();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(InviteActivity.this,"拒绝失败了",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
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
        List<InvationInfo> invationInfos = Model.getInstance().getDBManager().getInviteTableDao().getInvitations();

        //刷新适配器
        mInviteAdapter.refresh(invationInfos);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.lv_invite);
    }
}
