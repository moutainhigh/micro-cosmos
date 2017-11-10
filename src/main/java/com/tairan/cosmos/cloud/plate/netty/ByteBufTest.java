package com.tairan.cosmos.cloud.plate.netty;

import io.netty.buffer.*;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by thomas.su on 2017/11/4 12:48.
 */
public class ByteBufTest {
    public static void main(String[] args){
            test1();
    }

    public static void  test2(){
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer(20);
        ByteBuf heapBuf = Unpooled.buffer(10);
        ByteBuf directBuf = Unpooled.directBuffer(15);

        compositeByteBuf.addComponents(heapBuf,directBuf);
        compositeByteBuf.removeComponent(0);
        Iterator<ByteBuf> iter = compositeByteBuf.iterator();
        while(iter.hasNext()){
            iter.next().toString();
        }
    }


    public static void test1(){
        ByteBuf directBuf = Unpooled.directBuffer(16);
        directBuf.writeInt(10);
        directBuf.writeInt(8);
        if(!directBuf.hasArray()){
            int len = directBuf.readableBytes();
            byte[] copy = new byte[len];

            directBuf.getBytes(directBuf.readerIndex(),copy);
            System.out.println("copy:"+new String(copy));
        }

        System.out.println("directBuf readableBytes:"+directBuf.readableBytes());
        System.out.println("directBuf writableBytes:"+directBuf.writableBytes());
        System.out.println("directBuf"+directBuf);

//        Netty心跳之IdleStateHandler
//        IdleStateHandler
        new IdleStateHandler(5,0,0, TimeUnit.SECONDS);

    }
}
