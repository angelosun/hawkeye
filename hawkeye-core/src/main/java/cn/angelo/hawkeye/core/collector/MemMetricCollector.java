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

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Author: angelo
 * Date: 2021/7/6 15:07
 * Description:
 */
public class MemMetricCollector extends AbstractCollector {

    public static final Logger LOG = LoggerFactory.getLogger(MemMetricCollector.class);

    @Override
    public void collect() throws Exception {
        MemVo memVo = new MemVo();
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        memVo.setTotal(new BigDecimal(String.valueOf(hal.getMemory().getTotal())).setScale(2, RoundingMode.HALF_UP).longValue());
        memVo.setFree(new BigDecimal(String.valueOf(hal.getMemory().getAvailable())).setScale(2, RoundingMode.HALF_UP).longValue());
        memVo.setUsed(memVo.getTotal() - memVo.getFree());

        LOG.info("内存情况 {} ", JSONObject.toJSONString(memVo));
        LOG.info("总内存容量:" + CommonUtil.capacityConversion(memVo.getTotal()));
        LOG.info("可用内存容量:" + CommonUtil.capacityConversion(memVo.getFree()));
        LOG.info("已用内存容量:" + CommonUtil.capacityConversion(memVo.getUsed()));

        ZkWatcher.getInstance().writeData(getZkPath(), memVo.toString());

    }

}
