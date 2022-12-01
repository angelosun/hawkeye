package cn.angelo.hawkeye.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
public class HawkEyeAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(HawkEyeAdminApplication.class, args);
	}

}