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
        String fileUrl = "D:\\work\\prj\\pace\\platform\\new2\\micro-cosmos\\cosmos-activiti\\src\\main\\resources\\diagram\\common_auto_flow2.bpmn";
        /*
        InputStream inputStream = null;
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection http =  (HttpURLConnection) url.openConnection();
            inputStream = http.getInputStream();
            http.connect();
        } catch (Exception e) {
        }*/

        try {
            File file = new File(fileUrl);
            System.out.println("file:" + file);
            if (file.exists()) {
                System.out.println("file exist... ");
            }

            InputStream inputStream = new FileInputStream(new File(fileUrl));
            System.out.println("inputStream=");
            Deployment deployment = repositoryService.createDeployment().addInputStream("auto_flow", inputStream).deploy();

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

            runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);

            Task task = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).singleResult();
            String name = task.getName();
            String taskId = task.getId();

            System.out.println("task id=" + taskId + ",task name=" + name);
            task.setAssignee("thomas");

            taskService.complete(taskId);
            System.out.println("task completed!");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
