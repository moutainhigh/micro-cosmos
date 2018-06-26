package org.activiti.drools.infos;

/*import org.activiti.engine.impl.pvm.PvmProcessDefinition;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.rules.RulesHelper;
import org.drools.core.marshalling.impl.ProtobufMessages;

*//**
 * @Author:thomas
 * @Date: 2018/6/21 20:49
 * @Description:
 * https://my.oschina.net/u/2886096/blog/1819094
 *//*
public class BusinessRuleTask_EX {

    public void execute(ActivityExecution execution) throws Exception {
        PvmProcessDefinition processDefinition = execution.getActivity().getProcessDefinition();
        String deploymentId = processDefinition.getDeploymentId();

        KnowledgeBase knowledgeBase = RulesHelper.findKnowledgeBaseByDeploymentId(deploymentId);
        StatefulKnowledgeSession ksession = knowledgeBase.newStatefulKnowledgeSession();


        if (variablesInputExpressions != null) {
            Iterator<Expression> itVariable = variablesInputExpressions.iterator();
            while (itVariable.hasNext()) {
                Expression variable = itVariable.next();
                Object obj=variable.getValue(execution);
                if(obj instanceof IRuleResultBase){
                    //设置全局变量
                    ksession.setGlobal("ruleResultBase", obj);
                }
                ksession.insert(variable.getValue(execution));
            }
        }

        if (!rulesExpressions.isEmpty()) {
            RulesAgendaFilter filter = new RulesAgendaFilter();
            Iterator<Expression> itRuleNames = rulesExpressions.iterator();
            while (itRuleNames.hasNext()) {
                Expression ruleName = itRuleNames.next();
                filter.addSuffic(ruleName.getValue(execution).toString());
            }
            filter.setAccept(!exclude);
            ksession.fireAllRules(filter);

        } else {
            ksession.fireAllRules();
        }*/
        //客户化 结果bean
   /* Collection<Object> ruleOutputObjects = ksession.getObjects();
    if (ruleOutputObjects != null && !ruleOutputObjects.isEmpty()) {
      Collection<Object> outputVariables = new ArrayList<Object>();
      for (Object object : ruleOutputObjects) {
        outputVariables.add(object);
      }
      execution.setVariable(resultVariable, outputVariables);
    }*/

    /*ruleResultBase=(RuleResultBase) ksession.getGlobal("ruleResultBase");
    Collection<Object> outputVariables = new ArrayList<Object>();
    outputVariables.add(ruleResultBase);
    execution.setVariable(resultVariable, ruleResultBase);*/
/*        execution.setVariable(resultVariable, ksession.getGlobal("ruleResultBase"));
        ksession.dispose();
        leave(execution);
    }


}*/
//
//}