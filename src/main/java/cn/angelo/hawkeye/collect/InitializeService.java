package cn.angelo.hawkeye.collect;

import cn.angelo.hawkeye.config.ZkConfig;
import cn.angelo.hawkeye.vo.CpuVo;
import cn.angelo.hawkeye.zk.ZkWatcher;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InitializeService implements InitializingBean, DisposableBean {

    @Resource
    private ZkConfig zkConfig;

    @Override
    public void afterPropertiesSet() throws Exception {

        ZkWatcher zkWatcher = ZkWatcher.getInstance();
        zkWatcher.setZkAddr(zkConfig.getAddress());
        zkWatcher.connection();
        InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();
        ZkWatcher.getInstance().addListener(CpuVo.ZK_PATH + "_" + hostAddress);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new StatisticsTask(hostAddress), 0, 10, TimeUnit.SECONDS);

    }
    @Override
    public void destroy() throws Exception {
        ZkWatcher zkWatcher = ZkWatcher.getInstance();
        zkWatcher.release();
    }
}
