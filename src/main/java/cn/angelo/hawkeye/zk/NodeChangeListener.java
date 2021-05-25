package cn.angelo.hawkeye.zk;

import org.apache.curator.framework.recipes.cache.NodeCacheListener;

public class NodeChangeListener implements NodeCacheListener {
    @Override
    public void nodeChanged() throws Exception {
        //TODO get data for web presentation
    }
}
