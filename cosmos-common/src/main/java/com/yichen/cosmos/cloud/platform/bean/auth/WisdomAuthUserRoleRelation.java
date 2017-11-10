package com.yichen.cosmos.cloud.platform.bean.auth;

/**
 * Created by Lizhengxian on 2017/3/23.
 */
public class WisdomAuthUserRoleRelation {

    private String userRoleRelationId;
    private String userId;
    private String roleId;
    private Integer flag;

    private String createTime;
    private String updateTime;
    private String creatorId;
    private String updaterId;


    public String getUserRoleRelationId() {
        return userRoleRelationId;
    }

    public void setUserRoleRelationId(String userRoleRelationId) {
        this.userRoleRelationId = userRoleRelationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }
}
