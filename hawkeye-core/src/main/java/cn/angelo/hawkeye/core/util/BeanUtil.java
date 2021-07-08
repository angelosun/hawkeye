package cn.angelo.hawkeye.core.util;

import cn.angelo.hawkeye.core.model.Metric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * Author: angelo
 * Date: 2021/7/7 21:19
 * Description:
 */
public class BeanUtil {

	public static final Logger LOG = LoggerFactory.getLogger(BeanUtil.class);

	public static<T> String printBeanInfo(T obj) throws Exception {
		Class clazz = obj.getClass();
		StringBuilder sbMsg = new StringBuilder();

		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			Object result = field.get(obj);
			if (result != null) {
				Annotation a = field.getAnnotation(Metric.class);
				if (a != null) {
					if (((Metric) a).capacityConversionFlag()) {
						result = CommonUtil.capacityConversion(Double.valueOf(result.toString()));
					}
				}
				sbMsg.append(field.getName()).append(" : ").append(result).append(" ");
			}
		}
		return sbMsg.toString();
	}

	public static<T> T convert(Class<T> cls, Map m) throws Exception {
		T obj = cls.getDeclaredConstructor().newInstance();
		Set<String> keys = m.keySet();
		for (String key : keys) {
			for (Field field : cls.getDeclaredFields()) {
				field.setAccessible(true);
				Annotation a = field.getAnnotation(Metric.class);
				if (a != null) {
					Metric metric = (Metric) a;
					String abbrName = metric.name();
					if (abbrName.equals(key)) {
						String fieldTypeName = field.getType().getName();
						if ("java.lang.Integer".equals(fieldTypeName)) {
							field.set(obj, Integer.parseInt((String) m.get(key)));
						} else if ("java.lang.Double".equals(fieldTypeName)) {
							field.set(obj, Double.parseDouble((String) m.get(key)));
						}
					}
				}
			}
		}
		return obj;
	}
}
