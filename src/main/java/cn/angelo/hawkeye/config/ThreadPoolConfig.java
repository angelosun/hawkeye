package cn.angelo.hawkeye.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {


    @Value("${async.executor.corePoolSize}")
    private int corePoolSize;

    @Value("${async.executor.maxPoolSize}")
    private int maxPoolSize;

    @Value("${async.executor.queueCapacity}")
    private int queueCapacity;

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        // 如果池中的实际线程数小于corePoolSize,无论是否其中有空闲的线程，都会给新的任务产生新的线程
        executor.setCorePoolSize(corePoolSize);
        // 连接池中保留的最大连接数。Default: 15 maxPoolSize
        executor.setMaxPoolSize(maxPoolSize);
        // queueCapacity 线程池所使用的缓冲队列
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("thread-pool-1");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;

    }
}
