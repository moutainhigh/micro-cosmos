package com.xunyi.cloud.wisdom.activiti.zero;

import org.drools.core.impl.KnowledgeBaseImpl;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.*;
import org.kie.internal.conf.MaxThreadsOption;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by thomas.su on 2017/10/16 17:07.
 */
@Service("builderComplexFlowProcessService")
public class BuilderComplexFlowProcessService{
    private final static Logger logger = LoggerFactory.getLogger(BuilderComplexFlowProcessService.class);

    public String buildComplexFlowProcess(String versionId,boolean ischecked) {
        try{
            logger.info("===加载流程。versionId={}",versionId);
            //2.生成规则KnowledgeBuilder、KnowledgeBase
            //加载规则，构建知识库
            KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
            //构建知识库配置信息
            KieBaseConfiguration config = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
            config.setOption(MaxThreadsOption.get(32));//设置执行线程数目

            KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(config);

            //3. 根据已启用的版本号id查询规则内容
//            List<Map<String, String>> ruleContextList = buildFlowProcessDao.getRuleContextListByVersionId(versionId);
            //获取规则内容
            List<Map<String, String>> ruleContextList = new ArrayList<>();
                    CompositeKnowledgeBuilder ckb = knowledgeBuilder.batch().type(ResourceType.DRL);
            if (!CollectionUtils.isEmpty(ruleContextList)) {
                for (Map<String, String> ruleContextMap : ruleContextList) {
                    String drlContext = ruleContextMap.get("drlContext");
                    ckb.add(ResourceFactory.newByteArrayResource(drlContext.getBytes("utf-8")));
                }
            }
            //批量构建、编译
            ckb.build();
            //检查规则编译是否有误
            boolean hasErrors = knowledgeBuilder.hasErrors();
            logger.info("*****versionId={},检查规则文件编译是否有错:hasErrors={}", versionId, hasErrors);
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
            KnowledgeBaseImpl intKbase = (KnowledgeBaseImpl) knowledgeBase;
            //[权宜之计]方便构建流程中使用，动态加载规则
//            KnowledgeStore.addKnowledgeBase(versionId, intKbase);

            //有流程，需引入JBPM
            //构建规则流程
//            RuleFlowProcess ruleFlowProcess = this.processDefinition(versionId);
            RuleFlowProcess ruleFlowProcess = null;
            if (ruleFlowProcess != null) {
                logger.debug("将规则流程[ruleFlowProcess]添加到[intKbase]");
                //model 1: split不起效果[权宜之计：将split条件以规则形式加载到知识仓库中]
                intKbase.addProcess(ruleFlowProcess);
                //model 2:ActionNode 判断逻辑需要写在consequence 中，否则NullPointerException
//                XmlBPMNProcessDumper dumper = XmlBPMNProcessDumper.INSTANCE;
//                String xml = dumper.dump(ruleFlowProcess);
//                knowledgeBuilder.add(ResourceFactory.newByteArrayResource(xml.getBytes()), ResourceType.BPMN2);
            }

            //系统生成规则，加载规则引擎成功，开启版本状态
            logger.info("success");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("规则系统生成有误，加载失败，版本发布失败。versionId={}", versionId);
        }

        return null;
    }
}
