package com.geekbrains.spring_data1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
// @Configuration - это занчит можем ссылаться на этот файл в бинах
@Configuration
// secrets.properties - в этом файле лежат инфо для токена эта инфо испаользуется в JwtTokenUtil
@PropertySource("secrets.properties")
public class AppConfig {
}
