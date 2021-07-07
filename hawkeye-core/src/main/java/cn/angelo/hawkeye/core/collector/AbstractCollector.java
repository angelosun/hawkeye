package cn.angelo.hawkeye.core.collector;


import cn.angelo.hawkeye.core.model.CollectorTypeEnum;
import cn.angelo.hawkeye.core.model.Constant;

/**
 * Author: angelosun
 * Date: 2021/5/31 15:44
 * Description:
 */
public abstract class AbstractCollector implements Runnable {

    private String clusterName;

    private CollectorTypeEnum collectorTypeEnum;

    @Override
    public void run() {
    }

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
}
