package com.myshopdemo.fi.model.db;

import android.content.Context;

import com.myshopdemo.fi.model.dao.ContactTableDao;
import com.myshopdemo.fi.model.dao.InviteTableDao;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/14 0014 7:37
 * 作用	     联系人数据库的管理类
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class DBManager {
    //创建联系人和邀请信息数据库对象
    private final DBHelper mDBHelper;
    //创建联系人表操作类对象
    private final ContactTableDao mContactTableDao;
    //邀请信息表的操作类
    private final InviteTableDao mInviteTableDao;

    /*public DBManager(DBHelper dbHelper, ContactTableDao contactTableDao, InviteTableDao inviteTableDao) {
        mDBHelper = dbHelper;
        mContactTableDao = contactTableDao;
        mInviteTableDao = inviteTableDao;
    }*/

    public DBManager(Context context,String name) {
        //实例化数据库
        mDBHelper=new DBHelper(context,name);

        //实例化该数据库中两张表的操作类
        mContactTableDao=new ContactTableDao(mDBHelper);
        mInviteTableDao=new InviteTableDao(mDBHelper);
    }

    //获取联系人表的操作对象
    public ContactTableDao getContactTableDao(){
        return  mContactTableDao;
    }
    //获取邀请信息表的操作对象
    public InviteTableDao getInviteTableDao(){
        return  mInviteTableDao;
    }

    /**
     * 关闭数据库的方法
     */
    public void close() {
        mDBHelper.close();
    }
}
