package cn.angelo.hawkeye.admin.init;

import cn.angelo.hawkeye.admin.zookeeper.NodeChangeListener;
import cn.angelo.hawkeye.admin.zookeeper.ZkConfig;
import cn.angelo.hawkeye.core.zookeeper.ZkWatcher;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Author: junyingcao
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
        NodeChangeListener nodeChangeListener = new NodeChangeListener();
        ZkWatcher.getInstance().addListener("/statistics/cpu", nodeChangeListener);

    }

    @Override
    public void destroy() {
        ZkWatcher.getInstance().release();
    }


}
