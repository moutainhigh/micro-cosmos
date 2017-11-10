package com.yichen.cosmos.cloud.platform.bean.auth;

/**
 * Created by Lizhengxian on 2017/3/23.
 */
public class WisdomAuthRoleMenuRelation {
    private String roleMenuRelationId;
    private String roleId;
    private String menuId;
    private Integer flag;
    private String createTime;
    private String updateTime;
    private String creatorId;
    private String updaterId;


    public String getRoleMenuRelationId() {
        return roleMenuRelationId;
    }

    public void setRoleMenuRelationId(String roleMenuRelationId) {
        this.roleMenuRelationId = roleMenuRelationId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
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
