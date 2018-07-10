
package com.tairan.activiti.demo.dynamic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.zip.ZipInputStream;


/**
 * 代码动态生成工作流
 * @author thomas
 * @version v1.0
 * @Date 2018年3月12日 下午8:22:13
 * @Description
 */

public class DynamicActivitiProcessTest {
	 @Rule
	  public ActivitiRule activitiRule = new ActivitiRule();

	  @Test
	  public void testDynamicDeploy() throws Exception {
	    // 1. Build up the model from scratch
	    BpmnModel model = new BpmnModel();
	    Process process = new Process();
	    model.addProcess(process);
	    process.setId("my-process");

	    process.addFlowElement(createStartEvent());
	    process.addFlowElement(createUserTask("task1", "First task", "fred"));
	    process.addFlowElement(createUserTask("task2", "Second task", "john"));
	    process.addFlowElement(createEndEvent());

	    process.addFlowElement(createSequenceFlow("start", "task1"));
	    process.addFlowElement(createSequenceFlow("task1", "task2"));
	    process.addFlowElement(createSequenceFlow("task2", "end"));

	    // 2. Generate graphical information
	    new BpmnAutoLayout(model).execute();

	    // 3. Deploy the process to the engine
	    Deployment deployment = activitiRule.getRepositoryService().createDeployment()
	        .addBpmnModel("dynamic-model.bpmn", model).name("Dynamic_process_deployment").deploy();

	    // 4. Start a process instance
	    ProcessInstance processInstance = activitiRule.getRuntimeService()
	        .startProcessInstanceByKey("my-process");

	    // 5. Check if task is available
	    List<Task> tasks = activitiRule.getTaskService().createTaskQuery()
	      .processInstanceId(processInstance.getId()).list();

	    Assert.assertEquals(1, tasks.size());
	    Assert.assertEquals("First task", tasks.get(0).getName());
	    Assert.assertEquals("fred", tasks.get(0).getAssignee());

	    // 6. Save process diagram to a file
	    InputStream processDiagram = activitiRule.getRepositoryService().getProcessDiagram(processInstance.getProcessDefinitionId());
	    FileUtils.copyInputStreamToFile(processDiagram, new File("target/diagram.png"));

	    // 7. Save resulting BPMN xml to a file
	    InputStream processBpmn = activitiRule.getRepositoryService().getResourceAsStream(deployment.getId(), "dynamic-model.bpmn");
	    FileUtils.copyInputStreamToFile(processBpmn, new File("target/process.bpmn20.xml"));
	  }

	  protected UserTask createUserTask(String id, String name, String assignee) {
	    UserTask userTask = new UserTask();
	    userTask.setName(name);
	    userTask.setId(id);
	    userTask.setAssignee(assignee);
	    return userTask;
	  }

	  protected SequenceFlow createSequenceFlow(String from, String to) {
	    SequenceFlow flow = new SequenceFlow();
	    flow.setSourceRef(from);
	    flow.setTargetRef(to);
	    return flow;
	  }

	  protected StartEvent createStartEvent() {
	    StartEvent startEvent = new StartEvent();
	    startEvent.setId("start");
	    return startEvent;
	  }

	  protected EndEvent createEndEvent() {
	    EndEvent endEvent = new EndEvent();
	    endEvent.setId("end");
	    return endEvent;
	  }


    //注入Activiti的RepositoryService
    @javax.annotation.Resource
    RepositoryService repositoryService;

