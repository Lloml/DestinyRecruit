package cn.lloml.destinyrecruit.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.time.Duration;

/**
 * @author Lloml
 */
@Configuration
public class HttpClientConfig {
    @Bean
    public HttpClient httpClient() {
        return HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofMillis(10000))
                .version(HttpClient.Version.HTTP_2)
                .build();
    }
}

