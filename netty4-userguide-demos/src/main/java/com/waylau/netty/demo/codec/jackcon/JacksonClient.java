package com.waylau.netty.demo.codec.jackcon;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thomas.su on 2017/11/28 19:38.
 */
public class JacksonClient {
    public static void main(String[] args) throws Exception {

    }

    private final String host;
    private final int port;

    public JacksonClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new JacksonClientHandlerInitializer());

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();

            JacksonBean user = new JacksonBean();
            user.setAge(27);
            user.setName("waylau");
            List<String> sons = new ArrayList<String>();
            for (int i = 0; i < 10; i++) {
                sons.add("Lucy" + i);
                sons.add("Lily" + i);
            }
            user.setSons(sons);
            Map<String, String> addrs = new HashMap<String, String>();
            for (int i = 0; i < 10; i++) {
                addrs.put("001" + i, "18998366112");
                addrs.put("002" + i, "15014965012");
            }
            user.setAddrs(addrs);

            channel.writeAndFlush(user);
            channel.closeFuture().sync();
        } catch (Exception e) {

        } finally {
            group.shutdownGracefully();
        }


    }
}
