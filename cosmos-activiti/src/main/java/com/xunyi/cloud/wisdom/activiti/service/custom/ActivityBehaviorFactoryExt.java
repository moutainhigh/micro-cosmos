package com.xunyi.cloud.wisdom.activiti.service.custom;

/**
 * @Author:thomas su
 * @Date: 2018/7/28 17:36
 * @Description:
 * 我们通过继续改写了这个分支节点的跳出机制，
 * 并且通过脚本引擎来执行其条件分支的判断处理，
 * 但流程引擎并不了解我们扩展的类，这时我们需要配置Activiti流程引擎的行为动作工厂类，如下所示：
 */

/**
 * 扩展缺省的流程节点默认工厂类，实现对Activiti节点的执行的默认行为的更改
 * @author keitch
 *
 */
/*public class ActivityBehaviorFactoryExt extends DefaultActivityBehaviorFactory {

    private ExclusiveGatewayActivityBehaviorExt exclusiveGatewayActivityBehaviorExt;

    *//**
     * 通过Spring容器注入新的分支条件行为执行类
     * @param exclusiveGatewayActivityBehaviorExt
     *//*
    public void setExclusiveGatewayActivityBehaviorExt(ExclusiveGatewayActivityBehaviorExt exclusiveGatewayActivityBehaviorExt) {
        this.exclusiveGatewayActivityBehaviorExt = exclusiveGatewayActivityBehaviorExt;
    }

    //重写父类中的分支条件行为执行类
    @Override
    public ExclusiveGatewayActivityBehavior createExclusiveGatewayActivityBehavior(ExclusiveGateway exclusiveGateway) {
        return exclusiveGatewayActivityBehaviorExt;
    }
}*/
