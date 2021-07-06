package cn.angelo.hawkeye.core.model;

import cn.angelo.hawkeye.core.colloct.CpuMetricCollector;
import cn.angelo.hawkeye.core.colloct.MemMetricCollector;

/**
 * Author: junyingcao
 * Date: 2021/5/31 16:35
 * Description:
 */
public enum CollectorTypeEnum {

    CPU_COLLECTOR(1, CpuMetricCollector.class, "cpu"),
    MEM_COLLECTOR(2,MemMetricCollector.class, "mem"),
    ;


    CollectorTypeEnum(Integer type, Class clazz, String zkPath) {
        this.type = type;
        this.clazz = clazz;
        this.zkPath = zkPath;

    }
    private Integer type;
    private Class clazz;
    private String zkPath;

    public String getZkPath() {
        return zkPath;
    }

    public void setZkPath(String zkPath) {
        this.zkPath = zkPath;
    }

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
