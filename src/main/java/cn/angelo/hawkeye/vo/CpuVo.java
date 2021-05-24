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
}
