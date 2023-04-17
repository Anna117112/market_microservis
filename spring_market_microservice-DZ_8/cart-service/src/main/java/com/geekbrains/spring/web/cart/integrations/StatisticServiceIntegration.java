package com.geekbrains.spring.web.cart.integrations;

import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StatisticServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integrations.statistic-service.url}")
    private String statServiseUrl;

    public void statistic(Long id) {
        ProductDto productDto = restTemplate.getForObject(statServiseUrl + "/api/v1/statistic/top/" +1, ProductDto.class);

    }
}

