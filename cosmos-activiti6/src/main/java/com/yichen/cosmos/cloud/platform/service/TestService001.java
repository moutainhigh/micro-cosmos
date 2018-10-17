package com.yichen.cosmos.cloud.platform.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.yichen.cosmos.cloud.platform.enums.NodeTypeEnum;
import com.yichen.cosmos.cloud.platform.util.ActivitiUtils;
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


//    @Transactional(isolation=Isolation.SERIALIZABLE)
    public Map completeTask(String taskId,Map<String,Object> params,String active){

        Map<String,String> dataMap = new HashMap<>();

        //检查任务是否已经存在
        //防止重复执行
        //问题：监听器：MQ消息发出去，factor异步迅速通知过来，导致创建的节点还未插入数据库中，所以
//        查询时，发现任务不存在，导致系统认为任务已经执行完毕
        //TODO  1. 重试机制  2.先插入DB，再触发监听器(调整顺序)  3.在Query查询前，提交事务Commit，插入DB
        //问题总结：即数据脏读，读写不一致
        logger.warn("[执行任务查询，模拟脏读]taskId:{}",taskId);
        org.activiti.engine.task.Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task == null){
            logger.warn("任务不存在.说明已经执行完毕了》taskId:{}",taskId);
            dataMap.put("msg","任务不存在.说明已经执行完毕了》taskId:"+ taskId);
            return dataMap;
        }

        logger.warn("[任务执行] taskId:{},taskName:{}",task.getId(),task.getName());
        String proId = task.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proId).singleResult();
      /*  if(!"active".equalsIgnoreCase(active)){
            if(task.isSuspended()){
                logger.info("如果任务被挂起了，需要重新激活；目前可以通过挂起流程，实现任务的挂起");
                dataMap.put("msg","如果任务被挂起了，需要重新激活；目前可以通过挂起流程，实现任务的挂起》taskId:"+ taskId);
                return dataMap;
            }
        }else{
//            String proId = task.getProcessInstanceId();
//            processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proId).singleResult();
            logger.info("processInstance status:{}",processInstance.isSuspended());
            if(processInstance.isSuspended()){
                runtimeService.activateProcessInstanceById(proId);
            }
        }*/

        //研究一下任务的暂停和激活
        //然后可以异步发起请求了，接收异步通知，激活，继续执行
        //>>> 通过挂起流程实例，实现任务的挂起
        //对节点增加监听器：规则节点 BusinessRuleTask 是监听节点先执行还是规则先执行
        //规则执行的细节:是否与jbpm相同，如：规则组、独占执行等

        //            taskService.claim();
        //暂不考虑任务的认领（claim、assign）及所属owner
        //因为这个任务不一定是userTask
//        taskService.complete(taskId,params);


        //20180713 UserTask--》BusinessRuleTask-->UserTask----》BusinessRuleTask
        //任务执行时，下一条线可能会用到连线变量
        //变量值可能来自上一个规则节点的drools的决策结果，也可能来自MQ或其它第三方传入的值

        Map<String,Object> flowParamMap = new HashMap<>();
        flowParamMap.putAll(params);
        //获取BusinessRuleTask 的drools的值，这里输出结果都 统一为 rulesOutput，且在流程执行过程中，所以
        //可以直接从表act_ru_variable 中获取
        /*
        在这里取值是不对的
        1. 时机不对，应该在drools执行完毕后就获取，因为下面线中条件变量开始使用了
        2. 执行完后遇UserTask暂停
        Object obj = runtimeService.getVariable(processInstance.getId(), "rulesOutput");
        System.out.println("看看取出来的变量是啥格式："+ JSON.toJSONString(obj));
        if(obj instanceof  Map){
            flowParamMap.putAll((Map) obj);
        }*/


        //将数据源反馈的因子值 K-V ==》流程中
        String processInstanceId = task.getProcessInstanceId();
        /*runtimeService.setVariables(processInstanceId,params);

        //触发规则，入参名  map
        Map<String,Object> initMap = runtimeService.getVariable(processInstanceId, ActivitiConstants.FLOW_INPUT_VARIABLE_NAME,Map.class);
        if(initMap == null || initMap.isEmpty()){
            initMap = new HashMap<>();//会把旧的覆盖
        }
        initMap.putAll(params);
        runtimeService.setVariable(processInstanceId,ActivitiConstants.FLOW_INPUT_VARIABLE_NAME,initMap);*/

        Map<String, Object> variables = runtimeService.getVariables(processInstanceId);
        System.out.println("打印出所有的variables: "+ JSON.toJSONString(variables));
        //test  TODO
