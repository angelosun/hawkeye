package cn.angelo.hawkeye.spring.boot;

import cn.angelo.hawkeye.core.colloct.CpuMetricCollector;
import cn.angelo.hawkeye.core.zookeeper.ZkWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author: angelo
 * Date: 2021/5/31 15:03
 * Description:
 */
@Configuration
@EnableConfigurationProperties(HawkEyeProperties.class)
public class HawkEyeAutoConfiguration implements InitializingBean, DisposableBean {

    public static final Logger LOG = LoggerFactory.getLogger(CpuMetricCollector.class);

    @Resource
    private HawkEyeProperties hawkEyeProperties;

    @Override
    public void afterPropertiesSet() throws Exception {

        LOG.info("after properties set ");
        ZkWatcher zkWatcher = ZkWatcher.getInstance();
        zkWatcher.setZkAddr(hawkEyeProperties.getRegistryAddress());
        zkWatcher.connection();
        //ZkWatcher.getInstance().addListener("/statistics/cpu");
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new CpuMetricCollector(), 0, 10, TimeUnit.SECONDS);

    }
    @Override
    public void destroy() throws Exception {
        ZkWatcher zkWatcher = ZkWatcher.getInstance();
        zkWatcher.release();
    }


}
