package com.myshopdemo.fi.model.bean;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/13 0013 10:56
 * 作用	      用户bean类
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class UserInfo {
    //用户名称
    private String name;
    //环信id
    private String hxid;
    //用户的昵称
    private String nick;
    //用户头像
    private String photo;

    public UserInfo() {
    }

    /*public UserInfo(String hxid, String name, String nick, String photo) {
        this.hxid = hxid;
        this.name = name;
        this.nick = nick;
        this.photo = photo;
    }*/
    //简化操作
    public UserInfo(String name) {
        this.hxid = name;
        this.name = name;
        this.nick = name;
    }

    public String getHxid() {
        return hxid;
    }

    public void setHxid(String hxid) {
        this.hxid = hxid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
