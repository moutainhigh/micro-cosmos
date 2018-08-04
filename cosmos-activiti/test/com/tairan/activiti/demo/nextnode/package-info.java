/**
 * @Author:thomas su
 * @Date: 2018/8/4 9:55
 * @Description: activiti根据当前节点获取下一个UseTask节点
 */
package com.tairan.activiti.demo.nextnode;

/***
 以前写过一篇文章activiti根据当前节点获取下一个节点信息，
 当时的需求只是需要获取下一个节点而已，并没有要求获得什么类型的节点，
 所以下一个节点可能是任何节点，在最近的项目中的需求是根据当前的节点获取下一个UseTask节点，
 为当前审批人选择下一个审批人提示，所以前面的文章所给出的信心就不能够实现当前功能。

 所以本文所述为获取下一个任务节点。根据上一篇文章，可以获取到下一个节点的信息，
 如果我们获取到的节点是Exclusive Gateway，那么再根据这个节点再往下查询直到
 下一个节点是useTask为止，那么遇到GateWay就需要根据条件对流程进行判断，
 获取el表达式，再根据条件得到流程走的是哪一条线。

 https://blog.csdn.net/chenfengdejuanlian/article/details/77063814

 **/