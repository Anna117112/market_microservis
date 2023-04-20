package com.geekbrains.spring.web.statistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// микросервис сбора статистики  топ 5 складываемых в корзину товаров
@SpringBootApplication
public class StatisticApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticApplication.class, args);
    }
}

