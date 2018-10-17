package com.xunyi.cloud.wisdom.activiti.service.event;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月13日 下午3:43:55 
 * @Description
 */
public class GlobalEventListener implements ActivitiEventListener{

	private static final Logger logger = LoggerFactory.getLogger(GlobalEventListener.class);
	
	/** (non-Javadoc)
	 * @see org.activiti.engine.delegate.event.ActivitiEventListener#onEvent(org.activiti.engine.delegate.event.ActivitiEvent)
	 * {@link ActivitiEventType}
	 */
	@Override
	public void onEvent(ActivitiEvent event) {
/*
		System.out.println("流程事件信息：");
		System.out.println("流程定义：" + event.getProcessDefinitionId());
		System.out.println("流程实例：" + event.getProcessInstanceId());
		System.out.println("流程执行ID：" + event.getExecutionId());
		System.out.println("操作：" + event.getType());
*/

//		System.out.println("捕获事件类型："+event.getType().name()+"type="+ ToStringBuilder.reflectionToString(event));
		
		
	/*	ActivitiEventType eventType = event.getType();
		
		//事件类型  {@link ActivitiEventType}
		switch(eventType){
		 case ENGINE_CREATED:
			 System.out.println("流程初始化  *****************************************************");
             break;
         case PROCESS_COMPLETED:
			 System.out.println("【全局】【最终】   =============[流程结束啦]=========================================");
			 System.out.println("流程定义：" + event.getProcessDefinitionId());
			 System.out.println("流程实例：" + event.getProcessInstanceId());
			 System.out.println("流程执行ID：" + event.getExecutionId());
			 System.out.println("操作：" + event.getType());

			 HistoryService historyService = (HistoryService) SpringContextHelper.getBean("historyService");
			 HistoricVariableInstanceQuery historicVariableInstanceQuery = historyService.createHistoricVariableInstanceQuery().processInstanceId(event.getProcessInstanceId());
			 logger.info("【最终】[测试节点结束事件]>>>[监听器]historicVariableInstanceQuery:{}",historicVariableInstanceQuery);
			 if(historicVariableInstanceQuery != null){
				 List<HistoricVariableInstance> variableInstances = historyService.createHistoricVariableInstanceQuery().processInstanceId(event.getProcessInstanceId()).list();
				 logger.info("【最终】[测试节点结束事件]>>>执行过程中，流程未结束；所以变量还未移到历史表中。列表可能为空：variableInstances.size:{}",variableInstances.size());
				 if(CollectionUtils.isNotEmpty(variableInstances)){
					 for(HistoricVariableInstance hvi:variableInstances){
						 //注意：
						 //以BusinessRuleTask的入参和结果参数名为例：流程中存在多个规则节点，历史变量名如果相同的话，每次执行时会被覆盖；
						 //查询结果示例：
						 //查询流程历史变量值： 5b4aaa15e08442b4ab5958505b65677d  -  map	 -  {}
//                      查询流程历史变量值： 8c00d6544e634f0d808cf16ccb167dc0  -  rulesOutput	 -  [{result=pass}]
						 System.out.println("【最终】[测试节点结束事件]>>>[监听器]查询流程历史变量值： "+ hvi.getId()+"  -  "+hvi.getVariableName()+"	 -  "+hvi.getValue());

						 if(ActivitiConstants.FLOW_INPUT_VARIABLE_NAME.equals(hvi.getVariableName())){
							 System.out.println(JSON.toJSONString(hvi.getValue()));
							 break;
						 }
					 }
				 }
			 }
             break;
         default:
			 System.out.println("event type name:"+event.getType().name());
         		
		}
*/
		logger.warn("捕获事件类型：{},type:{}",event.getType().name(), ToStringBuilder.reflectionToString(event));

	}

	@Override
	public boolean isFailOnException() {
		return false;
	}
}
