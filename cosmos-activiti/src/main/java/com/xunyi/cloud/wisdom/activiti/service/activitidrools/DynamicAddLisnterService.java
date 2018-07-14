package com.xunyi.cloud.wisdom.activiti.service.activitidrools;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xunyi.cloud.wisdom.activiti.service.BaseService;
import com.xunyi.cloud.wisdom.activiti.util.ActivitiUtils;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.repository.Deployment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:thomas
 * @Date: 2018/7/9 11:39
 * @Description: 动态添加监听器的测试
 */
@Service
public class DynamicAddLisnterService extends BaseService {

    private static final String deploy_name_prefix = "deploy_name_";
    private static final String proc_def_name_prefix = "proc_def_name_";
    private static final String proc_def_id_prefix = "proc_def_id_";
    private static final String bpmn_model_name_prefix = "bpmn_model_name_";

    //第一步
    //部署文件
    //生成流程定义
    //deployId 全局唯一,对应strategyname 全局唯一
    //tenantId 注意使用，区分不同的租户   TODO
    //通过UserTask 实现流程暂停
    //通过task isSuspended  实现暂停
    // 通过暂停流程实例ProcessInstance，实现Task 的暂停
    public void createDeployment(String strategyname){
        //1. 对旧的部署文件已经删除
        //分布式锁

        List<Deployment> deployments = repositoryService.createDeploymentQuery().deploymentName(deploy_name_prefix+strategyname).list();
        if(CollectionUtils.isNotEmpty(deployments)){
            logger.info("deployname={},部署文件已经存在，需删除，重新生成",deploy_name_prefix+strategyname);

            //注意删除失败的情况
            //存在进行中的任务或历史用例及任务
            //可以先暂停该流程图，在此基础上创建新的流程
            try{
                //删除前需要先挂起，不允许发起这个流程定义的流程实例
//                repositoryService.suspendProcessDefinitionById(deployment.getId());
                //挂起流程定义及级联挂起流程下面的实例
//                repositoryService.suspendProcessDefinitionById(deployment.getId(),true,null);

//                repositoryService.deleteDeployment(deployment.getId());
                //级联删除流程
                for(Deployment deployment: deployments){
                    repositoryService.deleteDeployment(deployment.getId(),true);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            logger.info("流程文件不存在。名称：{}",deploy_name_prefix+strategyname);
        }

        logger.warn("准备流程的重新部署.................");

        // 1. Build up the model from scratch
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        model.addProcess(process);
        process.setId(proc_def_id_prefix+strategyname);
        process.setName(proc_def_name_prefix+strategyname);

        process.addFlowElement(ActivitiUtils.createStartEvent());
        process.addFlowElement(ActivitiUtils.createUserTask("userTask1_id","用户节点","none"));

        List<String> ruleNames = new ArrayList<>();
        ruleNames.add("rule1");

        //process.addFlowElement(ActivitiUtils.businessRuleTask("bis_id","规则节点",RulesUtils.getRuleNames()));

        process.addFlowElement(ActivitiUtils.createEndEvent());

        process.addFlowElement(ActivitiUtils.createSequenceFlow("start", "userTask1_id",null));
//        process.addFlowElement(ActivitiUtils.createSequenceFlow("userTask1_id", "bis_id"));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("userTask1_id", "end",null));

        // 2. Generate graphical information
        new BpmnAutoLayout(model).execute();

        Deployment deployment = repositoryService.createDeployment()
                .addBpmnModel(bpmn_model_name_prefix+ strategyname + ".bpmn", model).name(deploy_name_prefix+strategyname).deploy();
        logger.warn("流程的重新部署................[完成].");



        //**************************  [测试结果：这样加载规则，规则不会执行]
//        KnowledgeBase knowledgeBase = RulesUtils.buildComplexFlowProcess(RulesUtils.ruleContextList());

        //参考RulesDeployer 提取
//            DeploymentManager deploymentManager = Context
//                    .getProcessEngineConfiguration()
//                    .getDeploymentManager();

//        DeploymentManager deploymentManager = processEngineConfiguration.getDeploymentManager();


        //关联知识仓库及部署
        //缓存是单机的，不是分布式的，需要自行实现
        //或者与流程关联，保存到数据库
//            https://blog.csdn.net/silent_zqy/article/details/70148298
//        deploymentManager.getKnowledgeBaseCache().add(deployment.getId(), knowledgeBase);
        logger.warn("流程的<规则>部署................[完成].");
        //**************************  [测试结果：这样加载规则，规则不会执行]
    }

}
