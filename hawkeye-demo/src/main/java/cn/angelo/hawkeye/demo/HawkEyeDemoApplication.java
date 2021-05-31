package cn.angelo.hawkeye.demo;

import cn.angelo.hawkeye.spring.boot.EnableHawkEye;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHawkEye
@SpringBootApplication
public class HawkEyeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HawkEyeDemoApplication.class, args);
	}

}