package cn.angelo.hawkeye.core.colloct;


import cn.angelo.hawkeye.core.model.CollectorTypeEnum;

/**
 * Author: angelosun
 * Date: 2021/5/31 15:44
 * Description:
 */
public abstract class AbstractCollector implements Runnable {


    private CollectorTypeEnum collectorTypeEnum;

    @Override
    public void run() {
    }


    public CollectorTypeEnum getCollectorTypeEnum() {
        return collectorTypeEnum;
    }

    public void setCollectorTypeEnum(CollectorTypeEnum collectorTypeEnum) {
        this.collectorTypeEnum = collectorTypeEnum;
    }
}