//        flowParamMap.put("result","pass");
        taskService.complete(taskId);

        logger.warn("执行了任务。taskId:{}",taskId);

        //问题：如果流程最后一个节点是businessRuleTask  ,一次性执行完毕后
        //表 act_ru_variable 变量为空，则无法获取数据
        //抛出异常信息： "org.activiti.engine.ActivitiObjectNotFoundException","execution 12b047083ae34df7bd07dba44e76d670 doesn't exist"
        //这时候，可以去历史表（act_hi_varinst），获取数据
        //---------------------------------------------------------------------------------
        //map 为规则节点 BusinessRuleTask的入参 ，也是规则里面声明的map变量名
        /*Map ruleOutputMap = (Map) runtimeService.getVariable(processInstance.getId(), "map");
        System.out.println("规则执行结果："+ ruleOutputMap);

        //rulesOutput 是BusinessRuleTask的结果参数
        Object rulesOutput = (Object) runtimeService.getVariable(processInstance.getId(), "rulesOutput");
        System.out.println("规则执行结果："+ JSON.toJSONString(rulesOutput));*/


        //获取下一个任务
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        if(CollectionUtils.isEmpty(tasks)){
            logger.info("没有需要执行的任务了");
            dataMap.put("msg","没有需要执行的任务了，流程已经完结");
            //TODO  获取流程最终所有结果
            return dataMap;
        }

        logger.info("返回下一个任务ID:{}",tasks.get(0).getId());
        dataMap.put("taskId",tasks.get(0).getId());
        return dataMap;
    }




    //根据流程定义，启动一个流程实例
//    @Transactional(isolation=Isolation.SERIALIZABLE)
    public Map startProcessByKey(String strategyname,String active){
        Map<String,String> dataMap = new HashMap<>();
        //processDefinitionKey是流程XML 文件中的ID
        //processDefinitionId 是数据库表中 act_re_procdef 对应的记录ID
        ProcessDefinition prodef = repositoryService.createProcessDefinitionQuery().processDefinitionName(proc_def_name_prefix+strategyname).singleResult();
        Assert.notNull(prodef,"不存在该策略的流程定义。strategyname："+strategyname);

        if(prodef.isSuspended()){

            repositoryService.activateProcessDefinitionById(prodef.getId());

        }
        //1. 执行，启动一个流程定义的流程实例：非传参示例

        logger.warn("启动流程——开始.....strategyname:{}",strategyname);
        ProcessInstance processInstance =runtimeService.startProcessInstanceByKey(prodef.getKey());
        logger.warn("流程启动——结束.....strategyname:{}",strategyname);
        //2. 执行，启动一个流程定义的流程实例：【传参示例】
        /*Map<String,Object> params = new HashMap<>();
        Map<String,Object> tmpMap = new HashMap<>();
        params.put("map",tmpMap);*/
//        ProcessInstance processInstance =runtimeService.startProcessInstanceByKey(prodef.getKey(),params);

        //流程中需要 UserTask，暂停后，添加变量影响BusinessRuleTask
//        runtimeService.setVariable(processInstance.getId(),"map",tmpMap);

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

        logger.warn("****下一个任务名称***** task name：{}",tasks.get(0).getName());


        dataMap.put("taskId",tasks.get(0).getId());//因为流程设计的并非是并行的、会签的，且只有一个执行节点
        dataMap.put("processInstanceId",processInstance.getProcessInstanceId());

        /*if(!"active".equalsIgnoreCase(active)){
            runtimeService.suspendProcessInstanceById(processInstance.getProcessInstanceId());
        }*/
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
        process.addFlowElement(ActivitiUtils.createUserTask("task_001", "thomas First task", "thomas"));
        process.addFlowElement(ActivitiUtils.createUserTask("task_002", "thomas Second task", "suzhiqiang"));
        process.addFlowElement(ActivitiUtils.createEndEvent());

        process.addFlowElement(ActivitiUtils.createSequenceFlow(NodeTypeEnum.START.name(), "task_001",null));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("task_001", "task_002",null));
        process.addFlowElement(ActivitiUtils.createSequenceFlow("task_002", NodeTypeEnum.END.name(),null));

        // 2. Generate graphical information
        new BpmnAutoLayout(model).execute();

        repositoryService.createDeployment()
                .addBpmnModel(bpmn_model_name_prefix+ strategyname + ".bpmn", model).name(deploy_name_prefix+strategyname).deploy();
        logger.warn("流程的重新部署................[完成].");
    }

}
