package com.tairan.cosmos.cloud.plate.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Created by thomas.su on 2017/11/4 15:11.
 */
public class EchoClient {
    private final static int readerIdleTimeSeconds = 40;//读操作空闲40秒
    private final static int writerIdleTimeSeconds = 50;//写操作空闲50秒

    private final static int allIdleTimeSeconds = 100;//读写全部空闲100秒

    public void connect(int port,String host) throws  Exception{
        EventLoopGroup group = new NioEventLoopGroup();

        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(
                            new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel ch) throws Exception {
                                    ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                                    ch.pipeline().addLast("idleStateHandler",new IdleStateHandler(readerIdleTimeSeconds,writerIdleTimeSeconds,allIdleTimeSeconds));
                                    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(10224,delimiter));
                                    ch.pipeline().addLast(new StringDecoder());
                                    ch.pipeline().addLast(new EchoHandler());

                                }
                            }
                    );

            //发起异步连接操作
            ChannelFuture channelFuture = bootstrap.connect(host,port).sync();
            //
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            group.shutdownGracefully();
        }
    }
}
