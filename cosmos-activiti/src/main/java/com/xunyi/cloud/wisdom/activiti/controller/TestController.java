package com.xunyi.cloud.wisdom.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.xunyi.cloud.wisdom.activiti.service.ITestService;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thomas.su on 2018/2/27 17:26.
 */
@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestController {

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


        String fileUrl = "D:\\work\\intelij\\csp\\micro-cosmos\\cosmos-activiti\\src\\main\\resources\\diagram\\common_auto_flow2.bpmn";

        try {
            File file = new File(fileUrl);
            System.out.println("file:" + file);
            if (file.exists()) {
                System.out.println("file exist... ");
            }

            //如果读取不到，文件没有编译进去，查看target
//            InputStream inputStream = TestController.class.getClassLoader().getResourceAsStream("diagram/common_auto_flow2.bpmn");
//
//            System.out.println("inputStream="+inputStream);
//            Deployment deployment = repositoryService.createDeployment().addInputStream("auto_flow", inputStream).deploy();

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


}
