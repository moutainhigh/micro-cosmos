package com.yichen.cosmos.cloud.platform.bean;

public class ResponseStatus {

    /*************
     * 公共模块
     *********************/
    public static final String RESPONSE_CODE_200 = "200";
    public static final String RESPONSE_MSG_200 = "请求成功";

    public static final String RESPONSE_CODE_205 = "205";
    public static final String RESPONSE_MSG_205 = "存在未执行数据";

    public static final String RESPONSE_CODE_404 = "404";
    public static final String RESPONSE_MSG_404 = "请求资源不存在";

    public static final String RESPONSE_CODE_405 = "405";
    public static final String RESPONSE_MSG_405 = "会话已过期,请重新登录!";

    public static final String RESPONSE_CODE_406 = "406";
    public static final String RESPONSE_MSG_406 = "缺少请求参数";

    public static final String RESPONSE_CODE_407 = "407";
    public static final String RESPONSE_MSG_407 = "请求参数错误";

    public static final String RESPONSE_CODE_408 = "408";
    public static final String RESPONSE_MSG_408 = "无法解析access_token参数，请检查传参是否正确!";
    public static final String RESPONSE_CODE_409 = "409";
    public static final String RESPONSE_MSG_409 = "没有获取到token参数，请带上token参数!";
    public static final String RESPONSE_CODE_410 = "410";
    public static final String RESPONSE_MSG_410 = "当前access_token过期或者已经登出，请重新登录!";


    public static final String RESPONSE_CODE_411 = "411";
    public static final String RESPONSE_MSG_411 = "该refresh_token已经过期，请重新登录!";
    public static final String RESPONSE_CODE_412 = "412";
    public static final String RESPONSE_MSG_412 = "该用户已经被删除了，无法创建新的access_token!";

    public static final String RESPONSE_CODE_413 = "413";
    public static final String RESPONSE_MSG_413 = "找不到数据，请重新上传";

    public static final String RESPONSE_CODE_500 = "500";
    public static final String RESPONSE_MSG_500 = "系统繁忙，请稍后再试!";

    //账户模块
    public static final String USER_ACCOUNT_IS_NOT_EXIST_CODE = "60001";
    public static final String USER_ACCOUNT_IS_NOT_EXIST = "用户账号不存在";

    public static final String USER_PASSWORD_IS_NOT_RIGHT_CODE = "60002";
    public static final String USER_PASSWORD_IS_NOT_RIGHT = "用户密码错误";

    public static final String ACCOUNT_IS_EMPTY_CODE = "60003";
    public static final String ACCOUNT_IS_EMPTY = "请输入账号";

    public static final String PASSWORD_IS_EMPTY_CODE = "60004";
    public static final String PASSWORD_IS_EMPTY = "请输入密码";

    public static final String EMAIL_IS_EMPTY_CODE = "60005";
    public static final String EMAIL_IS_EMPTY = "请输入邮箱";

    public static final String EMAIL_IS_NOT_EXIST_CODE = "60006";
    public static final String EMAIL_IS_NOT_EXIST = "邮箱不存在";

    public static final String DYNAMICCODE_IS_NOT_EXIST_CODE = "60007";
    public static final String DYNAMICCODE_IS_NOT_EXIST = "请输入验证码";

    public static final String SERIALNO_IS_NOT_EXIST_CODE = "60008";
    public static final String SERIALNO_IS_NOT_EXIST = "无效序列号";

    public static final String DYNAMICCODE_IS_OUT_TIME_CODE = "60009";
    public static final String DYNAMICCODE_IS_OUT_TIME = "验证码已过期，请重新获取!";

    public static final String DYNAMICCODE_IS_ERROR_CODE = "60010";
    public static final String DYNAMICCODE_IS_ERROR = "验证码不正确，请重新输入!";

    public static final String EMAIL_IS_NOT_VALID_CODE = "60011";
    public static final String EMAIL_IS_NOT_VALID = "请输入有效的邮箱";

    public static final String SYSTEM_CONFIG_ERROR_CODE = "60012";
    public static final String SYSTEM_CONFIG_ERROR = "账号异常,请联系客服";

    public static final String EMAIL_URL_INVALID_ERROR_CODE = "60013";
    public static final String EMAIL_URL_INVALID_ERROR = "无效链接，邮箱验证失败!";

    public static final String EMAIL_OUTTIME_INVALID_ERROR_CODE = "60014";
    public static final String EMAIL_OUTTIME_INVALID_ERROR = "对不起，您的邮箱验证请求超时,请重新获取!";


    public static final String PHONE_IS_EMPTY_CODE = "60015";
    public static final String PHONE_IS_EMPTY = "手机号不可为空!";

    public static final String PHONENUM_IS_ERROR_CODE = "60016";
    public static final String PHONENUM_IS_ERROR = "手机号输入错误!";

    public static final String COMFIRM_DYNAMIC_CODE_TYPE_CODE = "60017";
    public static final String COMFIRM_DYNAMIC_CODE_TYPE = "请确认验证码类型!";

