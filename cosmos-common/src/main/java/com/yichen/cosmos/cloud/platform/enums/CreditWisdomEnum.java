package com.yichen.cosmos.cloud.platform.enums;

/**
 * 鲸数智慧
 *
 * @author thomas.su
 * @version 1.0
 * @date 2016年8月2日 下午7:26:11
 */
public enum CreditWisdomEnum {

    //手机号状态绑定
    CREDIT_IS_BIND("1", "已绑定"),
    CREDIT_IS_NOT_BIND("0", "未绑定"),

    //动态验证码发送状态
    DYNAMIC_CODE_READY_SEND("1", "准备发送"),
    DYNAMIC_CODE_SENDING_STATE("2", "发送中"),
    DYNAMIC_CODE_SEND_SUCCESS("3", "发送成功"),
    DYNAMIC_CODE_SEND_FAILURE("4", "发送失败"),
    DYNAMIC_CODE_NO_RESPONSE("5", "没有返回信息"),

    //注册类型 0：微信授权  1:手机号注册
    WEIXIN_REGISTER_TYPE("0", "微信公众号授权 "),
    PHONE_REGISTER_TYPE("1", "手机号注册"),
    WEIXIN_MOBILE_REGISTER_TYPE("2", "微信移动端第三方授权 "),
    QQ_REGISTER_TYPE("3", "QQ授权"),
    SINA_REGISTER_TYPE("4", "新浪授权"),
    MAIL_REGISTER_TYPE("5", "邮箱注册"),

    //性别
    SEX_FEMALE("0", "女"),
    SEX_MALE("1", "男"),
    SEX_UNKNOW("2", "未知"),


    //消息是否已删除
    ISNOT_DELETE_STATE("0", "未删除"),
    IS_DELETE_STATE("1", "已删除"),

    //项目版本开启、关闭
    PROJECT_EDITTING_STATE("1", "项目版本编辑中"),
    PROJECT_DEPLOY_STATE("2", "项目版本已部署,启动,开启"),
    PROJECT_STOP_STATE("3", "项目版本已停止"),

    WISDOM_RULE_CONTEXT_CONDITION("1", "条件"),
    WISDOM_RULE_CONTEXT_DECISION("2", "决策"),


    WISDOM_LOCK_STATE("1", "版本锁定"),
    WISDOM_UNLOCK_STATE("0", "版本未锁定"),


    EXECUTE_ALL_RULES("1", "规则全部执行"),
    EXECUTE_SINGLETON_RULE("2", "规则独占执行"),

    OPERATOR_DROOLS_SET("set", "设值"),
    OPERATOR_DROOLS_ADD("add", "累加"),

    START_TYPE_NODE("1", "开始节点"),
    END_TYPE_NODE("2", "结束节点"),
    RULESET_TYPE_NODE("3", "规则集节点"),//规则集 RuleSetNode
    SPLIT_TYPE_NODE("4", "判断节点"),//splitNode
    JOIN_TYPE_NODE("5", "合并节点"),//joinNode jbpm中多分支只有一个end节点 所以要合并所有分支到一个end


    //产品开启、关闭
    PRODUCT_STATE_OPENED("1", "开启"),
    PRODUCT_STATE_CLOSED("0", "关闭"),

    WISDOM_MAIN_ACCOUNT("0", "主账号"),//对应表wisdom_user中father_id字段

    WISDOM_SYSTEM_DATA_FIXED("1", "系统原始表单、字段，不允许用户修改、删除、增加"),
    WISDOM_SYSTEM_DATA_NOT_FIXED("0", "用户自定义表单、字段，允许用户增删查改"),


    //账号
    ACCOUNT_IS_LOCKED("1", "账号已锁定"),
    ACCOUNT_ISNOT_LOCKED("0", "账号未锁定"),

    //机构账号是否锁定 (正常:OK 锁定LOCKED)
    ORG_IS_OK("OK", "机构正常"),
    ORG_IS_LOCKED("LOCKED", "机构已锁定"),

    //机构审核状态(通过:PASS;拒绝:REFUSED;待审核:AUDIT;跟进:FOLLOW)
    ORG_AUDIT_STATE_PASS("PASS", "通过"),
    ORG_AUDIT_STATE_REFUSED("REFUSED", "拒绝"),
    ORG_AUDIT_STATE_AUDIT("AUDIT", "待审核"),
    ORG_AUDIT_STATE_FOLLOW("FOLLOW", "跟进"),


