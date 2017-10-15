package com.myshopdemo.fi.controller.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.myshopdemo.fi.R;
import com.myshopdemo.fi.controller.activity.AddContactActivity;
import com.myshopdemo.fi.controller.activity.InviteActivity;
import com.myshopdemo.fi.utils.Constant;
import com.myshopdemo.fi.utils.SpUtils;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/13 0013 15:53
 * 作用	      联系人列表页面
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class ContactListFragment extends EaseContactListFragment {
    private ImageView             mIv_contact_red;
    private LocalBroadcastManager mLocalBroadcastManager;
    private BroadcastReceiver ContactInviteChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //更新红点显示
            mIv_contact_red.setVisibility(View.VISIBLE);
            SpUtils.getInstance().save(SpUtils.IS_NEW_INVITE,true);
        }
    };

    @Override
    protected void initView() {
        super.initView();

        //布局显示加号
        titleBar.setRightImageResource(R.drawable.em_add);

        //添加头布局
        View headerView = View.inflate(getActivity(), R.layout.header_fragment_contact, null);
        listView.addHeaderView(headerView);

        //获取红点对象
        mIv_contact_red = (ImageView) headerView.findViewById(R.id.iv_contact_red);

        //获取邀请信息条目的对象
        LinearLayout ll_contact_invite= (LinearLayout) headerView.findViewById(R.id.ll_contact_invite);

        //邀请信息条目点击事件
        ll_contact_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //红点处理
                mIv_contact_red.setVisibility(View.GONE);
                SpUtils.getInstance().save(SpUtils.IS_NEW_INVITE,false);

                //跳转到邀请信息列表页面
                Intent intent=new Intent(getActivity(),InviteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setUpView() {
        super.setUpView();

        //加号按钮的点击事件
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到添加联系人页面
                Intent intent = new Intent(getActivity(), AddContactActivity.class);
                startActivity(intent);
            }
        });

        //初始化红点显示
        boolean isNewInvite= SpUtils.getInstance().getBoolean(SpUtils.IS_NEW_INVITE,false);
        mIv_contact_red.setVisibility(isNewInvite?View.VISIBLE:View.GONE);

        //注册广播
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        mLocalBroadcastManager.registerReceiver(ContactInviteChangeReceiver,new IntentFilter(Constant.CONTACT_INVITE_CHENGED));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //关闭广播（注册了广播就一定要关闭广播）
        mLocalBroadcastManager.unregisterReceiver(ContactInviteChangeReceiver);
    }
}
