//package org.jbpm;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.jbpm.process.instance.impl.demo.SystemOutWorkItemHandler;
//import org.jbpm.test.JbpmJUnitTestCase;
//
//import org.junit.Test;
//
//import org.kie.api.runtime.KieSession;
//import org.kie.api.runtime.process.ProcessInstance;
//import org.kie.api.task.TaskService;
//import org.kie.api.task.model.TaskSummary;
//
//public class AuditFlowJUnitTest extends JbpmJUnitTestCase {
//
//    public AuditFlowJUnitTest() {
//        super(true);
//    }
//
//	@Test
//    public void testImplicit() {
//        KieSession ksession = createKnowledgeSession("auditflow.bpmn");
//        TaskService taskService = getTaskService();
//        ProcessInstance processInstance = ksession.startProcess("auditflow");
//        // execute task
//        String actorId = "";
//        List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner(actorId, "en-UK");
//        TaskSummary task = list.get(0);
//        taskService.start(task.getId(), actorId);
//        Map<String, Object> results = new HashMap<String, Object>();
//        // add results here
//        taskService.complete(task.getId(), actorId, results);
//
//        // do your checks here
//        // for example, assertProcessInstanceCompleted(processInstance.getId(), ksession);
//    }
//
//}