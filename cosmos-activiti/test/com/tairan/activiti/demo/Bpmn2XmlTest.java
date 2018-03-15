package com.tairan.activiti.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.test.Deployment;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.xunyi.cloud.wisdom.activiti.service.BaseService;

/**
 * http://blog.csdn.net/as154930786/article/details/64906110
 * BPMN、XML文件相互转化
 * 
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月13日 下午4:22:20 
 * @Description
 */
public class Bpmn2XmlTest extends BaseService {
	@Test
    @Deployment(resources={"resource/测试文件.bpmn"})
    public void testXmltoBpmn() throws XMLStreamException{
        ProcessDefinition processDefinition =    repositoryService
                                    .createProcessDefinitionQuery()
                                    .processDefinitionKey("leave").singleResult();
        //获取流程资源的名称
        String sourceName = processDefinition.getResourceName();
        //获取流程资源
        InputStream inputStream = repositoryService.getResourceAsStream(
        		processDefinition.getId(),sourceName);
        //创建转换对象
        BpmnXMLConverter converter = new BpmnXMLConverter();
        //读取xml文件
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(inputStream);
        //将xml文件转换成BpmnModel
        BpmnModel bpmnModel = converter.convertToBpmnModel(reader);
        //验证bpmnModel是否为空
        assertNotNull(bpmnModel);
        org.activiti.bpmn.model.Process process = bpmnModel.getMainProcess();
        //验证转换的流程id
        assertEquals("leave",process.getId());                  
    }
	
		@Deployment(resources={"resource/测试文件.bmpn"})
	    @Test
	    public void BpmnModeltoXml(){
	        ProcessDefinition processDefinition =    repositoryService
	                                                .createProcessDefinitionQuery()
	                                                .processDefinitionKey("leave").singleResult();
	        //获取BpmnModel对象
	        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
	        //创建转换对象
	        BpmnXMLConverter converter = new BpmnXMLConverter();
	        //把bpmnModel对象转换成字符
	        byte[] bytes = converter.convertToXML(bpmnModel);
	        String xmlContenxt = bytes.toString();
	        System.out.println(xmlContenxt);
	        
	        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	        
	      //验证bpmnModel 是否是正确的bpmn xml文件
	        ProcessValidatorFactory processValidatorFactory=new ProcessValidatorFactory();
	        ProcessValidator defaultProcessValidator = processValidatorFactory.createDefaultProcessValidator();
	        //验证失败信息的封装ValidationError
	        List<ValidationError> validate = defaultProcessValidator.validate(bpmnModel);
	        System.out.println(validate.size());
//	        需要说明的是：ValidationError封装的是验证信息，如果size为0说明，bpmnmodel正确，大于0,说明自定义的bpmnmodel是错误的，不可以使用的。
	    }
	
	
		@Deployment(resources={"resource/测试文件.bmpn"})
	    @Test
		public void getXmlResource() throws IOException{
			ProcessDefinition processDefinition =    repositoryService
                     .createProcessDefinitionQuery()
                     .processDefinitionKey("leave").singleResult();
			//获取流程资源的名称
			String sourceName = processDefinition.getResourceName();
			//获取流程资源
			InputStream inputStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(),sourceName);
			
			IOUtils.toByteArray(inputStream);
			
		}
		
		
		@Deployment(resources={"resource/测试文件.bmpn"})
	    @Test
		public void getImageResource() throws IOException{
			ProcessDefinition processDefinition =    repositoryService
                    .createProcessDefinitionQuery()
                    .processDefinitionKey("leave").singleResult();
			//获取流程资源的名称
			String diagramResourceName = processDefinition.getDiagramResourceName();
			//获取流程资源
			InputStream inputStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(),diagramResourceName);
			
			IOUtils.toByteArray(inputStream);
		}
	
	
}
