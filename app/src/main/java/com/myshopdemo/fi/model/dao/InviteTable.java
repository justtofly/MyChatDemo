package com.myshopdemo.fi.model.dao;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/14 0014 6:39
 * 作用	      邀请信息表的建表语句
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class InviteTable {
    public static final String TAB_NAME="tab_invite";

    public static final String COL_USER_HXID="user_hxid";//用户的环信id
    public static final String COL_USER_NAME="user_name";//用户的名称

    public static final String COL_GROUP_NAME="group_name";//群组名称
    public static final String COL_GROUP_HXID="group_hxid";//群组环信id

    public static final String COL_REASON="reason";//邀请的原因
    public static final String COL_STATUS="status";//邀请的状态

    //创建表 create table tab_invite(user_hxid text primary key,user_name text,group_name text
    // ,group_hxid text,reason text,status text);
    public static final String CREATE_TAB="create table "+TAB_NAME+"("+COL_USER_HXID+
            " text primary key,"+COL_USER_NAME+" text,"+COL_GROUP_NAME+" text,"+COL_GROUP_HXID+
            " text,"+COL_REASON+" text,"+COL_STATUS+" integer);";//integer能转换成枚举，枚举能转换成integer
}
