package com.tairan.cosmos.cloud.plate.netty;

/**
 * Created by thomas.su on 2017/11/4 15:24.
 */
public class EchoTest {
    public static void main(String[] args) throws Exception{
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        new EchoClient().connect(port, "127.0.0.1");
    }
}
