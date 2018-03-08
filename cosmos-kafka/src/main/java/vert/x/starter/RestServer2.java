package vert.x.starter;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;


/**
 *
 *官方中文文档 https://vertxchina.github.io/vertx-translation-chinese
 * Created by thomas on 2018/2/28 10:41.
 */
public class RestServer2  extends AbstractVerticle{
    public static void main(String[] args) {
        // 获取vertx基类
        VertxOptions options = new VertxOptions();
        options.setBlockedThreadCheckInterval(4000);//阻塞线程检查的时间间隔，默认1000，单位ms，即1秒；
        options.setMaxEventLoopExecuteTime(4000000000l);//Event Loop的最大执行时间，默认2l * 1000 * 1000000，单位ns，即2秒；
        options.setWarningExceptionTime(60000000000l);//如果线程阻塞时间超过了这个阀值，那么就会打印警告的堆栈信息，默认为5l * 1000 * 1000000，单位ns，即5秒；
//		options.setMaxWorkerExecuteTime(10000000000l);//Worker线程的最大执行时间，默认60l * 1000 * 1000000，单位ns，即60秒；
        options.setWorkerPoolSize(3);//设置Vert.x实例中支持的Worker线程的最大数量，默认值为20；
        options.setInternalBlockingPoolSize(2);//设置内部阻塞线程池最大线程数，这个参数主要被Vert.x的一些内部操作使用，默认值为20；
        Vertx vertx = Vertx.vertx(options);
        // 部署发布rest服务
        vertx.deployVerticle(new RestServer2());
    }

    @Override
    public void start() {
        // 实例化一个路由器出来，用来路由不同的rest接口
        Router router =Router.router(vertx);
        // 增加一个处理器，将请求的上下文信息，放到RoutingContext中
        router.route().handler(BodyHandler.create());
        // 处理一个get方法的rest接口
        router.get("/get/:param1/:param2").handler(this::handleGet);

        router.get("/get2/:param1/:param2").handler(this::handleGet2);

        router.get("/get3/:param1/:param2").handler(this::handleGet4);

        // 创建一个httpserver，监听8080端口，并交由路由器分发处理用户请求
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

    }

    // 逻辑同GET方法
    private void handleGet(RoutingContext context) {
        String param1 = context.request().getParam("param1");
        String param2 = context.request().getParam("param2");

        if (isBlank(param1) || isBlank(param2)) {
            context.response().setStatusCode(400).end();
        }
        JsonObject obj = new JsonObject();
        obj.put("method", "get").put("param1", param1).put("param2", param2);

        context.response().putHeader("content-type", "application/json")
                .end(obj.encodePrettily());
    }

    // 逻辑同GET方法
    private JsonObject handleGet3(RoutingContext context) {
        String param1 = context.request().getParam("param1");
        String param2 = context.request().getParam("param2");

        if (isBlank(param1) || isBlank(param2)) {
            context.response().setStatusCode(400).end();
        }
        JsonObject obj = new JsonObject();
        obj.put("method", "get").put("param1", param1).put("param2", param2);

        return obj;
    }

    // 逻辑同GET方法
    private void handleGet2(RoutingContext context) {
        // 阻塞式接口调用 (有序执行)
        vertx.executeBlocking(future -> {
            // Call some blocking API that takes a significant amount of time to return
            JsonObject obj =handleGet3(context);

            future.complete(obj);

        }, res -> {
            System.out.println("The result is: " + res.result());
            JsonObject obj = (JsonObject) res.result();
//              obj.put("method", "get").put("param1", param1).put("param2", param2);

            context.response().putHeader("content-type", "application/json")
                    .end(obj.encodePrettily());
        });

    }

    // 逻辑同GET方法
    private void handleGet4(RoutingContext context) {
        // 阻塞式接口调用 (无序执行)
        vertx.executeBlocking(future -> {
            // Call some blocking API that takes a significant amount of time to return
            JsonObject obj =handleGet3(context);
            System.out.println(Thread.currentThread().getName());
            future.complete(obj);

        }, false,res -> {
            System.out.println("The result is: " + res.result());
            JsonObject obj = (JsonObject) res.result();
//              obj.put("method", "get").put("param1", param1).put("param2", param2);

            context.response().putHeader("content-type", "application/json")
                    .end(obj.encodePrettily());
        });

    }

    private boolean isBlank(String str) {
        if (str == null || "".equals(str))
            return true;
        return false;
    }

}
