package cn.angelo.hawkeye.admin.spring.boot;

import cn.angelo.hawkeye.admin.core.zookeeper.ZkWatcher;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Author: angelo
 * Date: 2021/5/31 15:03
 * Description:
 */
@Configuration
@ConditionalOnBean(annotation = EnableHawkEye.class)
public class HawkEyeAutoConfiguration implements InitializingBean, DisposableBean {

    @Resource
    private HawkEyeProperties hawkEyeProperties;

    @Override
    public void afterPropertiesSet() throws Exception {

        ZkWatcher zkWatcher = ZkWatcher.getInstance();
        zkWatcher.setZkAddr(hawkEyeProperties.getRegistryAddress());
        zkWatcher.connection();
        //ZkWatcher.getInstance().addListener("/statistics/cpu");
        //Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new StatisticsTask(), 0, 10, TimeUnit.SECONDS);

    }
    @Override
    public void destroy() throws Exception {
        ZkWatcher zkWatcher = ZkWatcher.getInstance();
        zkWatcher.release();
    }


}
