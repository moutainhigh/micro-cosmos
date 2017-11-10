package com.yichen.cosmos.cloud.platform.bean.gateway;

public class GatewayErrorCode {

    // ================ 通用错误提示 开始============================//

    public static final String RESPONSE_CODE_200 = "200";
    public static final String RESPONSE_MSG_200 = "success";

    public static final String RESPONSE_CODE_400 = "400";
    public static final String RESPONSE_MSG_400 = "request parameter error";

    public static final String RESPONSE_CODE_404 = "404";
    public static final String RESPONSE_MSG_404 = "not fund";

    public static final String RESPONSE_CODE_405 = "405";
    public static final String RESPONSE_MSG_405 = "request method is not supported";

    public static final String RESPONSE_CODE_500 = "500";
    public static final String RESPONSE_MSG_500 = "error";

    public static final String RESPONSE_CODE_504 = "504";
    public static final String RESPONSE_MSG_504 = "the request timeout";


    // ================ 通用错误提示 结束============================//


    //=============== 签名验证的错误提示 开始 ==================//
    public static String AUTH_MISS_PARAMS_CODE = "301"; // 缺少参数
    public static String AUTH_MISS_PARAMS_MSG = "缺少必要参数";

    public static String AUTH_PERTNER_ID_ILLEGAL_CODE = "302"; // 非法的商户
    public static String AUTH_PERTNER_ID_ILLEGAL_MSG = "非法的商户编号";

    public static String AUTH_ORG_NO_OPEN_CODE = "303"; // 商户没有授权
    public static String AUTH_ORG_NO_OPEN_MSG = "商户没有授权，请联系客服";

    public static String AUTH_SIGN_NO_PASS_CODE = "304"; // 商户没有授权
    public static String AUTH_SIGN_NO_PASS_MSG = "密钥验证失败";

    public static String AUTH_PARAM_TYPE_ERROR_CODE = "305"; // 参数类型不正确
    public static String AUTH_PARAM_TYPE_ERROR_MSG = "参数不符合"; // 参数类型不正确

    public static String AUTH_MERCHANT_AUDIT_PASS_ERROR_CODE = "306";//商户机构尚未审核通过
    public static String AUTH_MERCHANT_AUDIT_PASS_ERROR_MSG = "商户机构尚未审核通过";


    public static String AUTH_PRODUCT_ID_ILLEGAL_ERROR_CODE = "307";//非法的产品编号
    public static String AUTH_PRODUCT_ID_ILLEGAL_ERROR_MSG = "非法的产品编号";

    public static String AUTH_PRODUCT_NOT_OPENED_ERROR_CODE = "308";//产品尚未开启
    public static String AUTH_PRODUCT_NOT_OPENED_ERROR_MSG = "产品状态尚未开启";

    public static String AUTH_PRODUCT_DONT_HAVE_VERSION_RELATION_ERROR_CODE = "309";//产品尚未关联版本
    public static String AUTH_PRODUCT_DONT_HAVE_VERSION_RELATION_ERROR_MSG = "产品尚未关联版本";

    public static String AUTH_STRATEGY_ID_ILLEGAL_ERROR_CODE = "310";//非法的策略编号
    public static String AUTH_STRATEGY_ID_ILLEGAL_ERROR_MSG = "非法的策略编号";

    public static String AUTH_STRATEGY_NOT_DEPLOYED_ERROR_CODE = "311";//策略尚未部署
    public static String AUTH_STRATEGY_NOT_DEPLOYED_ERROR_MSG = "策略尚未部署";

    //=============== 签名验证的错误提示 结束 ==================//


    // ================ 自助查询错误提示 开始============================//

    public static String SELF_HELP_NO_REPORT_CODE = "351"; // 没有报告
    public static String SELF_HELP_NO_REPORT_MSG = "没有发现报告"; // 没有报告

    public static String SELF_HELP_PERSON_JOB_ERROR_CODE = "352"; // 职业查询异常
    public static String SELF_HELP_PERSON_JOB_ERROR_MSG = "职业查询失败"; // 职业查询异常

    public static String SELF_HELP_BUESINESS_LOAN_ERROR_CODE = "353"; // 同业借贷查询异常
    public static String SELF_HELP_BUESINESS_LOAN_ERROR_MSG = "借贷查询失败"; // 借贷查询失败

    public static String SELF_HELP_PERSON_CREDIT_ERROR_CODE = "354"; // 同业借贷查询异常
    public static String SELF_HELP_PERSON_CREDIT_ERROR_MSG = "个人信用查询失败"; // 个人信用查询失败

