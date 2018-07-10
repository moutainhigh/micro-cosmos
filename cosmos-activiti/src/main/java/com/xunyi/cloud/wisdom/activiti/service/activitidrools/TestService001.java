package com.xunyi.cloud.wisdom.activiti.service.activitidrools;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.xunyi.cloud.wisdom.activiti.service.BaseService;
import com.xunyi.cloud.wisdom.activiti.util.ActivitiUtils;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:thomas
 * @Date: 2018/7/5 13:52
 * @Description:
 *  1. 动态创建流程，创建部署文件 createDeployment
 *  2. 启动流程实例
 *  3. 执行任务（模拟异步通知）
 *
 */
@Service
public class TestService001 extends BaseService{

    private static final String deploy_name_prefix = "deploy_name_";
    private static final String proc_def_name_prefix = "proc_def_name_";
    private static final String proc_def_id_prefix = "proc_def_id_";
    private static final String bpmn_model_name_prefix = "bpmn_model_name_";


    public Map completeTask(String taskId,Map<String,Object> params,String active){

        Map<String,String> dataMap = new HashMap<>();

        //检查任务是否已经存在
        //防止重复执行
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task == null){
            logger.info("任务不存在.说明已经执行完毕了》taskId:{}",taskId);
            dataMap.put("msg","任务不存在.说明已经执行完毕了》taskId:"+ taskId);
            return dataMap;
        }

        logger.info("task.isSuspended:{}",task.isSuspended());
        if(!"active".equalsIgnoreCase(active)){
            if(task.isSuspended()){
                logger.info("如果任务被挂起了，需要重新激活；目前可以通过挂起流程，实现任务的挂起");
                dataMap.put("msg","如果任务被挂起了，需要重新激活；目前可以通过挂起流程，实现任务的挂起》taskId:"+ taskId);
                return dataMap;
            }
        }else{
            String proId = task.getProcessInstanceId();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proId).singleResult();
            logger.info("processInstance status:{}",processInstance.isSuspended());
            if(processInstance.isSuspended()){
                runtimeService.activateProcessInstanceById(proId);
            }
        }

        //研究一下任务的暂停和激活
        //然后可以异步发起请求了，接收异步通知，激活，继续执行
        //>>> 通过挂起流程实例，实现任务的挂起
        //对节点增加监听器：规则节点 BusinessRuleTask 是监听节点先执行还是规则先执行
        //规则执行的细节:是否与jbpm相同，如：规则组、独占执行等

        //            taskService.claim();
        //暂不考虑任务的认领（claim、assign）及所属owner
        //因为这个任务不一定是userTask
        taskService.complete(taskId,params);
        logger.info("执行了任务。taskId:{}",taskId);
        //获取下一个任务
        String processInstanceId = task.getProcessInstanceId();
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



    //根据流程定义，启动一个流程实例
    public Map startProcessByKey(String strategyname,String active){
        Map<String,String> dataMap = new HashMap<>();
        //processDefinitionKey是流程XML 文件中的ID
        //processDefinitionId 是数据库表中 act_re_procdef 对应的记录ID
        ProcessDefinition prodef = repositoryService.createProcessDefinitionQuery().processDefinitionName(proc_def_name_prefix+strategyname).singleResult();
        Assert.notNull(prodef,"不存在该策略的流程定义。strategyname："+strategyname);

        if(prodef.isSuspended()){

            repositoryService.activateProcessDefinitionById(prodef.getId());

        }
        ProcessInstance processInstance =runtimeService.startProcessInstanceByKey(prodef.getKey());
        if(processInstance.isSuspended()){
            //可用于分布式服务：同一个流程实例可在不同的服务上执行
            runtimeService.activateProcessInstanceById(processInstance.getProcessInstanceId());
            //根据一个流程实例的id挂起该流程实例
//            runtimeService.suspendProcessInstanceById(processInstance.getProcessInstanceId());
        }

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
        if(CollectionUtils.isEmpty(tasks)){
            logger.info("===== [启动流程] 没有执行的任务了 =================== ");
            dataMap.put("msg","[启动流程] 没有执行的任务了");
            return dataMap;
        }

        logger.info("********* task name：{}",tasks.get(0).getName());


        dataMap.put("taskId",tasks.get(0).getId());//因为流程设计的并非是并行的、会签的，且只有一个执行节点
        dataMap.put("processInstanceId",processInstance.getProcessInstanceId());

        if(!"active".equalsIgnoreCase(active)){
            runtimeService.suspendProcessInstanceById(processInstance.getProcessInstanceId());
        }
        return dataMap;
    }

    //部署文件
    //生成流程定义
    //deployId 全局唯一,对应strategyname 全局唯一
    //tenantId 注意使用，区分不同的租户   TODO
    //通过UserTask 实现流程暂停
    //通过task isSuspended  实现暂停
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
        }

        logger.warn("准备流程的重新部署.................");

        // 1. Build up the model from scratch
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        model.addProcess(process);
        process.setId(proc_def_id_prefix+strategyname);
        process.setName(proc_def_name_prefix+strategyname);

        process.addFlowElement(ActivitiUtils.createStartEvent());
        process.addFlowElement(ActivitiUtils.createUserTask("task1", "thomas First task", "thomas"));
        process.addFlowElement(ActivitiUtils.createUserTask("task2", "thomas Second task", "suzhiqiang"));
        process.addFlowElement(ActivitiUtils.createEndEvent());

        process.addFlowElement(ActivitiUtils.createSequenceFlow("start", "task1"));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("task1", "task2"));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("task2", "end"));

        // 2. Generate graphical information
        new BpmnAutoLayout(model).execute();

        repositoryService.createDeployment()
                .addBpmnModel(bpmn_model_name_prefix+ strategyname + ".bpmn", model).name(deploy_name_prefix+strategyname).deploy();
        logger.warn("流程的重新部署................[完成].");
    }

}
