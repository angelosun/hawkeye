package cn.angelo.hawkeye.core.collector;

import cn.angelo.hawkeye.core.model.CpuVo;
import cn.angelo.hawkeye.core.zookeeper.ZkWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.text.DecimalFormat;

/**
 * Author: angelo
 * Date: 2021/5/31 15:26
 * Description:
 */
public class CpuMetricCollector extends AbstractCollector {

    public static final Logger LOG = LoggerFactory.getLogger(CpuMetricCollector.class);


    @Override
    public void collect() throws Exception {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        CpuVo cpuVo = new CpuVo();
        cpuVo.setLogicalProcessorCount(processor.getLogicalProcessorCount());
        cpuVo.setCpuSystemUsage(new DecimalFormat("#.##%").format(cSys * 1.0 / totalCpu));
        cpuVo.setCpuUserUsage(new DecimalFormat("#.##%").format(user * 1.0 / totalCpu));
        cpuVo.setCpuCurrentWaitPercent(new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu));
        cpuVo.setCpuAvailablePercent(new DecimalFormat("#.##%").format(idle * 1.0 / totalCpu));
        cpuVo.setSystemCpuLoadBetweenTricks(processor.getSystemCpuLoadBetweenTicks());
        cpuVo.setSystemCpuLoad(processor.getSystemCpuLoad());

        LOG.info("cpu核数:" + processor.getLogicalProcessorCount());
        LOG.info("cpu系统使用率:" + new DecimalFormat("#.##%").format(cSys * 1.0 / totalCpu));
        LOG.info("cpu用户使用率:" + new DecimalFormat("#.##%").format(user * 1.0 / totalCpu));
        LOG.info("cpu当前等待率:" + new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu));
        LOG.info("cpu当前空闲率:" + new DecimalFormat("#.##%").format(idle * 1.0 / totalCpu));
        LOG.info("CPU load: %.1f%% (counting ticks)%n", processor.getSystemCpuLoadBetweenTicks() * 100);
        LOG.info("CPU load: %.1f%% (OS MXBean)%n", processor.getSystemCpuLoad() * 100);
        ZkWatcher.getInstance().writeData(getZkPath(), cpuVo.toString());
    }
}
