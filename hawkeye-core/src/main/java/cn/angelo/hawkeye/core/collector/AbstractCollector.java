package cn.angelo.hawkeye.core.collector;


import cn.angelo.hawkeye.core.model.CollectorTypeEnum;
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

    private CollectorTypeEnum collectorTypeEnum;

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(5);
                deal();
            } catch (Exception e) {
                LOG.error("失败", e);
            }
        }
    }

    public abstract void deal() throws Exception;


    public String getZkPath() {
        return "/" + Constant.ZK_PATH_PREFIX + "/" + clusterName +  "/" + getCollectorTypeEnum().getZkPath();
    }


    public CollectorTypeEnum getCollectorTypeEnum() {
        return collectorTypeEnum;
    }

    public void setCollectorTypeEnum(CollectorTypeEnum collectorTypeEnum) {
        this.collectorTypeEnum = collectorTypeEnum;
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
