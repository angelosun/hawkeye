package cn.angelo.hawkeye.spring.boot;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: angelo
 * Date: 2021/5/31 14:51
 * Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(HawkEyeAutoConfiguration.class)
public @interface EnableHawkEye {

}