    public static final String DYNAMIC_CODE_OVER_TIMES_CODE = "60018";
    public static final String DYNAMIC_CODE_OVER_TIMES = "今日验证码已超过5次,请明日再试!";

    public static final String PHONE_IS_NOT_REGISTED_CODE = "60019";
    public static final String PHONE_IS_NOT_REGISTED = "手机号尚未注册!";

    public static final String ACCOUTN_SMS_ERROR_CODE = "60020";
    public static final String ACCOUTN_SMS_ERROR = "获取短信失败!";

    public static final String PHONE_IS_NOT_REGISTED_OR_ACCOUNT_ISNOT_EXIST_CODE = "60022";
    public static final String PHONE_IS_NOT_REGISTED_OR_ACCOUNT_ISNOT_EXIST = "手机号未注册或账号不存在!";

    public static final String PASSWORD_ISNOT_CONSISTENT_CODE = "60023";
    public static final String PASSWORD_ISNOT_CONSISTENT = "两次密码输入不一致!";

    public static final String INVALID_REQUEST_URL_CODE = "60024";
    public static final String INVALID_REQUEST_URL = "无效请求链接!";

    public static final String REGISTER_FAIL_CODE = "60025";
    public static final String REGISTER_FAIL = "对不起，注册失败!";

    public static final String ACCOUNT_EXIST_CODE = "60026";
    public static final String ACCOUNT_EXIST = "账号已经存在，注册失败!";
    public static final String EMAIL_EXIST_CODE = "60027";
    public static final String EMAIL_EXIST = "邮箱已经存在，注册失败!";
    public static final String PHONE_EXIST_CODE = "60028";
    public static final String PHONE_EXIST = "手机已经存在，注册失败!";


    public static final String GROUP_ID_IS_EMPTY_CODE = "60029";
    public static final String GROUP_ID_IS_EMPTY = "缺少用户角色ID参数!";

    public static final String WORKFLOW_ISNOT_EMPTY_CODE = "60030";
    public static final String WORKFLOW_ISNOT_EMPTY = "工作流非空，请删除名下的规则集!";

    public static final String RULESET_ISNOT_EMPTY_CODE = "60031";
    public static final String RULESET_ISNOT_EMPTY = "规则集非空，请删除名下的规则组!";

    public static final String RULEGROUP_ISNOT_EMPTY_CODE = "60032";
    public static final String RULEGROUP_ISNOT_EMPTY = "规则组非空，请删除名下的规则!";

    public static final String PROJECT_VERSION_HAS_BEAN_LOCKED_CODE = "60033";
    public static final String PROJECT_VERSION_HAS_BEAN_LOCKED = "该版本已被其他用户锁定，如想继续，请先解锁!";

    public static final String DUPLICATE_PROJECT_NAME_CODE = "60034";
    public static final String DUPLICATE_PROJECT_NAME = "该项目名已存在，请重新命名!";

    public static final String DUPLICATE_PROJECT_VERSION_CODE = "60035";
    public static final String DUPLICATE_PROJECT_VERSION = "该项目版本号已被使用，请重新输入!";

    public static final String WRONG_FORM_NAME_CODE = "60036";
    public static final String WRONG_FORM_NAME = "表单英文名称不符合规范,请重新命名!";

    public static final String WRONG_FIELD_NAME_CODE = "60037";
    public static final String WRONG_FIELD_NAME = "英文名称不符合规范,请重新命名!";

    public static final String PROJECT_VERSION_LIST_ISNOT_EMPTY_CODE = "60038";
    public static final String PROJECT_VERSION_LIST_ISNOT_EMPTY = "项目非空，请删除名下的项目版本!";

    public static final String VERSION_RULESET_LIST_ISNOT_EMPTY_CODE = "60039";
    public static final String VERSION_RULESET_LIST_ISNOT_EMPTY = "项目版本非空，请删除名下的工作流!";

    public static final String VERSION_START_NODE_EXIST_CODE = "60040";
    public static final String VERSION_START_NODE_EXIST = "流程起始节点已存在!";

    public static final String VERSION_END_NODE_EXIST_CODE = "60041";
    public static final String VERSION_END_NODE_EXIST = "流程结束节点已存在!";

    public static final String WORK_FLOW_NODE_NOT_EXIST_CODE = "60042";
    public static final String WORK_FLOW_NODE_NOT_EXIST = "工作流节点不存在";

    public static final String WORK_FLOW_LINE_NOT_EXIST_CODE = "60043";
    public static final String WORK_FLOW_LINE_NOT_EXIST = "线指向不存在";

    public static final String WORK_FLOW_LINE_CONDITION_NOT_EXIST_CODE = "60044";
    public static final String WORK_FLOW_LINE_CONDITION_NOT_EXIST = "线指向条件不存在";

    public static final String PARAMETER_IS_NOT_CORRECT_CODE = "60045";
    public static final String PARAMETER_IS_NOT_CORRECT = "请求参数不合法";

    public static final String PROJECT_VERSION_NOT_EXIST_CODE = "60045";
    public static final String PROJECT_VERSION_NOT_EXIST = "请求项目版本不存在";