	/**
	 * https://www.programcreek.com/java-api-examples/index.php?api=org.activiti.editor.language.json.converter.BpmnJsonConverter
	 * @throws IOException
     */
    public void modifyBPMN() throws IOException {
        //读取指定ID的Model的二进制资源，是Json格式的。
        byte[] modelSource = repositoryService.getModelEditorSource("3");
        //使用jackson将Model的源信息转换为JsonNode对象
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(modelSource);
        //创建一个bpmn的json转换器
        BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
        //将jsonNode对象转换为bpmnModel的对象
        BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(jsonNode);


        //读取bpmnModel中id为process的流程定义
        Process process = bpmnModel.getProcess("process");

        //创建一个UserTask任务，id为b3,
        UserTask userTask = new UserTask();
        userTask.setId("b3");
        userTask.setName("userTask2");
        userTask.setAssignee("小金");

        //添加到流程中
        process.addFlowElement(userTask);

        //移除开始和userTask1之间的a2箭头，
        process.removeFlowElement("a2");

        //创建一个新的箭头从b2指向b3,也就是从userTask1连接到userTask2
        SequenceFlow flow1 = new SequenceFlow();
        flow1.setSourceRef("b2");
        flow1.setTargetRef("b3");

        //创建一个新的箭头从b3指向b4,也就是从userTask2连接到结束事件
        SequenceFlow flow2 = new SequenceFlow();
        flow2.setSourceRef("b3");
        flow2.setTargetRef("b4");
        //将两个箭头添加到流程定义中
        process.addFlowElement(flow1);
        process.addFlowElement(flow2);

        //对文件进行重新排版，生成位置信息，否则会报错
        new BpmnAutoLayout(bpmnModel).execute();

        //创建一个bpmn的xml转换器，
        BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
        //这里将结果输出到一个文件中,查看是否成功,(必需成功)
        byte[] bytes = xmlConverter.convertToXML(bpmnModel);
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/kingboy/Desktop/MyDIY.bpmn");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    private void test180627() throws Exception{
		String modelId = "";
		byte[] bpmnBytes = null;
		String filename = null;
		JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelId));
		BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
		BpmnModel model = jsonConverter.convertToBpmnModel(editorNode);
		filename = model.getMainProcess().getId() + ".bpmn20.xml";
		bpmnBytes = new BpmnXMLConverter().convertToXML(model);

		ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);

    }

	/**
	 * 发布模型为流程定义
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/model/deploy/{id}", method = RequestMethod.POST)
	@ResponseBody
	public void deploy(@PathVariable("id") String id, HttpServletRequest request) throws Exception {

		//获取模型
		Model modelData = repositoryService.getModel(id);
		byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

		if (bytes == null) {
//			return new Result(false, id, "模型数据为空，请先设计流程并成功保存，再进行发布。");
			return ;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode modelNode = objectMapper.readTree(bytes);

		BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
		if (model.getProcesses().size() == 0) {
//			return new Result(false, id, "数据模型不符要求，请至少设计一条主线流程。");
			return ;
		}
		try {
			//----------------生成zip文件-------------------------
			byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
			/*Result xml = generateResource("xml", id, request);
			Result image = generateResource("image", id, request);
			if (xml.getData() == null || image.getData() == null) {
				return new Result(false, "部署失败", "流程尚未定义或定义错误，不能生成有效的xml和png文件");
			}
			String basePath = request.getRealPath("/");
			String fileName = modelData.getKey() + ".bpmn20.model.zip";
			String zipFileName = DIR_PATH + File.separator + fileName;
			File file = new File(basePath + File.separator + zipFileName);
			if (file.exists()) {
				file.delete();
			}
			String zipPath = FileUtil.generateZipFile(basePath, zipFileName, xml.getData().toString(),
					image.getData().toString());*/
			String zipPath = "";
			InputStream inputStream = new FileInputStream(zipPath);
			ZipInputStream zipInputStream = new ZipInputStream(inputStream);
			//---------------------------------------------------
			//发布流程
			String processName = modelData.getKey() + ".bpmn20.xml";
			DeploymentBuilder deploymentBuilder = repositoryService.createDeployment()
					.name(modelData.getName())
					.category(modelData.getCategory())
					.tenantId(modelData.getTenantId())
					//使用addZipInputStream后可以预防flow连线文字丢失的问题
					.addZipInputStream(zipInputStream);
			//.addString(processName, new String(bpmnBytes, "UTF-8"))


			List<JsonNode> forms=modelNode.findValues("formkeydefinition");
			for (JsonNode form : forms) {
				String formName=form.textValue();
				if(!StringUtils.isEmpty(formName)){
					InputStream stream=this.getClass().getClassLoader().getResourceAsStream(formName);
					deploymentBuilder.addInputStream(formName,stream);
				}
			}
			Deployment deployment=deploymentBuilder.deploy();

			//更新流程定义类别,替换掉页面的定义
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId
					(deployment.getId()).singleResult();
			if (processDefinition != null)
				repositoryService.setProcessDefinitionCategory(processDefinition.getId(), deployment.getCategory());

			modelData.setDeploymentId(deployment.getId());
			repositoryService.saveModel(modelData);
//			return new Result(true);
		} catch (Exception ex) {
//			return new Result(false, "部署失败", ex.getMessage().toString());
		}
	}

}
