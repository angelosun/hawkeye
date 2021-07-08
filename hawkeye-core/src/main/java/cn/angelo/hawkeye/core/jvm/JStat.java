package cn.angelo.hawkeye.core.jvm;

import cn.angelo.hawkeye.core.util.ArrayUtil;
import cn.angelo.hawkeye.core.util.ExecuteCmd;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: angelo
 * Date: 2021/7/8 14:15
 * Description:
 */
public class JStat {

	public static Map jstat(String[] strings) throws Exception {
		String s = ExecuteCmd.execute(strings);
		assert s != null;
		BufferedReader reader = new BufferedReader(new StringReader(s));
		String[] keys = ArrayUtil.trim(reader.readLine().split("\\s+|\t"));
		String[] values = ArrayUtil.trim(reader.readLine().split("\\s+|\t"));

		Map<String, String> m = new HashMap();
		for (int i = 0; i < keys.length; i++) {
			m.put(keys[i], values[i]);
		}
		return m;
	}
}
