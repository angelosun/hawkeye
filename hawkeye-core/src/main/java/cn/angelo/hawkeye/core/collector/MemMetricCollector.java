package cn.angelo.hawkeye.core.collector;

import cn.angelo.hawkeye.core.model.Constant;
import cn.angelo.hawkeye.core.model.MemVo;
import cn.angelo.hawkeye.core.util.CommonUtil;
import cn.angelo.hawkeye.core.zookeeper.ZkWatcher;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.concurrent.TimeUnit;

/**
 * Author: angelo
 * Date: 2021/7/6 15:07
 * Description:
 */
public class MemMetricCollector extends AbstractCollector {

    public static final Logger LOG = LoggerFactory.getLogger(MemMetricCollector.class);

    @Override
    public void run() {
        while (true) {
            try {
                MemVo memVo = new MemVo();
                TimeUnit.SECONDS.sleep(10);
                SystemInfo si = new SystemInfo();
                HardwareAbstractionLayer hal = si.getHardware();
                memVo.setTotal(hal.getMemory().getTotal());
                memVo.setFree(hal.getMemory().getAvailable());
                memVo.setUsed(hal.getMemory().getTotal() - hal.getMemory().getAvailable());
                LOG.info("内存情况 {} ", JSONObject.toJSONString(memVo));
                LOG.info("总内存容量:" + CommonUtil.convertFileSize(Long.parseLong(String.valueOf(memVo.getTotal()))));
                LOG.info("可用内存容量:" + CommonUtil.convertFileSize(Long.parseLong(String.valueOf(memVo.getFree()))));
                LOG.info("已用内存容量:" + CommonUtil.convertFileSize(Long.parseLong(String.valueOf(memVo.getUsed()))));

                ZkWatcher.getInstance().writeData("/" + Constant.ZK_PATH_PREFIX + "/" + getCollectorTypeEnum().getZkPath(), memVo.toString());

            } catch (Exception e) {

            }
        }
    }

}
