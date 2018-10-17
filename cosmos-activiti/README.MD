
#worklog



#2018.03.14 周三
> 1. 多实例 TODO

> 2. 子流程---> 调用活动（call activiti）

> 3. activiti:initiator

    activiti学习资料--initiator的作用及其使用
    http://blog.csdn.net/howareyoutodaysoft/article/details/8081577

    构建流程时，各个组件的ID必须指定




----------------------------------------------------------------------------------------

#2018.03.13  周二
> 1. 分配用户任务的三种方式

    http://blog.csdn.net/zjx86320/article/details/50388157
    assigne 分配
    claim   认领

> 2. 用户和用户组的关系

    http://blog.csdn.net/cq1982/article/details/45560897

> 3. 任务分配，动态表单设定任务的接收人及接受组

    利用spring service 的bean动态设定人或组
    http://blog.csdn.net/ak57193856/article/details/78053417

> 4.事件监听器  EventListener/ TenantId  /线条件

    4.1 TenantId: 涉及到了工作流多租户的问题
    http://blog.csdn.net/simplemurrina/article/details/74993068

    TenantId 本身并无太多含义，只是作为一个标记
    考虑问题：多引擎多租户解决方案


     4.2

    监听器：执行监听器（ExecutionListener）、任务监听器（TaskListener）、事件监听器（ActivitiEventListener）
    http://blog.csdn.net/as154930786/article/details/64919563

    事件列表：
    http://blog.csdn.net/u010009709/article/details/74177731

    全局事件监听器配置>> riskcontrol-activiti.xml, 属性：eventListeners

    4.3 线条件

    TODO



> 5. 概念：信号  、边界 、 多实例类型 、 native/ Command

    TODO

    native：本地化查询，直接面向数据库
    http://blog.csdn.net/cs1075341779/article/details/52817883


    Activiti6特性
    http://www.shareniu.com/article/30.htm


    Bpmn和xml文件相互转化
    http://blog.csdn.net/as154930786/article/details/64906110



> 6. 分歧节点  exclusiveGateway 当所有的条件都不满足时，走默认线

    【但】默认线无需设置条件

    异常描述：
    Tue Mar 13 14:32:01 CST 2018
    There was an unexpected error (type=Internal Server Error, status=500).
    Errors while parsing: [Validation set: 'activiti-executable-process'
    | Problem: 'activiti-exclusive-gateway-condition-on-seq-flow'] :
    Default sequenceflow has a condition, which is not allowed -
    [Extra info : processDefinitionId = splitfow |
    processDefinitionName = 分歧条件测试 | | id = exclusivegateway1 |
    | activityName = Exclusive Gateway | ] ( line: 5, column: 92)


> 7. activiti bpmnModel使用 及理解

    http://blog.csdn.net/qq_30739519/article/details/51271580

    Activiti 由bpmn文件到BpmnModel实体类转换
    https://m.aliyun.com/yunqi/articles/54820
    博客文章学习
    http://blog.csdn.net/jiankunking/article/category/6167713
    【TODO】



---------------------------------------------------------------------------------------------------------
# 2018.03.12  周一
> 1. 定时器循环执行

    知识点：
    1.1 定时器时间格式：ISO8601
    http://blog.csdn.net/hyperhawk/article/details/6694211
    1.2 groovy 脚本
    https://www.w3cschool.cn/groovy/groovy_basic_syntax.html


> 2.一个客户回访计划的提醒功能也加到工作流当中,Activiti定时任务示例

    http://lucky16.iteye.com/blog/1775660

> 3.引入动态生成工作流Test
    com.tairan.activiti.demo.dynamic.DynamicActivitiProcessTest


> 4. UUID 生成策略

    http://www.kafeitu.me/activiti/2013/08/30/using-uuid-as-primary-key-in-activiti.html

--------------------------------------------------------------------------------------------------------------
# 20180626
> 1. pom.xml 文件添加依赖，将版本 <activiti-version>5.18.0 注释；需要依赖 <activiti-version>5.18.0 的，缺少方法，属性已经注释

    <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-spring-boot-starter-basic</artifactId>
            <version>6.0.0</version>
    </dependency>

    被注释的地方有：
        riskcontrol-activiti.xml 中 属性 jobExecutorActivate
        -------------
        GlobalEventListener
        LeaveSayHelloDelegate
        LeaveSayHelloGlobalListener

        -- 涉及抛出异常的方法等

