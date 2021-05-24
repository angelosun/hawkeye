package cn.angelo.hawkeye.config;

import cn.angelo.hawkeye.collect.InitializeService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfigureAfter(value = WebMvcAutoConfiguration.class )
@Configuration
public class InitBeanConfig {

    @Bean(name="initializeService")
    public InitializeService initializeService() {
        return new InitializeService();
    }
}
