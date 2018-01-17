package com.cosmos.plate.zkclient.bridge.lock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by thomas.su on 2018/1/17 11:15.
 */
public class FakeLimitedResource {
    private final AtomicBoolean inUse = new AtomicBoolean(false);

    public void inUse() throws InterruptedException {
        //真实环境中 我们会在这里访问、维护一个共享的资料
        //这个例子在使用锁的情况下，不会发生并发异常的IllegalStateException
        //但是在无锁的情况下，由于sleep了一段时间，很容易抛出异常
        if (!inUse.compareAndSet(false, true)) {
            throw new IllegalStateException("Needs to be used by one client at a time");
        }
        try {
            Thread.sleep(1000);
        } finally {
            inUse.set(false);
        }
    }
}
