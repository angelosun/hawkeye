package cn.angelo.hawkeye.spring.boot;

import cn.angelo.hawkeye.core.collector.AbstractCollector;
import cn.angelo.hawkeye.core.model.CollectorEnum;
import cn.angelo.hawkeye.core.zookeeper.ZkWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author: angelo
 * Date: 2021/5/31 15:03
 * Description:
 */
@Configuration
@EnableConfigurationProperties({ClusterConfig.class, ExecutorConfig.class})
public class HawkEyeAutoConfiguration implements InitializingBean, DisposableBean {

    public static final Logger LOG = LoggerFactory.getLogger(HawkEyeAutoConfiguration.class);

    @Resource
    private ClusterConfig clusterConfig;

    @Resource
    private ExecutorConfig executorConfig;

    @Resource(name = "asyncExecutor")
    private Executor asyncExecutor;


    private String threadNamePrefix = "AsyncExecutorThread-";

    @Override
    public void afterPropertiesSet() throws Exception {

        LOG.info("after properties set ");
        ZkWatcher zkWatcher = ZkWatcher.getInstance();
        zkWatcher.setZkAddr(clusterConfig.getRegistryAddress());
        zkWatcher.connection();
        for (CollectorEnum typeEnum : CollectorEnum.values()) {
            AbstractCollector collector = (AbstractCollector) typeEnum.getClazz().newInstance();
            collector.setClusterName(clusterConfig.getClusterName());
            collector.setCollectorTypeEnum(typeEnum);
            asyncExecutor.execute(collector);
        }

    }
    @Override
    public void destroy() throws Exception {
        ZkWatcher zkWatcher = ZkWatcher.getInstance();
        zkWatcher.release();
    }

    @Lazy
    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new VisibleThreadPoolTaskExecutor();
        // ????????????????????????????????????corePoolSize,??????????????????????????????????????????????????????????????????????????????
        executor.setCorePoolSize(executorConfig.getCorePoolSize());
        // ???????????????????????????????????????Default: 15 maxPoolSize
        executor.setMaxPoolSize(executorConfig.getMaxPoolSize());
        // queueCapacity ?????????????????????????????????
        executor.setQueueCapacity(executorConfig.getQueueCapacity());
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;

    }



}
