package cn.angelo.hawkeye.core.model;

import cn.angelo.hawkeye.core.colloct.CpuMetricCollector;

/**
 * Author: junyingcao
 * Date: 2021/5/31 16:35
 * Description:
 */
public enum CollectorTypeEnum {

    CPU_COLLECTOR(1, CpuMetricCollector.class);

    CollectorTypeEnum(Integer type, Class clazz) {
        this.type = type;
        this.clazz = clazz;

    }
    private Integer type;
    private Class clazz;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
