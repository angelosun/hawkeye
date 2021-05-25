package cn.angelo.hawkeye.vo;

public class CpuVo {
    /**
     * CPU核数
     */
    private Integer logicalProcessorCount;

    /**
     * CPU系统使用率
     */
    private String cpuSystemUsage;

    /**
     * CPU用户使用率
     */
    private String cpuUserUsage;

    /**
     * CPU当前等待率
     */
    private String cpuCurrentWaitPercent;

    /**
     * CPU当前空闲率
     */
    private String cpuAvailiablePercent;


    private Double systemCpuLoadBetweenTricks;

    private Double systemCpuLoad;

    public Integer getLogicalProcessorCount() {
        return logicalProcessorCount;
    }

    public void setLogicalProcessorCount(Integer logicalProcessorCount) {
        this.logicalProcessorCount = logicalProcessorCount;
    }

    public String getCpuSystemUsage() {
        return cpuSystemUsage;
    }

    public void setCpuSystemUsage(String cpuSystemUsage) {
        this.cpuSystemUsage = cpuSystemUsage;
    }

    public String getCpuUserUsage() {
        return cpuUserUsage;
    }

    public void setCpuUserUsage(String cpuUserUsage) {
        this.cpuUserUsage = cpuUserUsage;
    }

    public String getCpuCurrentWaitPercent() {
        return cpuCurrentWaitPercent;
    }

    public void setCpuCurrentWaitPercent(String cpuCurrentWaitPercent) {
        this.cpuCurrentWaitPercent = cpuCurrentWaitPercent;
    }

    public String getCpuAvailiablePercent() {
        return cpuAvailiablePercent;
    }

    public void setCpuAvailiablePercent(String cpuAvailiablePercent) {
        this.cpuAvailiablePercent = cpuAvailiablePercent;
    }

    public Double getSystemCpuLoadBetweenTricks() {
        return systemCpuLoadBetweenTricks;
    }

    public void setSystemCpuLoadBetweenTricks(Double systemCpuLoadBetweenTricks) {
        this.systemCpuLoadBetweenTricks = systemCpuLoadBetweenTricks;
    }

    public Double getSystemCpuLoad() {
        return systemCpuLoad;
    }

    public void setSystemCpuLoad(Double systemCpuLoad) {
        this.systemCpuLoad = systemCpuLoad;
    }
}