    public static final String PROJECT_NOT_EXIST_CODE = "60046";
    public static final String PROJECT_NOT_EXIST = "指定项目不存在";

    public static final String WISDOM_LIBRARY_NOT_EXIST_CODE = "60047";
    public static final String WISDOM_LIBRARY_NOT_EXIST = "规则库不存在";


    public static final String SERIALNO_HAS_BEEN_OVER_TIME_CODE = "60048";
    public static final String SERIALNO_HAS_BEEN_OVER_TIME = "请求已超时，请重新操作!";

    public static final String WISDOM_FORM_NOT_EXIST_CODE = "60049";
    public static final String WISDOM_FORM_NOT_EXIST = "表单不存在";

    public static final String WISDOM_FIELD_NOT_EXIST_CODE = "60050";
    public static final String WISDOM_FIELD_NOT_EXIST = "字段不存在";

    public static final String WISDOM_IMPORT_NOT_EXIST_CODE = "60051";
    public static final String WISDOM_IMPORT_NOT_EXIST = "导入项不存在";

    public static final String WISDOM_FIELD_NAME_NOT_EXIST_CODE = "60052";
    public static final String WISDOM_FIELD_NAME_NOT_EXIST = "字段名称已存在，请重新命名中文名或英文名";

    public static final String WISDOM_FORM_NAME_NOT_EXIST_CODE = "60053";
    public static final String WISDOM_FORM_NAME_NOT_EXIST = "表单名称已存在，请重新命名中文名或英文名";

    public static final String RULE_SET_NOT_EXIST_CODE = "60054";
    public static final String RULE_SET_NOT_EXIST = "指定规则集不存在";

    public static final String RULE_GROUP_NOT_EXIST_CODE = "60055";
    public static final String RULE_GROUP_NOT_EXIST = "指定规则组不存在";

    public static final String WISDOM_RULE_NOT_EXIST_CODE = "60056";
    public static final String WISDOM_RULE_NOT_EXIST = "指定规则不存在";

    public static final String WISDOM_RULE_CONDITION_NOT_EXIST_CODE = "60057";
    public static final String WISDOM_RULE_CONDITION_NOT_EXIST = "指定规则条件不存在";

    public static final String WISDOM_RULE_DECISION_NOT_EXIST_CODE = "60058";
    public static final String WISDOM_RULE_DECISION_NOT_EXIST = "指定规则决策不存在";

    public static final String RULE_GROUP_NAME_NOT_EXIST_CODE = "60059";
    public static final String RULE_GROUP_NAME_NOT_EXIST = "规则组名称已存在，请重新命名！";

    public static final String PRODUCT_NOT_EXIST_CODE = "60060";
    public static final String PRODUCT_NOT_EXIST = "请求产品信息不存在";

    public static final String WORK_FLOW_LINE_NAME_EXIST_CODE = "60061";
    public static final String WORK_FLOW_LINE_NAME_EXIST = "线指向名称已存在，请重新命名！";

    public static final String WORK_FLOW_LINE_EXIST_CODE = "60062";
    public static final String WORK_FLOW_LINE_EXIST = "线指向已存在！";

    public static final String USER_IS_NOT_EXIST_CODE = "60063";
    public static final String USER_IS_NOT_EXIST = "当前用户不存在！";


    public static final String WORK_FLOW_NODE_NAME_EXIST_CODE = "60064";
    public static final String WORK_FLOW_NODE_NAME_EXIST = "工作流节点名称已存在，请重新命名";

    public static final String SYSTEM_FIXED_FORM_DATA_CODE = "60065";
    public static final String SYSTEM_FIXED_FORM_DATA = "系统表单数据，不允许修改！";

    public static final String WORK_FLOW_START_NODE_ERROR_CODE = "60066";
    public static final String WORK_FLOW_START_NODE_ERROR = "工作流起点不能选择结束节点！";

    public static final String WORK_FLOW_STOP_NODE_ERROR_CODE = "60067";
    public static final String WORK_FLOW_STOP_NODE_ERROR = "工作流终点不能选择开始节点！";

    public static final String PLEASE_CHOOSE_THE_LIBRARY_TYPE_CODE = "60068";
    public static final String PLEASE_CHOOSE_THE_LIBRARY_TYPE = "请选择一种规则库类型！";

    public static final String SYSTEM_FIXED_FIELD_DATA_CODE = "60069";
    public static final String SYSTEM_FIXED_FIELD_DATA = "系统字段数据，不允许修改！";


    public static final String DUPLICATE_PRODUCT_NAME_CODE = "60070";
    public static final String DUPLICATE_PRODUCT_NAME = "该产品名称已被使用，请重新输入!";

    public static final String RULE_SET_NAME_EXIST_ENTRY_AGAIN_CODE = "60071";
    public static final String RULE_SET_NAME_EXIST_ENTRY_AGAIN = "规则集名称已存在，请重新命名！";

    public static final String FLOW_NODE_AND_DATASOURCE_RELATION_EXIST_NODE = "60072";
    public static final String FLOW_NODE_AND_DATASOURCE_RELATION_EXIST = "流程节点已关联数据源";

