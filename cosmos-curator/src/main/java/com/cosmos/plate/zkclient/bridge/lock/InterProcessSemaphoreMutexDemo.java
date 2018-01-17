package com.cosmos.plate.zkclient.bridge.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by thomas.su on 2018/1/17 13:41.
 */
public class InterProcessSemaphoreMutexDemo {
    private InterProcessSemaphoreMutex lock;
    private final FakeLimitedResource resource;
    private final String clientName;

    public InterProcessSemaphoreMutexDemo(
            CuratorFramework client,
            String path,
            FakeLimitedResource resource,
            String clientName
    ) {
        lock = new InterProcessSemaphoreMutex(client, path);
        this.clientName = clientName;
        this.resource = resource;
    }

    public void doWork(long time, TimeUnit timeUnit) throws Exception {
        try {
            if (!lock.acquire(time, timeUnit)) {
                throw new IllegalStateException(clientName + " 不能获取互斥锁");
            }
            System.out.println(clientName + "已经获取互斥锁");

            if (!lock.acquire(time, timeUnit)) {
                throw new IllegalStateException(clientName + " 不能获取互斥锁");
            }
            System.out.println(clientName + "已经获取互斥锁");

            resource.inUse();

        } finally {
            lock.release();
            lock.release();
        }
    }

    private static final int QTY = 5;
    private static final int REPETITIONS = QTY * 5;
    private static final String PATH = "/examples/path";


    public static void main(String[] args) throws Exception {
        final FakeLimitedResource resource = new FakeLimitedResource();
        ExecutorService service = Executors.newFixedThreadPool(QTY);

        final TestingServer server = new TestingServer();

        try {
            for (int i = 0; i < QTY; i++) {
                final int j = i;
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        CuratorFramework client =
                                CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
                        try {
                            client.start();
                            InterProcessMutexDemo demo = new InterProcessMutexDemo(client, PATH, resource, "ClientName:" + j);
                            demo.doWork(1000, TimeUnit.MINUTES);
                        } catch (Exception e) {

                        } finally {
                            CloseableUtils.closeQuietly(client);
                        }
                        return null;
                    }
                };

                service.submit(task);
            }

            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);
        } finally {
            CloseableUtils.closeQuietly(server);
        }

    }


}
