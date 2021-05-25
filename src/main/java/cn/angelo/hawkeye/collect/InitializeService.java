package cn.angelo.hawkeye.collect;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InitializeService implements InitializingBean, DisposableBean {



    @Override
    public void afterPropertiesSet() throws Exception {

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new StatisticsTask(), 0, 10, TimeUnit.SECONDS);

    }
    @Override
    public void destroy() {

    }
}
