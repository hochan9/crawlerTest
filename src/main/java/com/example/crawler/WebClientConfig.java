
package com.example.crawler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://m.land.naver.com")
                .defaultHeader(HttpHeaders.USER_AGENT,
                    "Mozilla/5.0 (iPhone; CPU iPhone OS 16_0 like Mac OS X)")
                .defaultHeader(HttpHeaders.REFERER, "https://m.land.naver.com/")
                .build();
    }
}
