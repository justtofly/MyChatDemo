package com.myshopdemo.fi.model;

import android.content.Context;

import com.myshopdemo.fi.model.bean.UserInfo;
import com.myshopdemo.fi.model.dao.UserAccountDao;
import com.myshopdemo.fi.model.db.DBManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/13 0013 7:52
 * 作用	      数据模型全局类
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class Model {
    private Context mContext;

    //创建线程池对象
    private ExecutorService executors = Executors.newCachedThreadPool();

    //创建对象
    private static Model model = new Model();

    //创建用户帐号数据库的操作类对象
    private UserAccountDao mUserAccountDao;

    //创建数据库管理类对象
    private DBManager mDBManager;

    //私有构造方法
    private Model() {
    }

    //获取单例对象
    public static Model getInstance() {
        return model;
    }

    //初始化的方法
    public void init(Context context) {
        mContext = context;

        //实例化用户帐号数据库的操作类对象
        mUserAccountDao = new UserAccountDao(mContext);
    }

    //获取全局线程池对象
    public ExecutorService getGlobalThreadPool() {
        return executors;
    }

    //用户登录成功后的处理方法
    public void loginSuccess(UserInfo account) {
        if (account==null){
            return;
        }

        if (mDBManager!=null){
            mDBManager.close();
        }
        mDBManager=new DBManager(mContext,account.getName());
    }

    /**
     * 获取数据库管理类对象
     * @return
     */
    public DBManager getDBManager(){
        return mDBManager;
    }

    //获取用户帐户数据库操作对象
    public UserAccountDao getUserAccountDao(){
        return mUserAccountDao;
    }
}
