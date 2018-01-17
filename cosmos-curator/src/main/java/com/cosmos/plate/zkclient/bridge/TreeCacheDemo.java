package com.cosmos.plate.zkclient.bridge;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * Created by thomas.su on 2018/1/16 16:12.
 */
public class TreeCacheDemo {

    private static final String path = "/example/path";
    private static final String connectionString = "";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, new ExponentialBackoffRetry(1000, 3));
        client.start();
        TreeCache treeCache = new TreeCache(client, path);
        TreeCacheListener treeCacheListener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                TreeCacheEvent.Type type = event.getType();
                if (TreeCacheEvent.Type.CONNECTION_LOST.equals(type)) {

                } else if (TreeCacheEvent.Type.CONNECTION_RECONNECTED.equals(type)) {

                } else if (TreeCacheEvent.Type.CONNECTION_SUSPENDED.equals(type)) {

                }

                TreeCacheEvent.Type[] values = TreeCacheEvent.Type.values();
                //...

                ChildData childData = event.getData();
                childData.getData();
                childData.getPath();
                Stat stat = childData.getStat();

            }
        };

        treeCache.getListenable().addListener(treeCacheListener);
        //TreeCache在初始化(调用start()方法)的时候会回调TreeCacheListener实例一个事TreeCacheEvent，
        // 而回调的TreeCacheEvent对象的Type为INITIALIZED，ChildData为null，
        // 此时event.getData().getPath()很有可能导致空指针异常，这里应该主动处理并避免这种情况。
        treeCache.start();
        client.setData().forPath(path, "01".getBytes());
        client.setData().forPath(path, "02".getBytes());
        client.delete().deletingChildrenIfNeeded().forPath(path);

        treeCache.close();
        client.close();

    }

    public static void test() {
        ConnectionStateListener stateListener = new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                boolean state = newState.isConnected();
            }
        };
    }
}
