package com.myshopdemo.fi.model.bean;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/13 0013 22:47
 * 作用	      群组信息bean类
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class GroupInfo {
    private String groupName;//群名称
    private String groupId;//群id
    private String invatePerson;//邀请人

    public GroupInfo() {
    }

    public GroupInfo(String groupId, String groupName, String invatePerson) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.invatePerson = invatePerson;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInvatePerson() {
        return invatePerson;
    }

    public void setInvatePerson(String invatePerson) {
        this.invatePerson = invatePerson;
    }
}
