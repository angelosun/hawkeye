package cn.angelo.hawkeye.admin.zookeeper;

import cn.angelo.hawkeye.admin.websocket.WebSocketServer;
import cn.angelo.hawkeye.core.model.Constant;
import cn.angelo.hawkeye.core.zookeeper.ZkWatcher;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class NodeChangeListener implements NodeCacheListener {
    public static final Logger LOG = LoggerFactory.getLogger(NodeChangeListener.class);


    @Override
    public void nodeChanged() throws Exception {
        //TODO get data for web presentation
        LOG.info("node changed notification");

        String parentPath = "/" + Constant.ZK_PATH_PREFIX;
        List<String> clusterNamePathList = ZkWatcher.getInstance().getChild(parentPath);
        for (String clusterNamePath : clusterNamePathList) {
            String clusterNameFullPath = parentPath + "/" + clusterNamePath;
            List<String> metricPathList = ZkWatcher.getInstance().getChild(clusterNameFullPath);
            for (String metricPath : metricPathList) {
                String data = ZkWatcher.getInstance().readData(clusterNameFullPath + "/" + metricPath);
                LOG.info("data : {}", data);
                if (StringUtils.isNotBlank(data)) {
                    WebSocketServer.pushDataToWeb(data);
                }
            }
        }
    }
}
