package cn.angelo.hawkeye.core.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: angelo
 * Date: 2021/7/7 21:14
 * Description:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Metric {
	String name() default "";
	boolean capacityConversionFlag() default false;
}
