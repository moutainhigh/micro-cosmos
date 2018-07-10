package com.xunyi.cloud.wisdom.activiti.service.activitidrools;

import com.alibaba.fastjson.JSON;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.conf.MaxThreadsOption;
import org.drools.impl.adapters.KnowledgeBaseConfigurationAdapter;
import org.drools.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:thomas
 * @Date: 2018/7/9 17:04
 * @Description:
 */
public class RulesUtils {

    private static final Logger logger = LoggerFactory.getLogger(RulesUtils.class);


    public static List<String> getRuleNames(){
        List<String> ruleNames = new ArrayList<>();

        ruleNames.add("bpm-flowgroup_1");
        ruleNames.add("bpm-flowgroup_2");

        return ruleNames;
    }
    public static List<Map<String, String>> ruleContextList(){
        List<Map<String, String>> ruleContextList = new ArrayList<>();
        Map<String,String> rule1Map = new HashMap<>();
        Map<String,String> rule2Map = new HashMap<>();

        String rule1 =  "package bpm;\n" +
                "import java.util.Map;\n" +
                "rule \"bpm-flowgroup_1\"\n" +
                "no-loop true \n" +
                "lock-on-active true\n" +
                "salience 60\n" +
//                "ruleflow-group \"test1\" \n" +
//                "activation-group \"activation-group1\"\n" +
                "    when\n" +
                "        eval(true)\n" +
                "        map:Map()\n" +
                "    then\n" +
                "        System.out.println(\"flowgroup_2\");\n" +
                "        System.out.println(drools.getRule());\n" +
                "        System.out.println(\"------------222-------------------\");\n" +
                "      \tSystem.out.println(map);\n" +
                "        System.out.println(\"-------------222------------------\");\n" +
                "        map.put(\"age\",\"20\");\n" +
                "        update(map);\n" +
                "        \n" +
                "end";

        rule1Map.put("drlContext",rule1);
        String rule2 = "package bpm;\n" +
                "import java.util.Map;\n" +
                "rule \"bpm-flowgroup_2\"\n" +
                "no-loop true \n" +
                "lock-on-active true\n" +
                "salience 59\n" +
//                "ruleflow-group \"test1\" \n" +
//                "activation-group \"activation-group1\"\n" +
                "    when\n" +
                "        eval(true)\n" +
                "        map:Map()\n" +
                "    then\n" +
                "        System.out.println(\"flowgroup_2\");\n" +
                "        System.out.println(drools.getRule());\n" +
                "        System.out.println(\"------------222-------------------\");\n" +
                "      \tSystem.out.println(map);\n" +
                "        System.out.println(\"-------------222------------------\");\n" +
                "        map.put(\"age\",\"20\");\n" +
                "        update(map);\n" +
                "        \n" +
                "end";
        rule2Map.put("drlContext",rule2);


        ruleContextList.add(rule1Map);
        ruleContextList.add(rule2Map);
        return ruleContextList;
    }



    public static KnowledgeBase buildComplexFlowProcess(List<Map<String, String>> ruleContextList) {
        try{
            logger.info("===加载流程规则。ruleContextList={}", JSON.toJSONString(ruleContextList));
            //2.生成规则KnowledgeBuilder、KnowledgeBase
            //加载规则，构建知识库
            KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
            //构建知识库配置信息
            org.drools.impl.adapters.KnowledgeBaseConfigurationAdapter config = (KnowledgeBaseConfigurationAdapter) KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
            config.setOption(MaxThreadsOption.get(32));//设置执行线程数目

            KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(config);

            //3. 根据已启用的版本号id查询规则内容
//            List<Map<String, String>> ruleContextList = buildFlowProcessDao.getRuleContextListByVersionId(versionId);
            //获取规则内容


            CompositeKnowledgeBuilder ckb = knowledgeBuilder.batch().type(ResourceType.DRL);
            if (!CollectionUtils.isEmpty(ruleContextList)) {
                for (Map<String, String> ruleContextMap : ruleContextList) {
                    String drlContext = ruleContextMap.get("drlContext");
                    System.out.println(drlContext);
                    ckb.add(ResourceFactory.newByteArrayResource(drlContext.getBytes("utf-8")));
                }
            }
            //批量构建、编译
            ckb.build();
            //检查规则编译是否有误
            boolean hasErrors = knowledgeBuilder.hasErrors();
            logger.info("*****,检查规则文件编译是否有错:hasErrors={}", hasErrors);
            if (hasErrors) {
                StringBuilder errorMsgBuilder = new StringBuilder();
                errorMsgBuilder.append(knowledgeBuilder.getResults());
                logger.info("*****Invalid knowledge base!!系统规则加载错误 ，原因 reason={}", knowledgeBuilder.getResults());
                //如果有错，批量移除
                KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
                for (KnowledgeBuilderError error : errors) {
                    logger.error("***************error.message={}", error.getMessage());
                    errorMsgBuilder.append(error.getMessage());
                }
                //先打印错误信息，再进行移除；否则，先移除的话，错误信息也会一并消失
                knowledgeBuilder.undo();
                //系统生成规则，加载规则引擎有误，拒绝开启版本状态
                logger.error("。失败原因：" + errorMsgBuilder.toString());
            }

            knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
//            KnowledgeBaseImpl intKbase = (KnowledgeBaseImpl) knowledgeBase;
            //[权宜之计]方便构建流程中使用，动态加载规则
//            KnowledgeStore.addKnowledgeBase(versionId, intKbase);

            //有流程，需引入JBPM
            //构建规则流程
//            RuleFlowProcess ruleFlowProcess = this.processDefinition(versionId);
//            RuleFlowProcess ruleFlowProcess = null;
//            if (ruleFlowProcess != null) {
//                logger.debug("将规则流程[ruleFlowProcess]添加到[intKbase]");
            //model 1: split不起效果[权宜之计：将split条件以规则形式加载到知识仓库中]
//                intKbase.addProcess(ruleFlowProcess);
            //model 2:ActionNode 判断逻辑需要写在consequence 中，否则NullPointerException
//                XmlBPMNProcessDumper dumper = XmlBPMNProcessDumper.INSTANCE;
//                String xml = dumper.dump(ruleFlowProcess);
//                knowledgeBuilder.add(ResourceFactory.newByteArrayResource(xml.getBytes()), ResourceType.BPMN2);
//            }

            //系统生成规则，加载规则引擎成功，开启版本状态
            logger.info("knowledgeBase....... success");

            return knowledgeBase;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("规则系统生成有误，加载失败，版本发布失败。",e);
        }
        logger.info("knowledgeBase....... failed");
        return null;
    }
}
