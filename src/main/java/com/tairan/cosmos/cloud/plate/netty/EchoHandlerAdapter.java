package com.tairan.cosmos.cloud.plate.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by thomas.su on 2017/11/4 15:25.
 * http://blog.csdn.net/linuu/article/details/51509847
 */
public class EchoHandlerAdapter extends ChannelInboundHandlerAdapter {
    private  int counter;
    private  static final String ECHO_REQ = "hi,boy,welcome to netty.$_";

    public EchoHandlerAdapter(){}


}
