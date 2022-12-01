package cn.angelo.hawkeye.admin.init;

import cn.angelo.hawkeye.admin.zookeeper.NodeChangeListener;
import cn.angelo.hawkeye.admin.zookeeper.ZkConfig;
import cn.angelo.hawkeye.core.model.Constant;
import cn.angelo.hawkeye.core.zookeeper.ZkWatcher;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * Author: angelosun
 * Date: 2021/6/1 19:10
 * Description:
 */
@Configuration
public class InitService implements InitializingBean, DisposableBean {

    @Resource
    private ZkConfig zkConfig;
    @Override
    public void afterPropertiesSet() throws Exception {

        ZkWatcher zkWatcher = ZkWatcher.getInstance();
        zkWatcher.setZkAddr(zkConfig.getAddress());
        zkWatcher.connection();

        String parentPath = "/" + Constant.ZK_PATH_PREFIX;
        NodeChangeListener nodeChangeListener = null;

        if (!zkWatcher.checkExist(parentPath)) {
            zkWatcher.createPersistentNode(parentPath, "");
        }
        List<String> clusterNamePathList = ZkWatcher.getInstance().getChild(parentPath);
        for (String clusterNamePath : clusterNamePathList) {
            String clusterNameFullPath = parentPath + "/" + clusterNamePath;
            List<String> metricPathList = ZkWatcher.getInstance().getChild(clusterNameFullPath);
            for (String childPath : metricPathList) {
                nodeChangeListener = new NodeChangeListener();
                ZkWatcher.getInstance().addListener(clusterNameFullPath + "/" + childPath, nodeChangeListener);
            }
        }


    }

    @Override
    public void destroy() {
        ZkWatcher.getInstance().release();
    }


}
