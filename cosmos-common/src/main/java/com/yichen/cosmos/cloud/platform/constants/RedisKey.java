package com.yichen.cosmos.cloud.platform.constants;

public class RedisKey {

    public static final String USER_TOKEN_GROUP = "wisdom_user:token_";
    //鲸数智慧后台
    public static final String ADMIN_USER_TOKEN_GROUP = "wisdom_admin_user:token_";

    public static final String USER_TOKEN_SERIALNO_GROUP = "wisdom_user:serialno_";
    public static final String USER_TOKEN_CONNECT = "_@_";
    //验证码redis缓存前缀。k-v方式设置过期时间
    public static final String VALIDATE_CODE_PREFIX = "cache:validate_code_prefix_";

    //通过邮箱找回密码
    public static final String EMAIL_PREFIX = "wisdom_email_";

    //5分钟超时
    public static final Integer DYNAMIC_CODE_TIMEOUT = 5;

    //动态验证码
    public static final String DYNAMIC_CODE_PREFIX_KEY = "wisdom:dynamic_code_prefix_key_";

    public static final String DYNAMIC_CODE_SPLIT = "_";
    //短信次数限制,一天最多5次
    public static final String DYNAMIC_CODE_LIMIT_TIMES = "wisdom_dynamic_code:limit_times_";

    //指定工程的版本号(唯一)的锁定状态
    public static final String PROJECT_VERSION_LOCK_STATE = "wisdom_version:lock_state_";
    //指定工程的版本号(唯一)的锁定状态,用户userId
    public static final String PROJECT_VERSION_LOCK_USER = "wisdom_version:lock_userId_";

    //用户信息
    public static final String USER_REDIS_INFO = "wisdom_user_info";

    //后台用户信息
    public static final String ADMIN_USER_REDIS_INFO = "wisdom_admin_user_info";


    //用户所属机构信息
    public static final String ORG_REDIS_INFO = "wisdom_org_info";


    //公司性质
    public static final String COMPANY_MODEL_KEY = "company_model";
    //星座
    public static final String CONSTELLATION_KEY = "constellation";
    //985学校
    public static final String SCHOOL_985_KEY = "school985";
    //211学校名单
    public static final String SCHOOL_211_KEY = "school211";
    //学历
    public static final String EDUCATION_KEY = "education";
    //工作职位
    public static final String JOB_POSITION_KEY = "jobPosition";
    //婚姻状况
    public static final String MARRIAGE_KEY = "marriage";
    //民族字典
    public static final String NATIONALITY_KEY = "nationality";
    //社会关系
    public static final String SOCIAL_RELATION_KEY = "socialRelation";

    //每次执行时，生成一个流程uuid，用来识别流程中的数据源是否已经获取过.
    //规避重复进行报告获取、因子清洗
    public static final String flownode_datasource_key = "flownode_datasource";

}
