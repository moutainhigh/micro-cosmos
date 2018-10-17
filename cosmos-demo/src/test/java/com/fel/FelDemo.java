package com.fel;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.common.ObjectUtils;
import com.greenpineyu.fel.context.ContextChain;
import com.greenpineyu.fel.context.FelContext;
import com.greenpineyu.fel.context.MapContext;
import com.greenpineyu.fel.function.CommonFunction;
import com.greenpineyu.fel.function.Function;
import org.junit.Test;

/**
 * @Author:thomas su
 * @Date: 2018/8/9 20:13
 * @Description:
 */
public class FelDemo {



//    自定义函数
    @Test
    public void test7(){
        //定义 hello 函数
        Function fun= new CommonFunction() {
                        public String getName() {
                            return "hello";
                        }
                    /** 调用 hello("xxx")时执行的代码
                     */
                        @Override
                        public Object call(Object[] arguments) {
                                Object msg= null;
                                if(arguments!= null && arguments.length>0){
                                    msg= arguments[0];
                                    }
                                    return ObjectUtils.toString(msg);
                            }
                };
            FelEngine e= new FelEngineImpl();
            //添加函数到引擎中。
            e.addFun(fun);
            String exp= "hello('fel')";
            //解释执行
            Object eval = e.eval(exp);
            System.out.println("hello "+eval);
            //编译执行
            Expression compile= e.compile(exp, null);
            eval = compile.eval(null);
            System.out.println("hello "+eval);
       }

        //编译执行
    @Test
    public void test6(){
        FelEngine fel= new FelEngineImpl();
        FelContext ctx= fel.getContext();
        ctx.set("单价", 5000);
        ctx.set("数量", 12);
        ctx.set("运费", 7500);
        Expression exp= fel.compile("单价*数量+运费",ctx);
        Object result= exp.eval(ctx);
        System.out.println(result);
    }

//    多层上下文环境（命名空间）
    @Test
    public void test5(){
        FelEngine fel= new FelEngineImpl();
        String costStr= "成本";
        String priceStr="价格";
        FelContext baseCtx= fel.getContext();
        //父级上下文中设置成本和价格
        baseCtx.set(costStr, 50);
        baseCtx.set(priceStr,100);
        String exp= priceStr+"-"+costStr;
        Object baseCost= fel.eval(exp);
        System.out.println("期望利润：" + baseCost);
        FelContext ctx= new ContextChain(baseCtx, new MapContext());
        //通货膨胀导致成本增加（子级上下文 中设置成本，会覆盖父级上下文中的成本）
        ctx.set(costStr,50+20 );
        Object allCost= fel.eval(exp, ctx);
        System.out.println("实际利润：" + allCost);
    }



//    自定义上下文环境
    @Test
    public void test4(){
        //负责提供气象服务的上下文环境
//        FelContext ctx= new AbstractConetxt() {
//            public Object get(Object name) {
//                if("天气".equals(name)){
//                    return "晴";
//                }
//                if("温度".equals(name)){
//                    return 25;
//                }
//                return null;
//            }
//        };
//        FelEngine fel= new FelEngineImpl(ctx);
//        Object eval = fel.eval("'天气:'+天气+';温度:'+温度");
//        System.out.println(eval);
    }


//    调用 JAVA 方法
    @Test
    public void test3(){
        FelEngine fel= new FelEngineImpl();
        FelContext ctx= fel.getContext();
        ctx.set("out", System.out);
        fel.eval("out.println('Hello Everybody'.substring(6))");
    }

//    变量
    @Test
    public void test2(){
        FelEngine fel= new FelEngineImpl();
        FelContext ctx= fel.getContext();
        ctx.set("单价", 5000);
        ctx.set("数量", 12);
        ctx.set("运费", 7500);
        Object result= fel.eval("单价*数量+运费");
        System.out.println(result);
    }


    //算术表达式
    @Test
    public void test1(){
        FelEngine fel= new FelEngineImpl();
        Object result= fel.eval("5000*12+7500");
        System.out.println(result);
    }


}
