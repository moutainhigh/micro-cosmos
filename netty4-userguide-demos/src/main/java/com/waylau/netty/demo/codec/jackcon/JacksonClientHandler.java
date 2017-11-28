package com.waylau.netty.demo.codec.jackcon;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by thomas.su on 2017/11/28 19:25.
 */
public class JacksonClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        String jsonString = "";
        if (msg instanceof JacksonBean) {
            JacksonBean user = (JacksonBean) msg;
            jsonString = JacksonMapper.getInstance().writeValueAsString(user);
        } else {
            JacksonMapper.getInstance().writeValueAsString(msg);
        }
        System.out.println("client get msg from server: " + jsonString);
    }
}
