package com.yichen.cosmos.cloud.platform;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author:thomas
 * @Date: 2018/7/2 15:27
 * @Description:
 */
public class SampleClass {

    public void test(){
        System.out.println("hello world");
    }

    public static void main(String[] args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before method run...");
//                Object result = methodProxy.invoke(o,objects);
                Object result = methodProxy.invokeSuper(o,objects);
                System.out.println("after method run....");
                return result;
            }
        });


        SampleClass sampleClass = (SampleClass)enhancer.create();
        sampleClass.test();

    }


}
