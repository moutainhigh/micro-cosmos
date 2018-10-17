package com.yichen.cosmos.cloud.platform.enums;

/**
 * @Author:thomas su
 * @Date: 2018/7/26 15:40
 * @Description: 流程节点类型
 */
public enum NodeTypeEnum {

    START("开始节点"),
    END("终止节点"),
    RULE("规则节点"),
    SPLIT("分歧节点"),//排他网关  ExlusiveGateWay
    JOIN("合并节点"),//没用到，会去掉
    ;
    private String desc;
    NodeTypeEnum(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static boolean  isRightType(String nodeType){
        boolean isRight = false;
        for(NodeTypeEnum nodeTypeEnum: NodeTypeEnum.values()){
            if(nodeTypeEnum.name().equals(nodeType)){
                isRight = true;
                break;
            }
        }
        return isRight;
    }
}
