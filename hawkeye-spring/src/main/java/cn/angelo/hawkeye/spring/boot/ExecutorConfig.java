package cn.angelo.hawkeye.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Author: junyingcao
 * Date: 2021/7/6 17:18
 * Description:
 */
@ConfigurationProperties(prefix = "hawkeye.executor")
public class ExecutorConfig {

    private int corePoolSize;

    private int maxPoolSize;

    private int queueCapacity;


    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}
