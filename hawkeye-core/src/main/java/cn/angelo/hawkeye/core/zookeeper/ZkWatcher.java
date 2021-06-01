package cn.angelo.hawkeye.core.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ZkWatcher {

    public static final Logger LOG = LoggerFactory.getLogger(ZkWatcher.class);

    public static ZkWatcher instance = new ZkWatcher();

    private CuratorFramework client;

    private String zkAddr;

    private String zkPath;

    private ZkWatcher() {}

    public static ZkWatcher getInstance() {
        return instance;
    }

    public void connection() throws Exception {
        client = CuratorFrameworkFactory.newClient(this.zkAddr, new RetryNTimes(10, 5000));
        client.start();
    }

    public void release() {
        if (null != client) {
            this.client.getZookeeperClient().close();
        }
    }

    public void addListener(String path, NodeCacheListener nodeCacheListener) {
        CuratorCache curatorCache = CuratorCache.builder(client, path).build();
        CuratorCacheListener listener = CuratorCacheListener
                .builder()
                .forNodeCache(nodeCacheListener)
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

    public String getZkAddr() {
        return zkAddr;
    }

    public void setZkAddr(String zkAddr) {
        this.zkAddr = zkAddr;
    }

    public String getZkPath() {
        return zkPath;
    }

    public void setZkPath(String zkPath) {
        this.zkPath = zkPath;
    }
}