> 2. 添加包 com.xunyi.cloud.wisdom.activiti.zero

    2.1 动态加载 rule 文件  BuilderComplexFlowProcessService

> 3. <druid.version>1.0.15   与 activiti 6.0.0 不匹配，会报出下列异常

    org.activiti.engine.ActivitiException: couldn't create db schema: alter table ACT_RE_PROCDEF
    add constraint ACT_UNIQ_PROCDEF
    unique (KEY_,VERSION_, TENANT_ID_)

    解决方案：
    将druid 升级到最新版：1.1.10  1.0.23 or later

> 4. 思考JBPM的持久化： jBPM的允许您插入不同的持久性策略。默认情况下，如果你不配置的流程引擎，否则，流程实例没有持久性。

    https://blog.csdn.net/muxiaoshan/article/details/7223091
    JBPM   Hibernate ； Activit Mybatis


> 5. 采用activiti 5.18.0
确保表生成，所使用的activiti版本一致

> 6. 资料

    https://blog.csdn.net/songzheng_741/article/details/17199339

    构建流程时，各个组件的ID必须指定


# 20180705 新手入门
> 1.

    表数问题
    5.16.x版本  23张表
    5.18.x版本  24张表
    5.19.x版本  25张表


> 2.版本兼容问题

    mybatis 与activiti整合版本问题
    http://blog.csdn.net/stone_zhy/article/details/52350435

