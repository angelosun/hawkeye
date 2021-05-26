package cn.angelo.hawkeye.collect;

import cn.angelo.hawkeye.config.ZkConfig;
import cn.angelo.hawkeye.zk.ZkWatcher;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InitializeService implements InitializingBean, DisposableBean {

    @Resource
    private ZkConfig zkConfig;

    @Override
    public void afterPropertiesSet() throws Exception {

        ZkWatcher zkWatcher = ZkWatcher.getInstance();
        zkWatcher.setZkAddr(zkConfig.getAddress());
        zkWatcher.connection();
        ZkWatcher.getInstance().addListener("/statistics/cpu");
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new StatisticsTask(), 0, 10, TimeUnit.SECONDS);

    }
    @Override
    public void destroy() throws Exception {
        ZkWatcher zkWatcher = ZkWatcher.getInstance();
        zkWatcher.release();
    }
}