    public static final String RULESETNODE_BIND_DATASOURCE_CODE = "60073";
    public static final String RULESETNODE_BIND_DATASOURCE = "请选择规则集节点绑定数据源";

    public static final String DATASOURCE_DOESNT_EXISTS_CODE = "60074";
    public static final String DATASOURCE_DOESNT_EXISTS = "数据源不存在，请重新选择";

    public static final String SPLITNODE_CAN_HAVE_MORE_THAN_ONE_OUT_LINE_CODE = "60075";
    public static final String SPLITNODE_CAN_HAVE_MORE_THAN_ONE_OUT_LINE = "只有分歧节点可以有多条流出的线！";

    public static final String JOINNODE_CAN_HAVE_MORE_THAN_ONE_IN_LINE_CODE = "60076";
    public static final String JOINNODE_CAN_HAVE_MORE_THAN_ONE_IN_LINE = "只有合并节点可以有多条流入的线！";

    //=====================61xxxx=用户账号系列============================================================
    public static final String USER_ACCOUNT_IS_LOCKED_CODE = "61000";
    public static final String USER_ACCOUNT_IS_LOCKED = "账号已被锁定";

    public static final String USER_ACCOUNT_ORG_IS_LOCKED_CODE = "61001";
    public static final String USER_ACCOUNT_ORG_IS_LOCKED = "所属机构已被注销";

    public static final String ORG_IS_NOT_EXISTS_CODE = "61002";
    public static final String ORG_IS_NOT_EXISTS = "所属机构不存在";

    public static final String ORG_IS_AUDITING_CODE = "61003";
    public static final String ORG_IS_AUDITING = "所属机构尚待审核中";

    public static final String ORG_STATE_REFUZE_CODE = "61004";
    public static final String ORG_STATE_REFUZE = "所属机构审核拒绝";


    public static final String CHILD_ACCOUNT_HAS_NO_AUTHORITY_TO_CREATE_USER_CODE = "61004";
    public static final String CHILD_ACCOUNT_HAS_NO_AUTHORITY_TO_CREATE_USER = "子账号无创建权限";


    public static final String CHILD_ACCOUNT_HAS_NO_AUTHORITY_TO_DELETE_USER_CODE = "61005";
    public static final String CHILD_ACCOUNT_HAS_NO_AUTHORITY_TO_DELETE_USER = "对不起，子账号无删除权限";

    public static final String ACCOUNT_INFO_DOESNT_EXIST_CODE = "61006";
    public static final String ACCOUNT_INFO_DOESNT_EXIST = "账户信息不存在";

    public static final String NEW_PASSWORD_DOESNT_MATCH_PRE_PASSWORD_CODE = "61007";
    public static final String NEW_PASSWORD_DOESNT_MATCH_PRE_PASSWORD = "新密码和确认密码不相同";

    public static final String USER_CENTER_PLEASE_ENTER_OLD_PASSWORD_CODE = "61008";
    public static final String USER_CENTER_PLEASE_ENTER_OLD_PASSWORD = "请输入原密码!";

    public static final String USER_CENTER_PLEASE_ENTER_NEW_PASSWORD_CODE = "61009";
    public static final String USER_CENTER_PLEASE_ENTER_NEW_PASSWORD = "请输入新密码!";

    public static final String USER_CENTER_PLEASE_ENTER_CONFIRM_PASSWORD_CODE = "61010";
    public static final String USER_CENTER_PLEASE_ENTER_CONFIRM_PASSWORD = "请输入确认密码!";

    public static final String WRONG_OLD_PASSWORD_CODE = "61011";
    public static final String WRONG_OLD_PASSWORD = "原密码错误!";


    public static final String NOT_CHILD_OF_CURRENT_ACCOUNT_CODE = "61012";
    public static final String NOT_CHILD_OF_CURRENT_ACCOUNT = "非当前账号名下子账号，不可操作!";

    public static final String CHILD_ACCOUNT_HAS_NO_AUTHORITY_TO_MODIFY_USER_CODE = "61013";
    public static final String CHILD_ACCOUNT_HAS_NO_AUTHORITY_TO_MODIFY_USER = "对不起，子账号无更新权限";


    public static final String CHILD_ACCOUNT_HAS_NO_AUTHORITY_TO_RESET_PASSWORD_USER_CODE = "61014";
    public static final String CHILD_ACCOUNT_HAS_NO_AUTHORITY_TO_RESET_PASSWORD_USER = "对不起，子账号无重置密码权限";

    public static final String ASSIGN_ROLE_TO_SUB_ACCOUNT_CODE = "61015";
    public static final String ASSIGN_ROLE_TO_SUB_ACCOUNT = "请先联系主账号进行角色分配。";

    public static final String DONT_HAVE_ANY_MENU_TO_DISPLAY_CODE = "61016";
    public static final String DONT_HAVE_ANY_MENU_TO_DISPLAY = "菜单尚未进行分配，导航栏无法显示";

    public static final String ORG_IS_LOCKED_CODE = "61017";
    public static final String ORG_IS_LOCKED = "机构已被锁定/关闭";

