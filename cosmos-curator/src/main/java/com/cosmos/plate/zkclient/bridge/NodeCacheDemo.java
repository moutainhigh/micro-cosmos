package com.cosmos.plate.zkclient.bridge;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by thomas.su on 2018/1/16 16:03.
 */
public class NodeCacheDemo {
    private static final String path = "/example/cache";
    private static final String connectionString = "";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, new ExponentialBackoffRetry(1000, 3));
        client.start();
        final NodeCache cache = new NodeCache(client, path);
        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                ChildData childData = cache.getCurrentData();
                if (childData != null) {
                    childData.getPath();
                    childData.getData();
                } else {
                    System.out.println("节点已经被删除");
                }
            }
        };

        cache.getListenable().addListener(nodeCacheListener);
        cache.start();

        client.setData().forPath(path, "01".getBytes());
        client.setData().forPath(path, "02".getBytes());

        client.delete().deletingChildrenIfNeeded().forPath(path);

        cache.close();
        client.close();


    }
}
