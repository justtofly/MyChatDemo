package com.myshopdemo.fi.model.dao;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/13 0013 11:04
 * 作用	      建表类
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class UserAccountTable {
    public static final String TAB_NAME  = "tab_account";
    public static final String COL_NAME  = "name";
    public static final String COL_HXID  = "hxid";
    public static final String COL_NICK  = "nick";
    public static final String COL_PHOTO = "photo";

    /**
     * create table tab_account(hxid text primary key,name text,nick text,photo text);
     */
    public static final String CREATE_TAB ="create table "+TAB_NAME+"("+COL_HXID+" text primary key,"+COL_NAME+
            " text,"+COL_NICK+" text,"+COL_PHOTO+" text);";
}
