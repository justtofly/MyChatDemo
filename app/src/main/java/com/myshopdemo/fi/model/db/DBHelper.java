package com.myshopdemo.fi.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myshopdemo.fi.model.dao.ContactTable;
import com.myshopdemo.fi.model.dao.InviteTable;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/13 0013 23:19
 * 作用	      联系人和邀请信息数据库
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建联系人的表
        db.execSQL(ContactTable.CREATE_TAB);

        //创建邀请信息的表
        db.execSQL(InviteTable.CREATE_TAB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