    //=====================62xxxx=项目版本号系列============================================================
    public static final String PROJECT_VERSION_ID_ISNOT_EXIST_CODE = "62000";
    public static final String PROJECT_VERSION_ID_ISNOT_EXIST = "指定项目名下无开启版本";

    public static final String VERSION_IS_NOT_BEYONG_THIS_PROJECT_CODE = "62001";
    public static final String VERSION_IS_NOT_BEYONG_THIS_PROJECT = "关联版本号非当前指定项目名下或该版本未开启状态";

    public static final String VERSION_IS_ONLINE_CODE = "62002";
    public static final String VERSION_IS_ONLINE = "该版本正在运行，不能关闭/删除!";

    public static final String PROJECT_IS_NOT_BEYOND_THE_USER_CODE = "62003";
    public static final String PROJECT_IS_NOT_BEYOND_THE_USER = "对当前项目无操作权限!";

    public static final String VERSION_IS_NOT_BEYOND_THE_USER_CODE = "62004";
    public static final String VERSION_IS_NOT_BEYOND_THE_USER = "对当前版本无操作权限!";

    public static final String WORKFLOW_IS_NOT_BEYOND_THE_USER_CODE = "62005";
    public static final String WORKFLOW_IS_NOT_BEYOND_THE_USER = "对当前工作流无操作权限!";

    public static final String WORKFLOWLINE_IS_NOT_BEYOND_THE_USER_CODE = "62006";
    public static final String WORKFLOWLINE_IS_NOT_BEYOND_THE_USER = "对当前工作流线无操作权限!";

    public static final String VERSION_DEPLOY_FAILURE_CODE = "62007";
    public static final String VERSION_DEPLOY_FAILURE = "规则编辑有误,版本发布失败!";

    public static final String VERSION_DOESNT_EXISTS_CODE = "62008";
    public static final String VERSION_DOESNT_EXISTS = "版本不存在!";

    public static final String VERSION_DOESNT_CLOSE_CODE = "62009";
    public static final String VERSION_DOESNT_CLOSE = "版本处于开启状态，若需修改版本请先将版本关闭!";

    public static final String RULESET_HAVE_RULES_CODE = "62010";
    public static final String RULESET_HAVE_RULES = "只有规则集节点可以配置规则!";

    //=====================63xxxx=规则系列===============================================================
    public static final String RULESET_OF_RULElIST_ISNOT_EMPTY_CODE = "63000";
    public static final String RULESET_OF_RULElIST_ISNOT_EMPTY = "规则集非空，请删除名下的规则!";

    public static final String RULE_NAME_HAS_EXIST_CODE = "63001";
    public static final String RULE_NAME_HAS_EXIST = "规则名称已存在，请重新输入!";

    public static final String RULESET_IS_NOT_BEYOND_THE_USER_CODE = "63002";
    public static final String RULESET_IS_NOT_BEYOND_THE_USER = "对当前规则集无操作权限!";

    public static final String WISDOMRULE_IS_NOT_BEYOND_THE_USER_CODE = "63003";
    public static final String WISDOMRULE_IS_NOT_BEYOND_THE_USER = "对当前规则无操作权限!";

    public static final String WISDOMRULE_CONDITION_IS_NOT_BEYOND_THE_USER_CODE = "63004";
    public static final String WISDOMRULE_CONDITION_IS_NOT_BEYOND_THE_USER = "对当前规则条件无操作权限!";

    public static final String WISDOMRULE_DECISION_IS_NOT_BEYOND_THE_USER_CODE = "63005";
    public static final String WISDOMRULE_DECISION_IS_NOT_BEYOND_THE_USER = "对当前规则决策无操作权限!";

    public static final String WISDOM_FIELD_IS_NOT_BEYOND_THE_USER_CODE = "63006";
    public static final String WISDOM_FIELD_IS_NOT_BEYOND_THE_USER = "对当前字段无操作权限!";

    public static final String WISDOM_FORM_IS_NOT_BEYOND_THE_USER_CODE = "63007";
    public static final String WISDOM_FORM_IS_NOT_BEYOND_THE_USER = "对当前表单无操作权限!";
    //=====================64xxxx=产品系列===============================================================
    public static final String PRODUCT_IS_NOT_BEYOND_THE_USER_CODE = "64000";
    public static final String PRODUCT_IS_NOT_BEYOND_THE_USER = "对当前产品无操作权限!";

    public static final String PRODUCT_HAS_RELATION_WITH_OTHER_VERSION_CODE = "64001";
    public static final String PRODUCT_HAS_RELATION_WITH_OTHER_VERSION = "当前产品已经关联其它项目版本!";

    public static final String THE_PRODUCT_DOES_NOT_EXISTS_CODE = "64002";
    public static final String THE_PRODUCT_DOES_NOT_EXISTS = "所选产品不存在，请重新选择产品!";

    public static final String THE_PRODUCT_DOES_NOT_BE_OPENED_CODE = "64003";
    public static final String THE_PRODUCT_DOES_NOT_BE_OPENED = "所选产品已关闭，请重新开启!";

