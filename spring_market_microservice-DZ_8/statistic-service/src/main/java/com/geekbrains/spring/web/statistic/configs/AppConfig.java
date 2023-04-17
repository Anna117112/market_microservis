package com.geekbrains.spring.web.statistic.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
// класс конфиг для работы с rest - взаимодействие с другими сервисами spring2 - лекция 5
@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
