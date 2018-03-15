package com.tairan.activiti.demo;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

/**
 * 参照官方示例代码，创建activiti所需表 https://www.activiti.org/quick-start
 * Created by thomas on 2018/2/28 13:52.
 */
public class TestActiviti {
    //http://www.zuidaima.com/blog/3045359293975552.htm    5.16 版本 23张表  5.18版本 24张表  5.19版本 25张表
    /** 使用代码创建工作流所需的25张表 **/
    @Test
    public void test() {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        // 链接数据库的配置
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/cosmos-activiti?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("root");
        /*
         * DB_SCHEMA_UPDATE_FALSE 不能创建表，需要表存在
         * DB_SCHEMA_UPDATE_CREATE_DROP 先删除表再创建表
         * DB_SCHEMA_UPDATE_TRUE 如表不存在自动创建表
         */
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //工作流的核心对象，ProcessEngine对象
        ProcessEngine processEngine= configuration.buildProcessEngine();
        System.out.println("processEngine="+processEngine);


        //加载不到资源
//        String fileUrl = "D:/work/intelij/csp/micro-cosmos/cosmos-activiti/src/main/resources/diagram/common_auto_flow2.bpmn";
        String fileUrl2="diagram/common_auto_flow2.bpmn";
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(fileUrl2).deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).singleResult();
        System.out.println(
                "Found process definition ["
                        + processDefinition.getName() + "] with id ["
                        + processDefinition.getId() + "]");

        String resourceName = processDefinition.getResourceName();
        String diagramResourceName = processDefinition.getDiagramResourceName();
        System.out.println(
                "resourceName:"+resourceName
                +
                        "diagramResourceName:"+diagramResourceName

        );
    }

}
