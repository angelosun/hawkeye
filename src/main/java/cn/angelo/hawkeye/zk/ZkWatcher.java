package cn.angelo.hawkeye.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.CountDownLatch;

public class ZkWatcher {

    private final String ZK_ADDRESS = "1.117.90.230:2181";

    private CuratorFramework client;

    private CuratorCache cache;

    private CountDownLatch connectedSemaphore = new CountDownLatch(1);


    public void connection() throws Exception {
        client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, new RetryNTimes(10, 5000));
        client.start();
        connectedSemaphore.await();
    }

    public void release() {

    }


    public void watch() {}

    public void writeData() {

    }



}
