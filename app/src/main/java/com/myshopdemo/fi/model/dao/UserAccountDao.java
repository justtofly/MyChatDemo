package com.myshopdemo.fi.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myshopdemo.fi.model.bean.UserInfo;
import com.myshopdemo.fi.model.db.UserAccountDB;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/13 0013 11:11
 * 作用	      用户数据库的操作类
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class UserAccountDao {
    //创建用户数据库类对象
    private final UserAccountDB mHelper;

    public UserAccountDao(Context context) {
        //实例化用户数据库类对象
        mHelper = new UserAccountDB(context);
    }

    //添加用户到数据库
    public void addAccount(UserInfo userInfo){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        //执行添加操作
        ContentValues values=new ContentValues();
        values.put(UserAccountTable.COL_HXID,userInfo.getHxid());
        values.put(UserAccountTable.COL_NAME,userInfo.getName());
        values.put(UserAccountTable.COL_NICK,userInfo.getNick());
        values.put(UserAccountTable.COL_PHOTO,userInfo.getPhoto());

        db.replace(UserAccountTable.TAB_NAME, null, values);
    }

    //根据环信id获取所有用户信息
    public UserInfo getAccountByHxId(String hxId){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        //执行查询语句   select * from tab_account where hxid=?
        String sql="select * from "+UserAccountTable.TAB_NAME+" where "+UserAccountTable.COL_HXID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{hxId});

        //创建用户信息对象
        UserInfo userInfo=null;
        //取出里面数据
        if(cursor.moveToNext()){
            //实例化用户信息
            userInfo=new UserInfo();
            //封装对象
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_HXID)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NICK)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_PHOTO)));
        }

        //关闭资源
        cursor.close();

        //返回数据
        return userInfo;
    }
}