    public static String SELF_HELP_RISK_ERROR_CODE = "355"; // 同业借贷查询异常
    public static String SELF_HELP_RISK_ERROR_MSG = "风险核验查询失败"; // 个人信用查询失败

    public static String OPERATOR_SESSION_TIMEOUT_ERROR_CODE = "601"; // 运营商会话过期
    public static String OPERATOR_SESSION_TIMEOUT_ERROR_MSG = "运营商会话过期，请重新重走流程"; // 运营商会话过期

    public static String OPERATOR_SESSION_UNKOWN_ERROR_CODE = "602"; // 未知错误
    public static String OPERATOR_SESSION_UNKOWN_ERROR_MSG = "未知异常"; // 运营商会话过期

    public static String OPERATOR_SAME_SESSION_SUBMIT_TOO_MUCH_CODE = "603";
    public static String OPERATOR_SAME_SESSION_SUBMIT_TOO_MUCH_MSG = "同一个会话提交太频繁，请重新走流程";

    public static String OPERATOR_INPUT_DATA_AGE_CODE = "604";
    public static String OPERATOR_INPUT_DATA_AGE_MSG = "请再一次输入";

    public static String OPERATOR_SEND_CAPTCHA_CODE = "605";
    public static String OPERATOR_SEND_CAPTCHA_MSG = "已经发送验证码";

    public static String OPERATOR_CAPTCHA_ERROR_CODE = "606";
    public static String OPERATOR_CAPTCHA_ERROR_MSG = "验证码错误，会重新发送";

    public static String OPERATOR_PASSWORD_ERROR_CODE = "607";
    public static String OPERATOR_PASSWORD_ERROR_MSG = "密码错误请重新输入";

    public static String OPERATOR_CAPTCHA_INVALID_CODE = "608";
    public static String OPERATOR_CAPTCHA_INVALID_MSG = "动态密码失效系统已自动重新下发";

    public static String OPERATOR_NO_FIND_REPORT_CODE = "609";
    public static String OPERATOR_NO_FIND_REPORT_MSG = "没有发现该报告";

    public static String OPERATOR_COLLECTING_REPORT_CODE = "610";
    public static String OPERATOR_COLLECTING_REPORT_MSG = "报告正在生成中，请稍后查询";

    public static String OPERATOR_SUBMIT_ERROR_CODE = "611";
    public static String OPERATOR_SUBMIT_ERROR_MSG = "运营商提交表单失败";

    public static String OPERATOR_COLLECT_FLOW_FAIL_CODE = "612";
    public static String OPERATOR_COLLECT_FLOW_FAIL_MSG = "采集流程失败，请重走流程";

    public static String OPERATOR_COLLECT_FAIL_CODE = "613"; // 运营商会话过期
    public static String OPERATOR_COLLECT_FAIL_MSG = "采集失败"; // 运营商会话过期


    // ================ 自助查询错误提示 结束============================//

    // ================ 公积金错误提示 开始============================//
    public static final String GJJ_IMAGECODE_CODE = "200010";
    public static final String GJJ_IMAGECODE_MSG = "请输入图片验证码";

    public static final String GJJ_NO_TYPE_CODE = "200011";
    public static final String GJJ_NO_TYPE_MSG = "登录类型为空";

    public static final String GJJ_NO_NAME_CODE = "200012";
    public static final String GJJ_NO_NAME_MSG = "姓名为空";

    public static final String GJJ_NO_IDCARD_CODE = "200013";
    public static final String GJJ_NO_DCARD_MSG = "身份证号码为空";

    public static final String GJJ_NO_PHONE_CODE = "200014";
    public static final String GJJ_NO_PHONE_MSG = "手机号码为空";

    public static final String GJJ_NO_JSONSTR_CODE = "200015";
    public static final String GJJ_NO_JSONSTR_MSG = "公积金信息为空";

    public static final String GJJ_NO_DISTRICT_CODE = "200016";
    public static final String GJJ_NO_DISTRICT_MSG = "地区编码为空";

    public static final String GJJ_NO_CITY_CODE = "200017";
    public static final String GJJ_NO_CITY_MSG = "城市名称为空";


    // ================ 公积金错误提示 结束============================//


    // =============== 电商错误提示 开始 =================================//

    public static final String EBUSINESS_IN_COLLECTION_SAME_USER_CODE = "300020";
    public static final String EBUSINESS_IN_COLLECTION_SAME_USER_MSG = "用户正在采集中，请稍后!";