> 3.
    TestController 部署bpmn文件，查询不到 process definition
    act_re_procdef 表为空;但junit test case 可以。
    原因 文件读取方法问题，参照官网。

    3.1   文件没读取到： 系统编译时，文件不存在，文件没有编译到
    3.2   文件读取方法有误，采取官网方式(https://www.activiti.org/quick-start)


> 4. 乱码问题，需要检查bpmn文件，存在乱码

    Preparing: insert into ACT_RE_PROCDEF(ID_, REV_, CATEGORY_, NAME_, KEY_, VERSION_, DEPLOYMENT_ID_, RESOURCE_NAME_, DGRM_RESOURCE_NAME_, DESCRIPTION_, HAS_START_FORM_KEY_, HAS_GRAPHICAL_NOTATION_ , SUSPENSION_STATE_, TENANT_ID_) values (?, 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    2018-02-28 15:29:00 [main] DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug 132 - ==> Parameters: auto_flow:2:2504(String), http://www.activiti.org/test(String), 閫氱敤鑷姩瀹℃牳娴佺▼(String), auto_flow(String), 2(Integer), 2501(String), diagram/common_auto_flow2.bpmn(String), diagram/common_auto_flow2.auto_flow.png(String), null, false(Boolean), true(Boolean), 1(Integer), (String)

> 5.同一个流程多次部署时，需要将之前的删除（待思考）

    select distinct RES.* from ACT_RU_TASK RES inner join ACT_RE_PROCDEF D on RES.PROC_DEF_ID_ = D.ID_ WHERE D.KEY_ = ? order by RES.ID_ asc LIMIT ? OFFSET ?
    2018-02-28 16:00:26 [http-nio-33002-exec-4] DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug 132 - ==> Parameters: auto_flow(String), 2147483647(Integer), 0(Integer)
    2018-02-28 16:00:26 [http-nio-33002-exec-4] DEBUG org.apache.ibatis.logging.jdbc.BaseJdbcLogger.debug 132 - <==      Total: 2
    2018-02-28 16:00:26 [http-nio-33002-exec-4] DEBUG o.activiti.engine.impl.interceptor.CommandContext.close 170 - Error while closing command context
    org.activiti.engine.ActivitiException: Query return 2 results instead of max 1



> 5.19.0 节点缓存


# 20180706

> 1. 创建流程时，不能是汉字，否则出现异常
    XML类似的解析时，会遇到'XXX' 不是 'NCName' 的有效值的问题

    参考地址： https://www.cnblogs.com/kzfy/p/5088301.html

    localhost:33002/xunyi-activiti/demo/createDeployment
    //processDefinitionKey是流程XML 文件中的ID
    //processDefinitionId 是数据库表中 act_re_procdef 对应的记录ID
    表：act_ru_task 正在执行的任务

    act_re_deployment  仓库 部署
    act_re_procdef     流程定义
    act_ge_bytearray   流程文件xml

> 2.
    [Validation set: 'activiti-executable-process' | Problem: 'activiti-servicetask-missing-implementation'] : One of the attributes 'class', 'delegateExpression', 'type', 'operation', or 'expression' is mandatory on serviceTask. - [Extra info : processDefinitionId = my-process | id = serviceTask_58371d16-98dd-4b83-a700-9aac919dafb7 | ] ( line: 10, column: 5)

> 3. busnessRuleTask 规则节点必须绑定规则，且对应部署定义必须绑定规则

    {
      "timestamp": 1531126518882,
      "status": 500,
      "error": "Internal Server Error",
      "exception": "org.activiti.engine.ActivitiException",
      "message": "deployment 68acd2161d1b41debcebf82fe1703e5e doesn't contain any rules",
      "path": "/xunyi-activiti/demo/completeTask"
    }


> 4. 流程部署空指针问题

    发生问题的代码:
    // 2. Generate graphical information
        new BpmnAutoLayout(model).execute();

    异常：
    java.lang.NullPointerException
        at org.activiti.bpmn.BpmnAutoLayout.generateSequenceFlowDiagramInterchangeElements(BpmnAutoLayout.java:299)
        at org.activiti.bpmn.BpmnAutoLayout.generateDiagramInterchangeElements(BpmnAutoLayout.java:276)
        at org.activiti.bpmn.BpmnAutoLayout.layout(BpmnAutoLayout.java:143)
        at org.activiti.bpmn.BpmnAutoLayout.execute(BpmnAutoLayout.java:88)

    原因：

    节点没有添加到流程process中；
    或者节点ID和流程线的开始，终点的节点ID不同
    导致流程构建时，线指向的ID找不到正确的节点


> 5 问题

    org.activiti.bpmn.exceptions.XMLException: cvc-datatype-valid.1.2.1: '1' 不是 'NCName' 的有效值。
    	at org.activiti.bpmn.converter.BpmnXMLConverter.convertToBpmnModel(BpmnXMLConverter.java:280)
    	at org.activiti.engine.impl.bpmn.parser.BpmnParse.execute(BpmnParse.java:187)
    	at org.activiti.engine.impl.bpmn.deployer.BpmnDeployer.deploy(BpmnDeployer.java:113)
    	at org.activiti.engine.impl.persistence.deploy.DeploymentManager.deploy(DeploymentManager.java:57)
    	at org.activiti.engine.impl.cmd.DeployCmd.execute(DeployCmd.java:103)

    	问题:activiti部署的时候出现  org.activiti.bpmn.exceptions.XMLException: cvc-datatype-valid.1.2.1: '' 不是 'NCName'

        原因:流程id不能以数字开头

        解决:id重新设置,不能以数字开头

        举例:错误的id 1411bbb



> 6. 线条件，涉及函数调用的问题

    conditionExpression:${isNaN(sex) && (sex=='无报告' || sex == '无数据')}

    org.activiti.engine.impl.javax.el.ELException: Expression uses functions, but no function mapper was provided
        at org.activiti.engine.impl.juel.Tree.bind(Tree.java:109)
        at org.activiti.engine.impl.juel.TreeValueExpression.<init>(TreeValueExpression.java:65)
        at org.activiti.engine.impl.juel.ExpressionFactoryImpl.createValueExpression(ExpressionFactoryImpl.java:428)
        at org.activiti.engine.impl.juel.ExpressionFactoryImpl.createValueExpression(ExpressionFactoryImpl.java:73)
        at org.activiti.engine.impl.el.ExpressionManager.createExpression(ExpressionManager.java:80)
        at org.activiti.engine.impl.bpmn.parser.handler.SequenceFlowParseHandler.executeParse(SequenceFlowParseHandler.java:61)
        at org.activiti.engine.impl.bpmn.parser.handler.SequenceFlowParseHandler.executeParse(SequenceFlowParseHandler.java:30)
        at org.activiti.engine.impl.bpmn.parser.handler.AbstractBpmnParseHandler.parse(AbstractBpmnParseHandler.java:81)

> 7.  通过 rulesOutput  获取最终数据


