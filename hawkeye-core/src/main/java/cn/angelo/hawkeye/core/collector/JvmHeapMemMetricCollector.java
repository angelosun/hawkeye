package cn.angelo.hawkeye.core.collector;

import cn.angelo.hawkeye.core.jvm.JStat;
import cn.angelo.hawkeye.core.util.BeanUtil;
import cn.angelo.hawkeye.core.model.HeapMemVo;
import cn.angelo.hawkeye.core.zookeeper.ZkWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Author: angelo
 * Date: 2021/6/1 8:52
 * Description:
 */
public class JvmHeapMemMetricCollector extends AbstractCollector {

	public static final Logger LOG = LoggerFactory.getLogger(JvmHeapMemMetricCollector.class);

	@Override
	public void deal() throws Exception {
		String pid = getPid();
		String [] cmd = new String[] {"jstat", "-gc", pid};
		Map map = JStat.jstat(cmd);
		HeapMemVo heapMemVo = BeanUtil.convert(HeapMemVo.class, map);
		LOG.info(BeanUtil.printBeanInfo(heapMemVo));
		ZkWatcher.getInstance().writeData(getZkPath(), heapMemVo.toString());

	}










}
