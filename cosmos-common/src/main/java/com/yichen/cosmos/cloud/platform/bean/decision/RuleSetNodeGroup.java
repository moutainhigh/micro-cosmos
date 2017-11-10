package com.yichen.cosmos.cloud.platform.bean.decision;

/**
 * Created by hzzt on 2017/10/9.
 */
public class RuleSetNodeGroup extends WorkFlow {
    private String groupName;
    private Object startNode;
    private Object endNode;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Object getStartNode() {
        return startNode;
    }

    public void setStartNode(Object startNode) {
        this.startNode = startNode;
    }

    public Object getEndNode() {
        return endNode;
    }

    public void setEndNode(Object endNode) {
        this.endNode = endNode;
    }
}
