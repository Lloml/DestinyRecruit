package cn.lloml.destinyrecruit.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志配置
 */
@Configuration
public class LoggerConfig {
    @Bean
    public Logger logger(){
        return LoggerFactory.getLogger("Logback");
    }
}
