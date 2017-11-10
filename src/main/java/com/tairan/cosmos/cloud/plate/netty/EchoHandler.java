package com.tairan.cosmos.cloud.plate.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by thomas.su on 2017/11/4 15:25.
 * http://blog.csdn.net/linuu/article/details/51509847
 */
public class EchoHandler implements ChannelInboundHandler {
    private  int counter;
    private  static final String ECHO_REQ = "hi,boy,welcome to netty.$_";

    public EchoHandler(){}


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i< 10; i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is " + ++counter + " times receive server : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if(IdleStateEvent.class.isAssignableFrom(evt.getClass())){
                IdleStateEvent event = (IdleStateEvent)evt;
                if(event.state() == IdleState.READER_IDLE){
                    System.out.println("read idle");
                }else if(event.state() == IdleState.WRITER_IDLE){
                    System.out.println("write idle");
                }else if(event.state() == IdleState.ALL_IDLE){
                    System.out.println("all idle");
                }
            }
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {

    }
}
