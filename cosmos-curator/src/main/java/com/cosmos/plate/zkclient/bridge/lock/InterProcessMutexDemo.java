package com.cosmos.plate.zkclient.bridge.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by thomas.su on 2018/1/17 11:21.
 */
public class InterProcessMutexDemo {
    private InterProcessMutex lock;
    private final FakeLimitedResource resource;
    private final String clientName;

    public InterProcessMutexDemo(CuratorFramework client, String path, FakeLimitedResource resource, String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessMutex(client, path);
    }

    public void doWork(long time, TimeUnit unit) throws Exception {
        if (lock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + "could not acquire the lock");
        }
        try {
            resource.inUse();
        } finally {
            lock.release();
        }
    }

    private static final int QTY = 5;
    private static final int PERETITIONS = QTY * 10;
    private static final String PATH = "/examples/locks";


    public static void main(String[] args) throws Exception {
        final TestingServer server = new TestingServer();
        final FakeLimitedResource resource = new FakeLimitedResource();
        ExecutorService service = Executors.newFixedThreadPool(QTY);
        try {
            for (int i = 0; i < QTY; i++) {
                final int j = i;
                Callable<Void> callable = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
                        client.start();
                        InterProcessMutexDemo example = new InterProcessMutexDemo(client, PATH, resource, "ClientName:" + j);
                        try {
                            for (int j = 0; j < PERETITIONS; ++j) {
                                example.doWork(10, TimeUnit.SECONDS);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            CloseableUtils.closeQuietly(client);
                        }
                        return null;
                    }
                };
                service.submit(callable);
            }

            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);
        } finally {
            CloseableUtils.closeQuietly(server);
        }


    }


}
