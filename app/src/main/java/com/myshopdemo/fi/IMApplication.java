package com.myshopdemo.fi;

import android.app.Application;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;
import com.myshopdemo.fi.model.Model;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/12 0012 22:29
 * 作用	      ${TODO}
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
//类名是灰色的，说明还没有配置
public class IMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化EaseUI
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);//设置同意后才能接受邀请
        options.setAutoAcceptGroupInvitation(false);//设置需要同意后才能接受邀请

//        EaseUI.getInstance().init(this, options);
        EaseUI.getInstance().init(this,options);

        //初始化数据模型层类
        Model.getInstance().init(this);
    }
}
