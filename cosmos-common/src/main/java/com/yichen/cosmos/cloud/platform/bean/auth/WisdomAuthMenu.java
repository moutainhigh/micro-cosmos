package com.yichen.cosmos.cloud.platform.bean.auth;

import java.util.List;

/**
 * Created by Lizhengxian on 2017/3/23.
 */
public class WisdomAuthMenu {

    private String menuId;
    private String menuName;
    private String menuPid;
    private String webRoute;
    private String targetUrl;
    private int type;
    private String icon;
    private int order;
    private int isDisplay;
    private String describe;
    private Integer flag;
    private String createTime;
    private String updateTime;
    private String creatorId;
    private String updaterId;

    //1:选中   0：未选中
    //在编辑角色时使用
    private String isChoose = "0";

    public String getIsChoose() {
        return isChoose;
    }

    public void setIsChoose(String isChoose) {
        this.isChoose = isChoose;
    }

    private List<WisdomAuthMenu> subMenuList;

    public List<WisdomAuthMenu> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<WisdomAuthMenu> subMenuList) {
        this.subMenuList = subMenuList;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(String menuPid) {
        this.menuPid = menuPid;
    }

    public String getWebRoute() {
        return webRoute;
    }

    public void setWebRoute(String webRoute) {
        this.webRoute = webRoute;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}
