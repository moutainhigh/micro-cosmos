package com.xunyi.cloud.wisdom.activiti.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 加载activiti-bpmn文件
 * 1. 第一次加载，将文件数据信息保存到数据库中
 * 2. 当系统再次重启，指定processInstanceId执行工作流时，当工作流不存在时(activiti缓存没有)，则从数据库里读取工作流文件，
 * 并加载到activiti缓存中
 * 
 * @author thomas
 *
 */
@RestController
@RequestMapping(value = "/loanbpmn", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoadbpmnFileController {
		
	
	
}
