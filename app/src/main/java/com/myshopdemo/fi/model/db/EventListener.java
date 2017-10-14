package com.myshopdemo.fi.model.db;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.myshopdemo.fi.model.Model;
import com.myshopdemo.fi.model.bean.InvationInfo;
import com.myshopdemo.fi.model.bean.UserInfo;
import com.myshopdemo.fi.utils.Constant;
import com.myshopdemo.fi.utils.SpUtils;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/14 0014 9:24
 * 作用	      全局事件监听类
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class EventListener {
    private Context mContext;
    private final LocalBroadcastManager mLocalBroadcastManager;

    public EventListener(Context context) {
        mContext = context;
        //创建一个发送广播的管理者对象
        mLocalBroadcastManager=LocalBroadcastManager.getInstance(mContext);

        //注册一个联系人变化的监听
        EMClient.getInstance().contactManager().setContactListener(emContactListener);
    }

    private final EMContactListener emContactListener = new EMContactListener() {
        //联系人增加后执行的方法
        @Override
        public void onContactAdded(String hxId) {
            //数据的更新
            Model.getInstance().getDBManager().getContactTableDao().saveContact(new UserInfo(hxId), true);

            //发送联系人变化的广播
            mLocalBroadcastManager.sendBroadcast(new Intent(Constant.CONTACT_CHANGED));
        }

        //联系人删除后执行的方法
        @Override
        public void onContactDeleted(String hxId) {
            //数据更新
            Model.getInstance().getDBManager().getContactTableDao().deleteContactByHxId(hxId);
            Model.getInstance().getDBManager().getInviteTableDao().removeInvitation(hxId);

            //发送联系人变化的广播
            mLocalBroadcastManager.sendBroadcast(new Intent(Constant.CONTACT_CHANGED));
        }

        //接受到了联系人的新邀请
        @Override
        public void onContactInvited(String hxId, String reason) {
            //数据库更新
            InvationInfo invationInfo = new InvationInfo();
            invationInfo.setUser(new UserInfo(hxId));
            invationInfo.setReason(reason);
            invationInfo.setStatus(InvationInfo.InvitationStatus.NEW_INVITE);//新邀请

            Model.getInstance().getDBManager().getInviteTableDao().addInvitation(invationInfo);

            //红点的处理
            SpUtils.getInstance().save(SpUtils.IS_NEW_INVITE, true);

            //发送邀请信息变化的广播
            mLocalBroadcastManager.sendBroadcast(new Intent(Constant.CONTACT_INVITE_CHENGED));
        }

        //别人同意了你的好友邀请
        @Override
        public void onContactAgreed(String hxId) {
            //数据库更新
            InvationInfo invationInfo = new InvationInfo();
            invationInfo.setUser(new UserInfo(hxId));
            invationInfo.setStatus(InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEEP);//别人同意了你的邀请

            Model.getInstance().getDBManager().getInviteTableDao().addInvitation(invationInfo);

            //红点的处理
            SpUtils.getInstance().save(SpUtils.IS_NEW_INVITE, true);

            //发送邀请信息变化的广播
            mLocalBroadcastManager.sendBroadcast(new Intent(Constant.CONTACT_INVITE_CHENGED));
        }

        //别人拒绝了你的好友邀请
        @Override
        public void onContactRefused(String s) {
            //红点的处理
            SpUtils.getInstance().save(SpUtils.IS_NEW_INVITE, true);

            //发送邀请信息变化的广播
            mLocalBroadcastManager.sendBroadcast(new Intent(Constant.CONTACT_INVITE_CHENGED));
        }
    };

}
