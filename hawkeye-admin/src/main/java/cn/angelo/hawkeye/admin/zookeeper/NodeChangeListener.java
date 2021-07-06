package cn.angelo.hawkeye.admin.zookeeper;

import cn.angelo.hawkeye.admin.websocket.WebSocketServer;
import cn.angelo.hawkeye.core.model.CollectorTypeEnum;
import cn.angelo.hawkeye.core.model.Constant;
import cn.angelo.hawkeye.core.zookeeper.ZkWatcher;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

public class NodeChangeListener implements NodeCacheListener {
    public static final Logger LOG = LoggerFactory.getLogger(NodeChangeListener.class);


    @Override
    public void nodeChanged() throws Exception {
        //TODO get data for web presentation
        LOG.info("node changed notification");
        InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();
        for (CollectorTypeEnum collectorTypeEnum : CollectorTypeEnum.values()) {
            String data = ZkWatcher.getInstance().readData("/" + Constant.ZK_PATH_PREFIX + "/" + collectorTypeEnum.getZkPath());
            LOG.info("data : {}", data);
            if (StringUtils.isNotBlank(data)) {
                WebSocketServer.pushDataToWeb(data);
            }
        }
    }
}
