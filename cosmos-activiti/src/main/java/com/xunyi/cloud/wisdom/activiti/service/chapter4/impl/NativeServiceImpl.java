package com.xunyi.cloud.wisdom.activiti.service.chapter4.impl;

import java.util.List;

import org.activiti.bpmn.model.Task;
import org.activiti.engine.history.HistoricProcessInstance;
import org.springframework.stereotype.Service;

import com.xunyi.cloud.wisdom.activiti.service.BaseService;
import com.xunyi.cloud.wisdom.activiti.service.chapter4.NativeService;

/**
 * @author thomas
 * @version v1.0  
 * @Date 2018年3月13日 下午2:58:31 
 * @Description
 */
@Service("nativeService")
public class NativeServiceImpl extends BaseService implements NativeService {

	/* (non-Javadoc)
	 * @see com.xunyi.cloud.wisdom.activiti.service.chapter4.NativeService#nativeInfo()
	 */
	@Override
	public void nativeInfo() {
		
		String tableName = managementService.getTableName(Task.class);
		logger.info("得到的表的名称 tableName={}",tableName);
		
		
		String htname = managementService.getTableName(HistoricProcessInstance.class);
		List<String> tableColumnNames =managementService.getTableMetaData(htname).getColumnNames();
	
		logger.info("表列名 tableColumnNames={}",tableColumnNames);
		
		List<HistoricProcessInstance> hisPis = historyService.createNativeHistoricProcessInstanceQuery()
				.sql("select * from "+htname+" where "+tableColumnNames.get(0)+" = #{a}")
				.parameter("a", "1").list(); 
		logger.info("hisPis={}",hisPis);		
	}

	
}
