package cn.angelo.hawkeye.collect;

import org.springframework.beans.factory.DisposableBean;

import javax.annotation.Resource;
import java.util.concurrent.Executor;


public class InitializeService implements DisposableBean {

    @Resource(name = "asyncExecutor")
    private Executor asyncExecutor;


    public void init() {
        asyncExecutor.execute(new StatiticsTask());
    }
    @Override
    public void destroy() {
        if (asyncExecutor instanceof DisposableBean) {
            try {
                ((DisposableBean) asyncExecutor).destroy();
            } catch (Exception e) {
            }
        }
    }
}
