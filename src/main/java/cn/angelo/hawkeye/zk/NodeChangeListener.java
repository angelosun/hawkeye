package cn.angelo.hawkeye.zk;

import cn.angelo.hawkeye.vo.CpuVo;
import cn.angelo.hawkeye.websocket.WebSocketServer;
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
        String data = ZkWatcher.getInstance().readData(CpuVo.ZK_PATH + "_" + hostAddress);
        LOG.info("data : {}", data);
        if (StringUtils.isNotBlank(data)) {
            WebSocketServer.pushDataToWeb(data);
        }
    }
}
