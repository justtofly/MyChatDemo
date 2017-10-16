package com.myshopdemo.fi.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myshopdemo.fi.model.bean.GroupInfo;
import com.myshopdemo.fi.model.bean.InvationInfo;
import com.myshopdemo.fi.model.bean.UserInfo;
import com.myshopdemo.fi.model.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/14 0014 6:50
 * 作用	      邀请信息表的操作类
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class InviteTableDao {
    private DBHelper mHelper;

    public InviteTableDao(DBHelper helper){
        mHelper=helper;
    }

    //添加邀请
    public void addInvitation(InvationInfo invationInfo){
        //获取数据库连接
        SQLiteDatabase db=mHelper.getReadableDatabase();

        //执行添加语句
        ContentValues values=new ContentValues();
        values.put(InviteTable.COL_REASON,invationInfo.getReason());//原因
        values.put(InviteTable.COL_STATUS,invationInfo.getStatus().ordinal());//状态

        //获取联系人
        UserInfo user=invationInfo.getUser();
        if(user!=null){//联系人
            values.put(InviteTable.COL_USER_HXID,invationInfo.getUser().getHxid());
            values.put(InviteTable.COL_USER_NAME,invationInfo.getUser().getName());
        }else {//群组
            values.put(InviteTable.COL_GROUP_HXID,invationInfo.getGroup().getGroupId());
            values.put(InviteTable.COL_GROUP_NAME,invationInfo.getGroup().getGroupName());
            values.put(InviteTable.COL_REASON,invationInfo.getGroup().getInvatePerson());
        }

        db.replace(InviteTable.TAB_NAME,null,values);
    }

    //获取所有邀请信息
    public List<InvationInfo> getInvitations(){
        //获取数据库连接
        SQLiteDatabase db=mHelper.getReadableDatabase();

        //执行查询语句
        String sql="select * from "+InviteTable.TAB_NAME;
        Cursor cursor=db.rawQuery(sql,null);

        List<InvationInfo> invationInfos=new ArrayList<>();

        while (cursor.moveToNext()){
            InvationInfo invationInfo=new InvationInfo();

            invationInfo.setReason(cursor.getString(cursor.getColumnIndex(InviteTable.COL_REASON)));//原因
            invationInfo.setStatus(int2InviteStatus(cursor.getInt(cursor.getColumnIndex(InviteTable.COL_STATUS))));//邀请状态

            String groupId=cursor.getString(cursor.getColumnIndex(InviteTable.COL_GROUP_HXID));

            if (groupId==null){//联系人的邀请信息
                UserInfo userInfo=new UserInfo();

                userInfo.setHxid(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_HXID)));
                userInfo.setName(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_NAME)));
                userInfo.setNick(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_NAME)));

                //把用户信息添加到邀请信息里面
                invationInfo.setUser(userInfo);
            }else {//群组的邀请信息
                GroupInfo groupInfo=new GroupInfo();

                groupInfo.setGroupId(cursor.getString(cursor.getColumnIndex(InviteTable.COL_GROUP_HXID)));
                groupInfo.setGroupName(cursor.getString(cursor.getColumnIndex(InviteTable.COL_GROUP_NAME)));
                groupInfo.setInvatePerson(cursor.getString(cursor.getColumnIndex(InviteTable.COL_GROUP_NAME)));

                //把群组信息添加到邀请信息里面
                invationInfo.setGroup(groupInfo);
            }

            //添加本次循环的邀请信息到总的集合中
            invationInfos.add(invationInfo);
        }

        //关闭资源
        cursor.close();

        //返回数据
        return invationInfos;
    }

    //将int类型状态转换为邀请的状态
    private InvationInfo.InvitationStatus int2InviteStatus(int intStatus){
        if (intStatus==InvationInfo.InvitationStatus.NEW_INVITE.ordinal()){
            return InvationInfo.InvitationStatus.NEW_INVITE;
        }

        if (intStatus==InvationInfo.InvitationStatus.INVITE_ACCEPT.ordinal()){
            return InvationInfo.InvitationStatus.INVITE_ACCEPT;
        }

        if (intStatus==InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEEP.ordinal()){
            return InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEEP;
        }

        if (intStatus== InvationInfo.InvitationStatus.NEW_GROUP_INVITE.ordinal()){
            return InvationInfo.InvitationStatus.NEW_GROUP_INVITE;
        }
        return null;
    }

    //删除邀请
    public void removeInvitation(String hxId){
        if (hxId==null){
            return;
        }

        //获取数据库连接
        SQLiteDatabase db=mHelper.getReadableDatabase();

        //执行删除语句
        db.delete(InviteTable.TAB_NAME,InviteTable.COL_USER_HXID+"=?",new String[]{hxId});
    }

    //更新邀请状态
    public void updateInvitationStatus(InvationInfo.InvitationStatus invitationStatus,String hxId){
        if (hxId==null){
            return;
        }

        //获取数据库连接
        SQLiteDatabase db=mHelper.getReadableDatabase();

        //执行更新操作
        ContentValues values=new ContentValues();
        values.put(InviteTable.COL_STATUS,invitationStatus.ordinal());

        db.update(InviteTable.TAB_NAME,values,InviteTable.COL_USER_HXID+"=?",new String[]{hxId});

    }
}