    public static final String THE_PRODUCT_DOES_NOT_RELATE_VERSION_CODE = "64004";
    public static final String THE_PRODUCT_DOES_NOT_RELATE_VERSION = "产品尚未关联规则版本!";


    //======================== admin系列 7XXXX================================================
    public static final String ADMIN_LOGIN_USERNAME_IS_EMPTY_CODE = "70000";
    public static final String ADMIN_LOGIN_USERNAME_IS_EMPTY = "登录账号为空!";

    public static final String ADMIN_LOGIN_PASSWORD_IS_EMPTY_CODE = "70001";
    public static final String ADMIN_LOGIN_PASSWORD_IS_EMPTY = "登录密码为空!";

    public static final String ADMIN_LOGIN_USERNAME_DOESNT_EXIST_CODE = "70002";
    public static final String ADMIN_LOGIN_USERNAME_DOESNT_EXIST = "登录账号不存在!";

    public static final String ADMIN_LOGIN_PASSWORD_ERROR_CODE = "70003";
    public static final String ADMIN_LOGIN_PASSWORD_ERROR = "登录密码错误!";

    public static final String SPECIFY_ORG_DOESNT_EXISTS_CODE = "70004";
    public static final String SPECIFY_ORG_DOESNT_EXISTS = "指定的组织机构不存在!";

    public static final String USER_ACCOUNT_EXISTS_CODE = "70005";
    public static final String USER_ACCOUNT_EXISTS = "账号已经存在,请重新输入!";

    public static final String USER_EMAIL_EXISTS_CODE = "70006";
    public static final String USER_EMAIL_EXISTS = "邮箱已经存在,请重新输入!";

    public static final String USER_PHONE_EXISTS_CODE = "70007";
    public static final String USER_PHONE_EXISTS = "手机号已经存在,请重新输入!";

    public static final String USER_NAME_EXISTS_CODE = "70009";
    public static final String USER_NAME_EXISTS = "联系人已经存在,请重新输入!";

    public static final String USER_NAME_ACCOUNT_EXISTS_CODE = "70009";
    public static final String USER_NAME_ACCOUNT_EXISTS = "联系人已经存在,请重新输入!";

    public static final String SPECIFY_CUSTOMER_DOESNT_EXISTS_CODE = "70008";
    public static final String SPECIFY_CUSTOMER_DOESNT_EXISTS = "指定客户不存在!";


    public static final String PLEASE_ENTER_OLD_PASSWORD_CODE = "70010";
    public static final String PLEASE_ENTER_OLD_PASSWORD = "请输入原密码!";

    public static final String PLEASE_ENTER_NEW_PASSWORD_CODE = "70011";
    public static final String PLEASE_ENTER_NEW_PASSWORD = "请输入新密码!";

    public static final String PLEASE_ENTER_CONFIRM_PASSWORD_CODE = "70012";
    public static final String PLEASE_ENTER_CONFIRM_PASSWORD = "请输入确认密码!";

    public static final String PLEASE_ENTER_NEW_DOESNT_MATCHES_CONFIRM_PASSWORD_CODE = "70013";
    public static final String PLEASE_ENTER_NEW_DOESNT_MATCHES_CONFIRM_PASSWORD = "新密码与确认密码不一致!";

    public static final String PLEASE_ENTER_OLD_PASSWORD_ERROR_CODE = "70014";
    public static final String PLEASE_ENTER_OLD_PASSWORD_ERROR = "原密码错误!";


    public static final String PLEASE_CONTACT_SYSTEM_MANAGER_CODE = "70015";
    public static final String PLEASE_CONTACT_SYSTEM_MANAGER = "若忘记密码请联系管理员!";


    public static final String SPECIFY_CUSTOMER_OF_FATHER_ACCOUNT_DOESNT_EXISTS_CODE = "70016";
    public static final String SPECIFY_CUSTOMER_OF_FATHER_ACCOUNT_DOESNT_EXISTS = "所属主账号不存在,解锁失败!";


    public static final String SPECIFY_CUSTOMER_OF_FATHER_ACCOUNT_LOCKED_CODE = "70017";
    public static final String SPECIFY_CUSTOMER_OF_FATHER_ACCOUNT_LOCKED = "所属主账号已被锁定，请先解锁主账号!";


    public static final String SPECIFY_CUSTOMER_ACCOUNT_LOCKED_CODE = "70018";
    public static final String SPECIFY_CUSTOMER_ACCOUNT_LOCKED = "账号已被锁定，请先解锁!";


    public static final String ADMIN_ACCOUNT_DOESNT_EXIST_CODE = "70019";
    public static final String ADMIN_ACCOUNT_DOESNT_EXIST = "账号不存在!";


    public static final String NOT_MAIN_ACCOUNT_DOESNT_CREATE_ACCOUNT_CODE = "70020";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_CREATE_ACCOUNT = "非主账号不能创建子账号!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_DELETE_ACCOUNT_CODE = "70021";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_DELETE_ACCOUNT = "非主账号不能进行删除账号操作!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_MODIFY_ACCOUNT_CODE = "70022";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_MODIFY_ACCOUNT = "非主账号不能进行更新账号操作!";


