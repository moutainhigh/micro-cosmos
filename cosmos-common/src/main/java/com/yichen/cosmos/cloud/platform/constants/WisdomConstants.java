package com.yichen.cosmos.cloud.platform.constants;

/**
 * Created by thomas on 2017/3/9.
 */
public class WisdomConstants {

    //自定义表单2000;  1-5 系统自带
    public static final int WISDOM_CUSTOME_FORM_TYPE = 2000;

    //对象包路径默认统一
    public static final String PACKAGE_PATH = "com.tairan.cloud.whale.wisdom.bean";

    /**
     * 系统项目自带的表单版本默认设置为1.0
     * 项目初始创建时使用到
     */
    public static final String default_project_version_id = "v1.0";


    /*****
     * 构建规则引擎
     ********/
    public static final String knowledge_builder_key = "knowledge_builder_key";
    /*****
     * 构建规则引擎
     ********/
    public static final String knowledge_base_key = "knowledge_base_key";

    public static final String version_id = "version_";

    //默认页面大小
    public final static String rows = "20";
    public final static String page = "1";


    //防止因子重复清洗，记录已经清洗过的 数据源code：datasource Code
    //wisdom_datasouce:dsource_code  及ChannelType
    public static final String datasource_code = "datasource_code";

    //每个流程每次执行的任务ID名称
    public static final String executeRuleTaskDataId = "executeRuleTaskDataId";
    //命中拒绝策略线条件ID
    public static final String hitRejectStrategy = "hitRejectStrategy";
    //规则执行条数
    public static final String executeRuleTaskCount = "executeRuleTaskCount";

}