    public static final String EBUSINESS_SESSION_TIME_OUT_CODE = "300021";
    public static final String EBUSINESS_SESSION_TIME_OUT_MSG = "该报告采集已经过期，请重新请求";

    public static final String EBUSINESS_GET_QRCODE_ERROR_CODE = "300022";
    public static final String EBUSINESS_GET_QRCODE_ERROR_MSG = "获得二维码失败，请重新尝试";

    public static final String EBUSINESS_IN_GET_QRCODE_CODE = "300023";
    public static final String EBUSINESS_IN_GET_QRCODE_MSG = "系统正在获取二维码中!";


    // =============== 电商错误提示 结束 =================================//


    // =============== 央行征信提示 开始 =================================//


    /////////////找回用户名////////////
    public static final String BANK_NAME_NULL_CODE = "400010";
    public static final String BANK_NAME_NULL_MSG = "姓名不能为空";

    public static final String BANK_IDCARD_NULL_CODE = "400011";
    public static final String BANK_IDCARD_NULL_MSG = "身份证号不能为空";

    public static final String BANK_CODE_NULL_CODE = "400012";
    public static final String BANK_CODE_NULL_MSG = "验证码不能为空";

    public static final String BANK_TOKEN_TIME_OUT_CODE = "400013";
    public static final String BANK_TOKEN_TIME_OUT_MSG = "央行征信流程会话过期";

    public static final String BANK_RETRIEVE_ERROR_CODE = "400014";
    public static final String BANK_RETRIEVE_ERROR_MSG = "您无法使用该功能找回登录名，可能是因为您的安全等级为低、未注册或已销户，请重新注册";

    ///////////////找回密码////////////////

    public static final String BANK_LOGIN_NAME_NULL_CODE = "400030";
    public static final String BANK_LOGIN_NAME_NULL_MSG = "登录名不能为空";

    public static final String BANK_LOGIN_NAME_NOT_EXIST_CODE = "400031";
    public static final String BANK_LOGIN_NAME_NOT_EXIST_MSG = "登录名不存在";

    public static final String BANK_PWD_NULL_CODE = "400032";
    public static final String BANK_PWD_NULL_MSG = "密码不能为空";

    public static final String BANK_ANSWER_NULL_CODE = "400033";
    public static final String BANK_ANSWER_NULL_MSG = "答案不能为空";

    //////////注册模块/////////////


    public static final String BANK_LOGIN_NAME_EXIST_CODE = "400034";
    public static final String BANK_LOGIN_NAME_EXIST_MSG = "用户名已经存在";

    public static final String BANK_PHONE_NULL_CODE = "400035";
    public static final String BANK_PHONE_NULL_MSG = "手机号不能为空";

    public static final String BANK_CODE_ERROR_CODE = "400036";
    public static final String BANK_CODE_ERROR_MSG = "验证码输入错误";

    public static final String BANK_NOT_INFO_ERROR_CODE = "400037";
    public static final String BANK_NOT_INFO_ERROR_MSG = "目前系统尚未收录您的个人信息，无法进行注册";

    public static final String BANK_OTHER_ERROR_CODE = "400038";
    public static final String BANK_OTHER_ERROR_MSG = "您已使用其他登录名注册系统并通过验证，请使用通过验证的登录名。如果忘记，请使用找回登录名功能找回";

    public static final String BANK_CODE_EXPIRED_CODE = "400039";
    public static final String BANK_CODE_EXPIRED_MSG = "动态码输入错误或已过期，请重新输入";

    public static final String BANK_PHONE_EXIST_CODE = "400040";
    public static final String BANK_PHONE_EXIST_MSG = "此手机号码已注册";

    public static final String BANK_REGIST_FAIL_CODE = "400041";
    public static final String BANK_REGIST_FAIL_MSG = "注册流程失败";

    public static final String BANK_GET_CODE_FAIL_CODE = "400042";
    public static final String BANK_GET_CODE_FAIL_MSG = "获取验证码异常";

    //////////////登录模块///////////////////
    public static final String BANK_LOGIN_OR_PWD_ERROR_CODE = "400043";
    public static final String BANK_LOGIN_OR_PWD_ERROR_MSG = "登录名或密码错误";

    public static final String BANK_QUICK_LOGIN_NOT_OPEN_CODE = "400044";
    public static final String BANK_QUICK_LOGIN_NOT_OPEN_MSG = "快捷查询未开通";

    public static final String BANK_ID_CODE_NOT_PASS_CODE = "400045";
    public static final String BANK_ID_CODE_NOT_PASS_MSG = "身份码验证未通过";


    // =============== 央行征信提示 结束 =================================//


}
