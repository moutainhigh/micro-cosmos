package com.cosmos.plate.zkclient.bridge;

import org.I0Itec.zkclient.IZkConnection;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by thomas.su on 2018/1/16 11:43.
 */
public class CuratorZKClientBridge implements IZkConnection {

    private final CuratorFramework curator;
    private final AtomicReference<CuratorListener> listener = new AtomicReference<CuratorListener>(null);

    public CuratorZKClientBridge(CuratorFramework curator) {
        this.curator = curator;
    }


    public CuratorFramework getCurator() {
        return curator;
    }


    @Override
    public void connect(final Watcher watcher) {
        if (watcher != null) {
            CuratorListener localListener = new CuratorListener() {
                @Override
                public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                    if (event.getWatchedEvent() != null) {
                        watcher.process(event.getWatchedEvent());
                    }
                }
            };
            curator.getCuratorListenable().addListener(localListener);

            try {

                BackgroundCallback callback = new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        WatchedEvent fakeEvent
                                = new WatchedEvent(
                                Watcher.Event.EventType.None,
                                curator.getZookeeperClient().isConnected() ?
                                        Watcher.Event.KeeperState.SyncConnected :
                                        Watcher.Event.KeeperState.Disconnected,
                                null
                        );
                        watcher.process(fakeEvent);
                    }
                };

                curator.checkExists().inBackground(callback).forPath("/foo");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }


    }

    @Override
    public void close() throws InterruptedException {
        CuratorListener localListener = listener.getAndSet(null);
        if (localListener != null) {
            curator.getCuratorListenable().removeListener(localListener);
        }
    }

    @Override
    public String create(String path, byte[] data, CreateMode mode) throws KeeperException, InterruptedException {

        try {
            curator.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
            return curator.create().withMode(mode).forPath(path, data);

        } catch (Exception e) {
            adjustException(e);
        }
        return null;
    }

    private void adjustException(Exception e) throws KeeperException, InterruptedException {
        if (e instanceof KeeperException) {
            throw (KeeperException) e;
        } else if (e instanceof InterruptedException) {
            throw (InterruptedException) e;
        }
        throw new RuntimeException(e);
    }

    @Override
    public void delete(String path) throws InterruptedException, KeeperException {
        try {
            curator.delete().deletingChildrenIfNeeded().forPath(path);
            curator.delete().withVersion(10086).forPath(path);
            curator.delete().guaranteed().forPath(path);
            curator.delete().forPath(path);

            curator.delete().guaranteed().deletingChildrenIfNeeded().withVersion(10086).forPath(path);
        } catch (Exception e) {
            adjustException(e);
        }
    }

    @Override
    public boolean exists(String path, boolean watch) throws KeeperException, InterruptedException {

        try {
            curator.checkExists().forPath(path);

        } catch (Exception e) {

        }


        return false;
    }

    @Override
    public List<String> getChildren(String path, boolean watch) throws KeeperException, InterruptedException {
        return null;
    }

    @Override
    public byte[] readData(String path, Stat stat, boolean watch) throws KeeperException, InterruptedException {

        try {
            curator.getData().forPath(path);
            curator.getData().storingStatIn(stat).forPath(path);
        } catch (Exception e) {

        }


        return new byte[0];
    }

    @Override
    public void writeData(String path, byte[] data, int expectedVersion) throws KeeperException, InterruptedException {
        try {
            curator.setData().forPath(path, data);
        } catch (Exception e) {

        }
    }

    @Override
    public Stat writeDataReturnStat(String path, byte[] data, int expectedVersion) throws KeeperException, InterruptedException {
        return null;
    }

    @Override
    public ZooKeeper.States getZookeeperState() {
        return null;
    }

    @Override
    public long getCreateTime(String path) throws KeeperException, InterruptedException {
        return 0;
    }

    @Override
    public String getServers() {
        return null;
    }
}
