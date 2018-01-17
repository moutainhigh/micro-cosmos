package com.cosmos.plate.zkclient.bridge;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * Created by thomas.su on 2018/1/16 15:21.
 */
public class PathCacheDemo {
    private static final String path = "/example/pathCache";

    public static void main(String[] args) throws Exception {
        String connectedString = null;
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectedString, new ExponentialBackoffRetry(1000, 3));
        client.start();
        PathChildrenCache cache = new PathChildrenCache(client, path, true);
        cache.start();
        PathChildrenCacheListener cacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("事件类型：" + pathChildrenCacheEvent.getType());
                if (pathChildrenCacheEvent.getData() != null) {
                    System.out.println("节点路径：" + pathChildrenCacheEvent.getData().getPath());
                    System.out.println("节点路径：" + pathChildrenCacheEvent.getData().getData());
                }
            }
        };

        cache.getListenable().addListener(cacheListener);
        client.create().creatingParentsIfNeeded().forPath("/example/pathCache/test01", "01".getBytes());
        client.create().creatingParentsIfNeeded().forPath("/example/pathCache/test02", "02".getBytes());

        client.setData().forPath("/example/pathCache/test01", "01_v1".getBytes());
        List<ChildData> cacheDataList = cache.getCurrentData();
        for (ChildData childData : cacheDataList) {
            System.out.println(childData.getData());
            System.out.println(childData.getPath());
        }

        client.delete().forPath("/example/pathCache/test01");
        client.delete().forPath("/example/pathCache/test02");

        cache.close();
        ;
        client.close();
        System.out.println("OK");


    }

}
