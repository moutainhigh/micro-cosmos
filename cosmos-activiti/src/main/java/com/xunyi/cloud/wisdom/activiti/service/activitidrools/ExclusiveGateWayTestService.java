package com.xunyi.cloud.wisdom.activiti.service.activitidrools;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.xunyi.cloud.wisdom.activiti.enums.NodeTypeEnum;
import com.xunyi.cloud.wisdom.activiti.service.BaseService;
import com.xunyi.cloud.wisdom.activiti.util.ActivitiUtils;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.drools.core.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:thomas su
 * @Date: 2018/8/15 14:00
 * @Description:
 */
@Service
public class ExclusiveGateWayTestService extends BaseService {

    private static final String deploy_name_prefix = "deploy_name_";
    private static final String proc_def_name_prefix = "proc_def_name_";
    private static final String proc_def_id_prefix = "proc_def_id_";
    private static final String bpmn_model_name_prefix = "bpmn_model_name_";


    public void createDeployment(String strategyname){
        //1. 对旧的部署文件已经删除
        //分布式锁

        List<Deployment> deployments = repositoryService.createDeploymentQuery().deploymentName(deploy_name_prefix+strategyname).list();
        if(CollectionUtils.isNotEmpty(deployments)){
            logger.info("deployname={},部署文件已经存在，需删除，重新生成。deployments-size:{}",deploy_name_prefix+strategyname,deployments.size());
            try{
                for(Deployment deployment: deployments){
                    repositoryService.deleteDeployment(deployment.getId(),true);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            logger.info("流程文件不存在。名称：{}",deploy_name_prefix+strategyname);
        }

        // 1. Build up the model from scratch
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        model.addProcess(process);
        process.setId(proc_def_id_prefix+strategyname);
        process.setName(proc_def_name_prefix+strategyname);

        process.addFlowElement(ActivitiUtils.createStartEvent());
        process.addFlowElement(ActivitiUtils.createUserTask("ut1","ut1","ts"));
        process.addFlowElement(ActivitiUtils.createUserTask("ut2","ut2","ts"));
        process.addFlowElement(ActivitiUtils.createUserTask("ut3","ut3","ts"));
        process.addFlowElement(ActivitiUtils.createUserTask("ut4","ut4","ts"));
        process.addFlowElement(ActivitiUtils.createUserTask("ut5","ut5","ts"));
        process.addFlowElement(ActivitiUtils.createExclusiveGateway("g1","g1","default"));//设置排他网关及默认线
        process.addFlowElement(ActivitiUtils.createEndEvent());

        process.addFlowElement(ActivitiUtils.createSequenceFlow(NodeTypeEnum.START.name(), "ut1",null));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("ut1", "g1",null));


        process.addFlowElement(ActivitiUtils.createSequenceFlow("l1","g1", "ut2","${ !regularService.isNumber(sex) && (sex=='无报告' || sex == '无数据')}"));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("l2","g1", "ut3","${regularService.isNumber(sex) && sex == 1}"));// ${sex == 'male'}
        process.addFlowElement(ActivitiUtils.createSequenceFlow("l3","g1", "ut4","${regularService.isNumber(sex) && sex == 2}"));// ${sex == 'female'}

        process.addFlowElement(ActivitiUtils.createSequenceFlow("default","g1", "ut5",null));

        process.addFlowElement(ActivitiUtils.createSequenceFlow("l4","ut2", NodeTypeEnum.END.name(),null));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("l5","ut3", NodeTypeEnum.END.name(),null));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("l6","ut4", NodeTypeEnum.END.name(),null));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("l7","ut5", NodeTypeEnum.END.name(),null));

        // 2. Generate graphical information
        new BpmnAutoLayout(model).execute();

        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();

        //后缀名 .bpmn 由 BpmnDeployer 加载解析
        deploymentBuilder
                .addBpmnModel(bpmn_model_name_prefix+ strategyname + ".bpmn", model)
                .name(deploy_name_prefix+strategyname)
                .deploy();

        logger.warn("流程的重新部署................[完成].");
    }



    public Map completeTask(JSONObject params){

        String taskId = params.getString("taskId");
        if(StringUtils.isEmpty(taskId)){
            logger.info("任务ID不存在");
            Map<String,Object> returnMap = new HashMap<>();
            returnMap.put("code",500);
            returnMap.put("msg","任务ID不存在");

            return returnMap;
        }


        Map<String,String> dataMap = new HashMap<>();

        //检查任务是否已经存在
        //防止重复执行
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task == null){
            logger.info("任务不存在.说明已经执行完毕了》taskId:{}",taskId);
            dataMap.put("msg","任务不存在.说明已经执行完毕了》taskId:"+ taskId);
            return dataMap;
        }

        //将数据源反馈的因子值 K-V ==》流程中
        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariables(processInstanceId,params);

        taskService.complete(taskId);

        logger.info("执行了任务。taskId:{}",taskId);

        //获取下一个任务
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        if(CollectionUtils.isEmpty(tasks)){
            logger.info("没有需要执行的任务了");
            dataMap.put("msg","没有需要执行的任务了，流程已经完结");
            return dataMap;
        }

        logger.info("返回下一个任务ID:{}",tasks.get(0).getId());
        dataMap.put("taskId",tasks.get(0).getId());
        return dataMap;
    }
}
