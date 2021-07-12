package cn.angelo.hawkeye.core.collector;


import cn.angelo.hawkeye.core.model.CollectorEnum;
import cn.angelo.hawkeye.core.model.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * Author: angelosun
 * Date: 2021/5/31 15:44
 * Description:
 */
public abstract class AbstractCollector implements Runnable {


    public static final Logger LOG = LoggerFactory.getLogger(AbstractCollector.class);

    private String clusterName;

    private CollectorEnum collectorEnum;

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(5);
                collect();
            } catch (Exception e) {
                LOG.error("失败", e);
            }
        }
    }

    public abstract void collect() throws Exception;


    public String getZkPath() {
        return "/" + Constant.ZK_PATH_PREFIX + "/" + clusterName +  "/" + getCollectorTypeEnum().getZkPath();
    }


    public CollectorEnum getCollectorTypeEnum() {
        return collectorEnum;
    }

    public void setCollectorTypeEnum(CollectorEnum collectorEnum) {
        this.collectorEnum = collectorEnum;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    protected String getPid(){
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        return  pid;
    }
}
