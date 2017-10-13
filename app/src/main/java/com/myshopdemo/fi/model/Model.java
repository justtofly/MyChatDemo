package com.myshopdemo.fi.model;

import android.content.Context;

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
    }

    //获取全局线程池对象
    public ExecutorService getGlobalThreadPool(){
        return executors;
    }
}
