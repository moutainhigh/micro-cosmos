package com.cosmos.plate.zkclient.bridge;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.Participant;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.List;

/**
 * Created by thomas.su on 2018/1/17 9:53.
 */
public class LeaderLatchDemo {

    private static final String path = "/example/path";
    private static final int CLIENT_QTY = 10;

    public static void main(String[] args) throws Exception {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> examples = Lists.newArrayList();

        String connectString = "";
        try {
            for (int i = 0; i < CLIENT_QTY; i++) {
                CuratorFramework client = CuratorFrameworkFactory.newClient(connectString,
                        new ExponentialBackoffRetry(1000, 3));
                clients.add(client);

                final LeaderLatch latch = new LeaderLatch(client, path, "Client # " + i);
                latch.addListener(new LeaderLatchListener() {
                    @Override
                    public void isLeader() {
                        System.out.println("i am leader");
                        boolean hasLeaderShip = latch.hasLeadership();
                    }

                    @Override
                    public void notLeader() {
                        System.out.println("i am not leader");
                    }
                });
                examples.add(latch);
                client.start();
                latch.start();
            }

            LeaderLatch curretLatch = null;
            for (LeaderLatch latch : examples) {
                if (latch.hasLeadership()) {
                    curretLatch = latch;
                }
            }

            System.out.println("current leader: " + curretLatch.getId());
            curretLatch.close();

            for (LeaderLatch latch : examples) {
                if (latch.hasLeadership()) {
                    curretLatch = latch;
                }
            }

            System.out.println("current leader:" + curretLatch.getId());
            Participant participant = curretLatch.getLeader();

        } finally {
            for (LeaderLatch latch : examples) {
                if (latch.getState() != null) {
                    CloseableUtils.closeQuietly(latch);
                }
            }

            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }

        }


    }
}
