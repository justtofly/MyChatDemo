package com.myshopdemo.fi.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myshopdemo.fi.model.dao.UserAccountTable;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/13 0013 11:00
 * 作用	      用户帐户数据库类
 * 用来存储用户信息
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class UserAccountDB extends SQLiteOpenHelper {
    //构造方法
    public UserAccountDB(Context context) {
        super(context, "account.db", null, 1);
    }

    //数据库创建的时候调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库表的语句
        db.execSQL(UserAccountTable.CREATE_TAB);
    }

    //数据库更新的时候调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
