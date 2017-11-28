package com.waylau.netty.demo.codec.jackcon;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by thomas.su on 2017/11/28 19:16.
 */
public class JacksonEncoder extends MessageToByteEncoder<JacksonBean> {
    @Override
    protected void encode(ChannelHandlerContext ctx, JacksonBean msg, ByteBuf out) throws Exception {
        ByteBufOutputStream byteBufOutputStream = new ByteBufOutputStream(out);

        ObjectMapper objectMapper = JacksonMapper.getInstance();
        objectMapper.writeValue(byteBufOutputStream, msg);
    }
}