    public static final String NOT_MAIN_ACCOUNT_DOESNT_RESET_ACCOUNT_PASSWORD_CODE = "70023";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_RESET_ACCOUNT_PASSWORD = "非主账号不能进行重置账号密码操作!";

    public static final String NOT_MAIN_ACCOUNT_AUTHORITY_CODE = "70130";
    public static final String NOT_MAIN_ACCOUNT_AUTHORITY = "当前账号无操作权限";


    public static final String PLEASE_ENTER_REALNAME_CODE = "70024";
    public static final String PLEASE_ENTER_REALNAME = "请输入真实姓名!";


    public static final String PLEASE_ENTER_PHONE_CODE = "70025";
    public static final String PLEASE_ENTER_PHONE = "请输入手机号!";

    public static final String PLEASE_ENTER_EMAIL_CODE = "70026";
    public static final String PLEASE_ENTER_EMAIL = "请输入邮箱地址!";

    public static final String PLEASE_ENTER_USERNAME_CODE = "70027";
    public static final String PLEASE_ENTER_USERNAME = "请输入账户!";

    public static final String PLEASE_ENTER_ACCOUNT_CODE = "70027";
    public static final String PLEASE_ENTER_ACCOUNT = "请输入账户!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_ASSIGN_ROLE_CODE = "70028";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_ASSIGN_ROLE = "非主账号没有分配角色权限!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_DELETE_ROLE_CODE = "70029";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_DELETE_ROLE = "非主账号没有删除角色权限!";

    public static final String ROLE_DOESNT_EXIST_CODE = "70030";
    public static final String ROLE_DOESNT_EXIST = "角色不存在!";


    public static final String USER_ROLE_RELATIONLSIT_BEYOND_ROLE_CANT_DELETE_CODE = "70031";
    public static final String USER_ROLE_RELATIONLSIT_BEYOND_ROLE_CANT_DELETE = "角色下存在关联账号时，不可删除!";

    public static final String ROLE_STATUS_IS_CLOSED_CODE = "70032";
    public static final String ROLE_STATUS_IS_CLOSED = "角色状态已被关闭，不可关联账号!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_CLOSE_OPEN_ROLE_CODE = "70033";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_CLOSE_OPEN_ROLE = "非主账号没有关闭/开启角色的权限!";


    public static final String USER_ROLE_RELATIONLSIT_BEYOND_ROLE_CANT_CLOSE_CODE = "70034";
    public static final String USER_ROLE_RELATIONLSIT_BEYOND_ROLE_CANT_CLOSE = "角色下存在关联账号时，不可关闭!";

    public static final String ROLE_STATUS_ERROR_CODE = "70035";
    public static final String ROLE_STATUS_ERROR = "角色状态错误!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_CREATE_ROLE_CODE = "70036";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_CREATE_ROLE = "非主账号没有创建角色的权限!";

    public static final String PLEASE_ENTER_ROLE_NAME_CODE = "70037";
    public static final String PLEASE_ENTER_ROLE_NAME = "请输入角色名称!";

    public static final String PLEASE_ENTER_ROLE_STATUS_CODE = "70037";
    public static final String PLEASE_ENTER_ROLE_STATUS = "请输入角色状态!";

    public static final String PLEASE_ENTER_ROLE_DESC_CODE = "70037";
    public static final String PLEASE_ENTER_ROLE_DESC = "请输入角色描述!";

    public static final String ROLE_NAME_HAS_EXIST_CODE = "70038";
    public static final String ROLE_NAME_HAS_EXIST = "角色名称已存在,请重新输入!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_MODIFY_ROLE_CODE = "70039";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_MODIFY_ROLE = "非主账号没有更新角色的权限!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_CREATE_MENU_CODE = "70080";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_CREATE_MENU = "非主账号没有更新角色的权限!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_MODIFY_MENU_CODE = "70081";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_MODIFY_MENU = "非主账号没有更新角色的权限!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_DELETE_MENU_CODE = "70082";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_DELETE_MENU = "非主账号没有更新角色的权限!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_MODIFY_MENU_STATE_CODE = "70083";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_MODIFY_MENU_STATE = "非主账号不能更新菜单显示状态!";

    public static final String MENU_DOESNT_EXISTS_CODE = "70084";
    public static final String MENU_DOESNT_EXISTS = "菜单不存在!";

    public static final String PLEASE_ENTER_MENU_NAME_CODE = "70085";
    public static final String PLEASE_ENTER_MENU_NAME = "请输入菜单名称!";

    public static final String PLEASE_ENTER_WEB_ROUTE_CODE = "70086";
    public static final String PLEASE_ENTER_WEB_ROUTE = "请输入路由地址!";


    public static final String PLEASE_ENTER_TARGET_URL_CODE = "70087";
    public static final String PLEASE_ENTER_TARGET_URL = "请输入目标链接地址!";

    public static final String PLEASE_ENTER_MENU_NAME_AGAIN_CODE = "70088";
    public static final String PLEASE_ENTER_MENU_NAME_AGAIN = "菜单名称重复,请重新输入!";


