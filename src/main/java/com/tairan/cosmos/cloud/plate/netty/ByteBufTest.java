package com.tairan.cosmos.cloud.plate.netty;

import io.netty.buffer.*;

/**
 * Created by thomas.su on 2017/11/4 12:48.
 */
public class ByteBufTest {
    public static void main(String[] args){
       ByteBuf directBuf = Unpooled.directBuffer(16);
        directBuf.writeInt(10);
        directBuf.writeInt(8);
        if(directBuf.hasArray()){
            int len = directBuf.readableBytes();
            byte[] copy = new byte[len];

            directBuf.getBytes(len,copy);
            System.out.println("copy:"+copy);
        }

        System.out.println("directBuf readableBytes:"+directBuf.readableBytes());
        System.out.println("directBuf writableBytes:"+directBuf.writableBytes());
        System.out.println("directBuf"+directBuf);

    }
}
