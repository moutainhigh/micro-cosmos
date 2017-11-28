package com.waylau.netty.demo.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by thomas.su on 2017/11/28 21:49.
 */
public class DiscardServerHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;

        try {
            while (in.readableBytes() > 0) {
                System.out.println((char) in.readByte());
                System.out.flush();
            }
        } catch (Exception e) {

        } finally {
            ReferenceCountUtil.release(msg);
        }

    }
}
