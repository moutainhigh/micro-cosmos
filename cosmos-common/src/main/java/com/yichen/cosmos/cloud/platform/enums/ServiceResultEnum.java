package com.yichen.cosmos.cloud.platform.enums;

import com.yichen.cosmos.cloud.platform.dto.ServiceResult;

/**
 * Created by Lizhengxian on 2017/3/23.
 */
public enum ServiceResultEnum {

    //常用返回值
    SUCCESS(200, "操作成功"),
    FAILED(500, "系统维护中，请稍后再试!"),

    //菜单操作
    CANT_FIND_MENU(80010, "找不到菜单"),
    CANT_UPDATE_MENU(80011, "菜单更新失败"),
    MENU_HAVE_CHILDREN(80012, "菜单有子菜单，请先删除子菜单"),
    CANT_DELETE_MENU(80013, "菜单删除失败"),
    PAGE_SIZE_LT_ONE(80013, "菜单页码小于1"),


    //用户-角色-菜单关联操作提示
    CANT_FIND_USER_ROLE_RELATION(80020, "找不到该用户的角色"),
    USER_ROLE_RELATION_ROLEID_IS_NULL(80021, "用户角色关联错误，无法找到角色"),//无法找到角色ID
    NO_MENU_FOR_ROLE(80022, "角色没有关联任何菜单"),
    CANT_FIND_ROLE_BY_ROLEID(80023, "无法通过角色ID找到角色"),//无法通过角色ID找到角色
    USER_ROLE_RELATION_EXIST(80024, "用户和角色已经建立关联"),

    //Controller操作相关
    NEED_PARAMS(80030, "请传入必要的参数"),

    //角色操作
    INSERT_ROLE_SUCCESS(200, "创建角色成功"),
    INSERT_ROLE_FAIL(80041, "创建角色失败"),
    CANT_FIND_ROLE(80042, "找不到角色"),

    //用户-角色
    CANT_RELATE_USER_AND_ROLE(80050, "无法关联用户和角色"),
    DELETE_SUCCESS(80051, "删除用户和角色关联成功"),
    CANT_FIND_USER_ROLE(80052, "找不到用户和角色的关系"),

    //角色-菜单
    ROLE_MENU_EXISTS(80060, "角色和菜单已经关联"),
    CANT_FIND_MENUS_BY_ROLEID(80061, "无法通过角色找到菜单"),//无法通过角色ID找到菜单
    //客户相关
    USER_ACCOUNT_EXISTS(60026, "账号已被占用，请重新输入"),
    USER_EMAIL_EXISTS(60027, "邮箱已被占用，请重新输入"),
    USER_PHONE_EXISTS(60028, "手机号已被占用，请重新输入"),
    CANT_FIND_USER(60029, "无法获取用户信息"),
    WRONG_PASSWORD(60030, "密码错误"),
    USER_EMAIL_WRONG(60031, "邮箱不合法"),
    USER_PHONE_WRONG(60032, "手机不合法"),
    OLD_NEW_NOT_EQUAL(60033, "新密码和确认密码不相同"),
    WRONG_OLD_PASSWORD(60034, "原密码错误"),
    NOT_CHILD_OF_CURRENT_ACCOUNT(60035, "非当前账号名下子账号"),

    //数据计算相关
    JSON_IS_WRONG(80070, "渲染json的时候出错，请按照给定的格式传入数据"),
    NEED_3_PARAMS(80071, "缺少用户三要素信息"),
    CANT_FIND_VERSIONID(80072, "产品关联版本出错，无法找到相关的版本"),//无法根据productId找到versionId
    CANT_FIND_PRODUCTID_BY_PRODUCTID(80073, "找不到该产品"),//无法根据productId找到产品
    CANT_READ_RESULT_LOG(80074, "查找的结果非该用户创建，无权查看"),
    CANT_FIND_RESULT(80075, "找不到该评分结果"),
    CANT_FIND_PRODUCTID(80075, "该数据没有记录产品信息，请联系管理员"),//该数据没有记录Product信息，请联系管理员
    NEED_IDCARD(80076, "缺少用户身份证信息"),;

    private int code;
    private String msg;

    ServiceResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceResult getServiceResult() {
        return new ServiceResult(code, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return getServiceResult().toString();
    }
}
