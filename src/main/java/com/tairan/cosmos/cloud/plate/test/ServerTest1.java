package com.tairan.cosmos.cloud.plate.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Created by thomas.su on 2017/11/21 20:46.
 */
public class ServerTest1 {

    public static void main(String[] args) {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
//        ServerBootstrapConfig


        try {
            bootstrap.group(boosGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childHandler(
                            new ChannelInitializer<Channel>() {
                                @Override
                                protected void initChannel(Channel ch) throws Exception {
                                    ch.pipeline()
                                            .addLast(new ProtobufVarint32FrameDecoder());
//                                    ch.pipeline()
//                                            .addLast(new ProtobufDecoder(MsgProto.Packet.getDefaultInstance()));
                                    ch.pipeline()
                                            .addLast(new ProtobufVarint32LengthFieldPrepender());
                                    ch.pipeline().addLast(new ProtobufEncoder());
                                    ch.pipeline()
                                            .addLast(new ServerChannelHandlerAdapter());

                                }
                            }
                    );

            ChannelFuture future = bootstrap.bind(2015).sync();
            //等待关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
