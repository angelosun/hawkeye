package cn.angelo.hawkeye.admin.zookeeper;

import cn.angelo.hawkeye.admin.websocket.WebSocketServer;
import cn.angelo.hawkeye.core.zookeeper.ZkWatcher;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeChangeListener implements NodeCacheListener {
    public static final Logger LOG = LoggerFactory.getLogger(NodeChangeListener.class);


    @Override
    public void nodeChanged() throws Exception {
        //TODO get data for web presentation
        LOG.info("node changed notification");
        String data = ZkWatcher.getInstance().readData("/statistics/cpu");
        LOG.info("data : {}", data);
        if (StringUtils.isNotBlank(data)) {
            WebSocketServer.pushDataToWeb(data);
        }
    }
}