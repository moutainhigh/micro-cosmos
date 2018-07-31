package com.xunyi.cloud.wisdom.activiti.service.activitidrools;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xunyi.cloud.wisdom.activiti.enums.NodeTypeEnum;
import com.xunyi.cloud.wisdom.activiti.service.BaseService;
import com.xunyi.cloud.wisdom.activiti.util.ActivitiUtils;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
    1.测试 排他网关  两条分支 ，分别流向不同的 终点
    2.测试排他网关  两条分支 ，流向同一个的 终点


 */
@Service
public class TestSplitNodeAndEndNodeService extends BaseService {

    private static final String deploy_name_prefix = "deploy_name_";
    private static final String proc_def_name_prefix = "proc_def_name_";
    private static final String proc_def_id_prefix = "proc_def_id_";
    private static final String bpmn_model_name_prefix = "bpmn_model_name_";


    /**
     *
     *
     * @param strategyname
     */
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

        logger.warn("准备流程的部署.................");

        // 1. Build up the model from scratch
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        model.addProcess(process);
        process.setId(proc_def_id_prefix+strategyname);
        process.setName(proc_def_name_prefix+strategyname);

        process.addFlowElement(ActivitiUtils.createStartEvent());
        process.addFlowElement(ActivitiUtils.createScriptTask("1"," 1th script task"));
        process.addFlowElement(ActivitiUtils.createExclusiveGateway("g1"," g1 gatewayTask"));
        process.addFlowElement(ActivitiUtils.createScriptTask("2"," 2nd script task"));
        process.addFlowElement(ActivitiUtils.createScriptTask("2"," 2nd script task"));
        process.addFlowElement(ActivitiUtils.createScriptTask("3"," 3rd script task"));
        process.addFlowElement(ActivitiUtils.createScriptTask("4"," 4th script task"));

        process.addFlowElement(ActivitiUtils.createEndEvent());

        process.addFlowElement(ActivitiUtils.createSequenceFlow(NodeTypeEnum.START.name(), "uid",null));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("uid", "bis_id",null));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("bis_id", "uid2",null));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("uid2", "bis_id2",null));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("bis_id2", NodeTypeEnum.END.name(),null));

        // 2. Generate graphical information
        new BpmnAutoLayout(model).execute();

        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();

        //后缀名 .bpmn 由 BpmnDeployer 加载解析
        deploymentBuilder
                        .addBpmnModel(bpmn_model_name_prefix+ strategyname + ".bpmn", model)
                        .name(deploy_name_prefix+strategyname)
                        .deploy();

//      拓展点：
//        RulesDeployer
//        BusinessRuleTaskActivityBehavior
        logger.warn("流程的重新部署................[完成].");



    }
}
