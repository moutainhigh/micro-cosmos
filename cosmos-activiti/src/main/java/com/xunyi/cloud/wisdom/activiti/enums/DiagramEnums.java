package com.xunyi.cloud.wisdom.activiti.enums;

/**
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月10日 下午6:38:09 
 * @Description
 */
public enum DiagramEnums {
	
	//第五章
	
	
	
	//====================>=>=>====================================================
	//测试XML、Image 文件输出流
	testxmlimage("diagram/testxmlimage.bpmn","testxmlimage task","testxmlimage"),
	//分歧测试
	splitflow("diagram/chapter4/splitflow.bpmn","splittest task","splitflow"),
	
	//接收任务测试
	receiveTask("diagram/chapter4/receivetask.bpmn","receive task","receiveTask"),
	
	
	//定时器启动
	timecycle("diagram/chapter4/timecycle.bpmn","time cycle","timecycle_20180312"),//timecycle_20180312
	
	

	//简单流程测试
	common_auto_flow2("diagram/common_auto_flow2.bpmn","activiti test","auto_flow"),
	
	//审核流程
	auditflow("diagram/auditflow.bpmn","audit flow","auditflow"),
	
	//泳池泳道，示例
	LanePattern("diagram/LanePattern.bpmn","LanePattern","process_flow1"),
	
	//test
	leavesayhello("diagram/leavesayhello.bpmn","leavesayhello","leavesayhello"),
	
	;
	
	DiagramEnums(String path,String desc,String processKey){
		this.path = path;
		this.desc = desc;
		this.processKey = processKey;
	}
	String path;
	String desc;
	String processKey;
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the processKey
	 */
	public String getProcessKey() {
		return processKey;
	}
	/**
	 * @param processKey the processKey to set
	 */
	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}
}
