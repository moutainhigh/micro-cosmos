package com.xunyi.cloud.wisdom.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.xunyi.cloud.wisdom.activiti.service.ITestService;
import com.yichen.cosmos.cloud.platform.bean.Response;
import com.yichen.cosmos.cloud.platform.bean.ResponseStatus;
import com.yichen.cosmos.cloud.platform.util.HttpsUtils;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**简单演示：common_auto_flow2.bpmn 
 * 1. 文件读取加载
 * 2. serviceTask、UserTask执行
 * Created by thomas.su on 2018/2/27 17:26.
 */
@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private FormService formService;


    @Autowired
    private ITestService testService;

    Map<String,String> processDefinitionKeyMap = new HashMap<>();


        @RequestMapping(value = "/taskComplete", method = RequestMethod.GET)
    public String taskComplete(HttpServletRequest request) throws Exception {

        String processDefinitionKey = processDefinitionKeyMap.get("processDefinitionKey");
        Task task = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).singleResult();
            String processDefinitionId = "processDefinitionId";
            taskService.createTaskQuery().processDefinitionId(processDefinitionId).singleResult();
        String name = task.getName();
        String taskId = task.getId();

        System.out.println("task id=" + taskId + ",task name=" + name);

        Map<String, Object> ids = new HashMap<>();
        ids.put("userId","thomas_id");
        taskService.complete(taskId,ids);
        System.out.println("task completed!");

        return "processDefinitionKey："+processDefinitionKey;

    }
    @RequestMapping(value = "/getTest", method = RequestMethod.GET)
    public String getTest(HttpServletRequest request) throws Exception {
        new Thread(
                () -> {
                    try {
                        loanBPMNFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        ).start();

        return JSON.toJSONString(testService.getTestById(Integer.parseInt(request.getParameter("id"))));
    }


    public void loanBPMNFile() throws Exception {


//        String fileUrl = "D:\\work\\intelij\\csp\\micro-cosmos\\cosmos-activiti\\src\\main\\resources\\diagram\\common_auto_flow2.bpmn";

        try {
           /* File file = new File(fileUrl);
            System.out.println("file:" + file);
            if (file.exists()) {
                System.out.println("file exist... ");
            }*/

            //如果读取不到，文件没有编译进去，查看target
//            InputStream inputStream = TestController.class.getClassLoader().getResourceAsStream("diagram/common_auto_flow2.bpmn");
//
//            System.out.println("inputStream="+inputStream);
//            Deployment deployment = repositoryService.createDeployment().addInputStream("auto_flow", inputStream).deploy();

            //加载工作流文件
            String fileUrl2 = "diagram/common_auto_flow2.bpmn";
            Deployment deployment = repositoryService.createDeployment()
                    .addClasspathResource(fileUrl2).deploy();

            System.out.println("1111111111");
            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
            System.out.println("list.size():" + list.size());


            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

            System.out.println("222222222");

            String resourceName = processDefinition.getResourceName();
            String diagramResourceName = processDefinition.getDiagramResourceName();

            System.out.println("resourceName=" + resourceName + ",diagramResourceName=" + diagramResourceName);

            String processDefinitionKey = processDefinition.getKey();
            if (processDefinition.isSuspended()) {
                repositoryService.activateProcessDefinitionByKey(processDefinitionKey);
            }

            Map<String, Object> variables = new HashMap<>();
            variables.put("branch_condition_parameter", "pass");
            variables.put("userId", "1111111");

            runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
            System.out.println("333333333:流程启动");

            processDefinitionKeyMap.put("processDefinitionKey",processDefinitionKey);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequestMapping(value = "/testTimeOut2Node", method = RequestMethod.POST)
    public String testTimeOut2Node(HttpServletRequest request,@RequestBody String param){
        logger.info("调用的参数：{}",param);
        String url = "http://127.0.0.1:33001/xunyi-rabbitmq/start/testTimeOut3Node";
        Map<String,String> params = new HashMap<>();
        params.put("name","thomas-node2");
        params.put("thread-name",Thread.currentThread().getName());
        try{

            long start = System.currentTimeMillis();
            TimeUnit.MINUTES.sleep(1);
            String result = HttpsUtils.doPost(url,JSON.toJSONString(params));

            logger.info("第二个节点 result={}，本身休眠5秒，统计耗时：{}",result,System.currentTimeMillis()-start);
        }catch (Exception e){
            logger.error("http 超时调用异常",e);
            e.printStackTrace();
            return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_500).msg(ResponseStatus.RESPONSE_MSG_500)
                    .build().toString();
        }
        return new Response.Builder().code(ResponseStatus.RESPONSE_CODE_200).msg(ResponseStatus.RESPONSE_MSG_200)
                .data(params)
                .build().toString();
    }


}