    public static final String VERSION_HAS_RELATION_WITH_OTHER_PRODUCTS_CODE = "70089";
    public static final String VERSION_HAS_RELATION_WITH_OTHER_PRODUCTS = "版本已被分配给其它产品!";


    public static final String CURRENT_ACCOUNT_ORGNZATION_HAS_NO_PRODUCTS_CODE = "70090";
    public static final String CURRENT_ACCOUNT_ORGNZATION_HAS_NO_PRODUCTS = "当前账户/机构暂未创建产品!";


    public static final String ORGANIZATION_DOESNT_EXISTS_CODE = "70091";
    public static final String ORGANIZATION_DOESNT_EXISTS = "机构不存在!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_DELETE_ORGNIZATION_CODE = "70092";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_DELETE_ORGNIZATION = "非主账号没有删除机构的权限!";

    public static final String PLEASE_ENTER_COMPANY_NAME_CODE = "70093";
    public static final String PLEASE_ENTER_COMPANY_NAME = "请输入机构名称!";

    public static final String PLEASE_ENTER_CONTACT_NAME_CODE = "70094";
    public static final String PLEASE_ENTER_CONTACT_NAME = "请输入联系人!";

    public static final String PLEASE_ENTER_AUDIT_STATUS_CODE = "70095";
    public static final String PLEASE_ENTER_AUDIT_STATUS = "请选择审核状态!";

    public static final String PLEASE_ENTER_ORG_IS_LOCK_CODE = "70096";
    public static final String PLEASE_ENTER_ORG_IS_LOCK = "请确认是否锁定!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_CREATE_ORGNIZATION_CODE = "70097";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_CREATE_ORGNIZATION = "非主账号没有创建机构的权限!";

    public static final String NOT_MAIN_ACCOUNT_DOESNT_MODIFY_ORGNIZATION_CODE = "70098";
    public static final String NOT_MAIN_ACCOUNT_DOESNT_MODIFY_ORGNIZATION = "非主账号没有更新机构的权限!";


    public static final String COMPANY_NAME_HAS_EXIST_CODE = "70099";
    public static final String COMPANY_NAME_HAS_EXIST = "公司名称已经存在!";
    //------------------- GOVERNER 服务
    public static final String CUSTOMER_DATA_DETAILS_AUDIT_PASS_DONT_NEED_DONE_AGAIN_CODE = "70100";
    public static final String CUSTOMER_DATA_DETAILS_AUDIT_PASS_DONT_NEED_DONE_AGAIN = "已审核且通过，不需要重复审核!";

    public static final String SYSTEM_ACCOUNT_CANT_BE_ENDITED_BY_ITSELF_CODE = "70100";
    public static final String SYSTEM_ACCOUNT_CANT_BE_ENDITED_BY_ITSELF = "系统主账号不可编辑!";

    public static final String SYSTEM_ACCOUNT_CANT_BE_DELETED_BY_ITSELF_CODE = "70100";
    public static final String SYSTEM_ACCOUNT_CANT_BE_DELETED_BY_ITSELF = "系统主账号不可删除!";

    public static final String SYSTEM_ACCOUNT_CANT_BE_RESET_BY_ITSELF_CODE = "70100";
    public static final String SYSTEM_ACCOUNT_CANT_BE_RESET_BY_ITSELF = "系统主账号不可重置!";

    public static final String PLEASE_CHOOSE_DATASOURCE_AND_REPORT_CODE = "80001";
    public static final String PLEASE_CHOOSE_DATASOURCE_AND_REPORT = "请选择数据源或输入报告编号!";


    //=======================================[网关查询]===========================================================

    public static final String GATEWAY_QUERY_DEPLOYED_PRODUCT_LIST_CODE_110001 = "110001";

    public static final String NEVER_RECIEVE_ANY_REPORT_TO_EXECUTE_RULE_TASK_CODE = "110002";
    public static final String NEVER_RECIEVE_ANY_REPORT_TO_EXECUTE_RULE_TASK = "没有接收到数据信息";


    //=======================================[网关查询]===========================================================

    public static final String FILE_CONTENT_ERROR_CODE = "90001";
    public static final String File_CONTENT_ERROR = "文件内容错误";

    public static final String FILE_CONTENT_ERROR_REPEAT_CODE = "90002";
    public static final String FILE_CONTENT_ERROR_REPEAT = "文件内容重复";

    // ========================================[ 资产解绑 ]=============================================================
    public static final String ITEM_RELEASE_ERROR_CODE = "120001";
    public static final String ITEM_RELEASE_ERROR = "解除资产拥有权限失败";

    public static final String ITEM_DOUBLE_BIND_ERROR_CODE = "120002";
    public static final String ITEM_DOUBLE_BIND_ERROR = "账号已绑定当前资产，无法再次绑定";

    // ----------------------------------------[ 测试数据 ] ------------------------------------------
    public static final String TEST_DATA_ALREADY_EXIST_ERROR_CODE = "130000";
    public static final String TEST_DATA_ALREADY_EXIST_CODE = "已有样本正在测试运行，请稍后再测";

}

