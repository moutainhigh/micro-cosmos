import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author:thomas
 * @Date: 2018/7/2 17:49
 * @Description:
 */

public class CGLIBTest {

    public void test(){
        System.out.println("hello world!");
    }

    @Test
    public void testFixedValue(){
        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(this.getClass());
        enhancer.setSuperclass(CGLIBTest.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                Object result = methodProxy.invokeSuper(o,objects);

                return result;
            }
        });

//        enhancer.c
    }
    @Test
    public void testEmpty(){
        String sss = "   ";
        System.out.println("aa"+sss+"bb");
        System.out.println("aa"+sss.trim()+"bb");
    }
}
