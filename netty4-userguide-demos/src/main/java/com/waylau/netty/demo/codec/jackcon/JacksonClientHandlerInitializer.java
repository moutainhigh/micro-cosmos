package com.waylau.netty.demo.codec.jackcon;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * Created by thomas.su on 2017/11/28 19:29.
 */
public class JacksonClientHandlerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new JacksonDecoder<JacksonBean>(JacksonBean.class));
        pipeline.addLast(new JacksonEncoder());
        pipeline.addLast(new JacksonClientHandler());
    }
}