    //记录有效
    FLAG_RECORD_IS_VALID("1", "有效"),
    //记录无效，逻辑删除
    FLAG_RECORD_IS_INVALID("0", "无效"),

    //产品和版本的关联
    PRODUCT_VERSION_ISONLINE("1", "在线"),
    PRODUCT_VERSION_ISDELINE("0", "关闭中"),


    //角色状态
    ROLE_STATUS_OPEN("1", "开启"),
    ROLE_STATUS_CLOSE("0", "关闭"),

    //菜单显示
    MENU_IS_DISPLAY("1", "显示"),
    MENU_ISNT_DISPLAY("0", "不显示"),

    //菜单主节点
    MENU_PARENT_ID("0", "主菜单"),


    MENU_IS_CHOOSE("1", "选中"),
    MENU_ISNT_CHOOSE("0", "未选中"),


    //参考wisdom_dictionary:370~372
    //审核结果值
    REPORT_RESULT_PASS("通过", "报告审核通过"),
    REPORT_RESULT_REFUZE("拒绝", "报告审核拒绝"),
    REPORT_RESULT_AUDIT("人工审核", "报告人工审核"),

    //审核结果值对应的code
    REPORT_RESULT_PASS_AUDITCODE("1", "报告审核通过"),
    REPORT_RESULT_REFUZE_AUDITCODE("2", "报告审核拒绝"),
    REPORT_RESULT_AUDIT_AUDITCODE("3", "报告人工审核"),

    //审批状态
    REPORT_AUDIT_STATUS_WAIT_AUDIT("0", "人工审核状态下：未审核"),
    REPORT_AUDIT_STATUS_AUDITED("1", "人工审核状态下：已审核"),
    REPORT_AUDIT_STATUS_NOT_AUDITED("2", "通过或拒绝状态下：不参与审核"),

    //审批管理
    AUDIT_HAS_DONE("1", "已审核"),
    AUDIT_HASNT_DONE("0", "未审核"),

    IS_FORMULA_CODE("10000", "是公式"),
    ISNOT_FORMULA_CODE("20000", "不是公式"),

    RULE_CONDITION("1", "条件"),
    RULE_DECISION("2", "决策"),


    RULE_SET_TYPE_DRL("1", "普通规则文件"),
    RULE_SET_TYPE_TABLE("2", "规则表"),
    RULE_SET_TYPE_TREE("3", "规则树"),
    RULE_SET_TYPE_SCORE_CARD("4", "记分卡"),

    //item_owner
    ITEM_TYPE_PRODUCT("1", "产品"),
    ITEM_TYPE_PROJECT("0", "决策"),

    //决策 产品 - 绑定
    BIND_ITEM("1", "绑定"),
    UNBIND_ITEM("0", "非绑定"),
    ALL_STATUS_ITEM("2", "所有状态"),

    //只显示我参与的产品
    SHOW_RELEATE_PRODUCT("1", "显示我参与的和我拥有的产品"),
    SHOW_ALL_PRODUCT("0", "显示所有的产品"),

    //excel离线测试
    TEST_TYPE_SINGLE("single", "但版本测试"),
    TEST_TYPE_AB("ab", "ab测试"),

    DATA_SOURCE_ONLINE("1", "上线"),//处于上线状态的数据源不能删除
    DATA_SOURCE_DEVELOP("2", "开发"),
    DATA_SOURCE_DISLINE("3", "下线"),

    VERSION_IS_PURE_RULE("1", "无流程(纯规则)"),
    VERSION_ISNOT_PURE_RULE("0", "有流程"),
    VERSION_IS_SPLIT_RULE("2", "多分支"),

    JINGSHU_DATASOURCE_ORGID("0", "鲸数提供数据源，默认orgId为0"),
    JINGSHU_DATASOURCE_SYSTEMFIXED("1", "1:系统提供的数据源"),
    JINGSHU_DATASOURCE_NOT_SYSTEMFIXED("0", "0：机构自提供数据源"),;


    private String code;
    private String value;


    CreditWisdomEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getExecutorName(String executorId) {
//		EXECUTE_ALL_RULES("1","规则全部执行"),
//				EXECUTE_SINGLETON_RULE("2","规则独占执行"),

        if (EXECUTE_ALL_RULES.getCode().equals(executorId)) {
            return EXECUTE_ALL_RULES.getValue();
        }

        if (EXECUTE_SINGLETON_RULE.getCode().equals(executorId)) {
            return EXECUTE_SINGLETON_RULE.getValue();
        }

        return "";
    }

    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }


}
