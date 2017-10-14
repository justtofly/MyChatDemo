package com.myshopdemo.fi.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.myshopdemo.fi.IMApplication;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/14 0014 8:15
 * 作用	      存储工具类
 * 一般封装成一个单例
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class SpUtils {
    private static SpUtils instance = new SpUtils();//饿汉式
    private static SharedPreferences mSp;

    //私有构造方法
    private SpUtils() {
    }

    //获取单例对象
    public static SpUtils getInstance() {
        if (mSp==null){
            mSp= IMApplication.getGlobalApplication().getSharedPreferences("im", Context.MODE_PRIVATE);
        }
        return instance;
    }

    //保存
    public void save(String key,Object value){
        if (value instanceof String){
            mSp.edit().putString(key, (String)value).commit();
        }else if (value instanceof  Boolean){
            mSp.edit().putBoolean(key,(Boolean)value).commit();
        }else if (value instanceof  Integer){
            mSp.edit().putInt(key, (Integer) value).commit();
        }
    }

    //获取数据的方法
    public String getString(String key,String defValue){
        return mSp.getString(key,defValue);
    }

    //获取boolean数据
    public boolean getBoolean(String key,boolean defValue){
        return mSp.getBoolean(key,defValue);
    }

    //获取int类型数据
    public int getInt(String key,int defValue){
        return mSp.getInt(key,defValue);
    }
}
