package cn.angelo.hawkeye.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class ZkWatcher {

    public static final Logger LOG = LoggerFactory.getLogger(ZkWatcher.class);

    private CuratorFramework client;

    private CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private String zkAddr;

    public ZkWatcher(String zkAddr) {
        this.zkAddr = zkAddr;
    }

    public void connection() throws Exception {
        client = CuratorFrameworkFactory.newClient(this.zkAddr, new RetryNTimes(10, 5000));
        client.start();
        connectedSemaphore.await();
    }

    public void release() throws Exception {
        if (null != client) {
            this.client.getZookeeperClient().close();
            connectedSemaphore.countDown();
        }
    }

    public void getCuratorCache(String path) {
        CuratorCache curatorCache = CuratorCache.builder(client, path).build();
        CuratorCacheListener listener = CuratorCacheListener
                .builder()
                .forNodeCache(new NodeCacheListener() {
                    public void nodeChanged() throws Exception {
                        //TODO ...
                    }
                })
                .build();
        curatorCache.listenable().addListener(listener);
        curatorCache.start();
    }




    public void writeData(String path, String data) throws Exception {
        client.create().orSetData().creatingParentsIfNeeded().forPath(path, data.getBytes());
    }

    public String readData(String path) throws Exception {
        byte[] dataBytes = client.getData().forPath(path);
        return (dataBytes == null || dataBytes.length == 0) ? null : new String(dataBytes);
    }

    public void deletePath(String path) throws Exception {
        client.delete().quietly().deletingChildrenIfNeeded().forPath(path);
    }


}
