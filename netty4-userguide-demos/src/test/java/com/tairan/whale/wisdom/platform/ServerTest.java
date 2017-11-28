package com.tairan.whale.wisdom.platform;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * Created by thomas.su on 2017/11/23 13:18.
 */
public class ServerTest {
    public static void main(String[] args) {
        EventLoopGroup boostGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        String host = "127.0.0.1";
        int port = 8090;
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boostGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 64)//
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ChannelPipeline pipline = ch.pipeline();
                            pipline.addLast(new StringDecoder());
                            pipline.addLast(new StringEncoder());
                            pipline.addLast(new LineBasedFrameDecoder(1024));
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(host, port).sync();
            channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boostGroup.shutdownGracefully()
            ;
            workGroup.shutdownGracefully();
        }
    }
}
