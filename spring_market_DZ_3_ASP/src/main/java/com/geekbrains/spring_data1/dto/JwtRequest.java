package com.geekbrains.spring_data1.dto;

import lombok.Data;

@Data
//это когда пользователь  присылаеть логин и пароль в обмен на токен
public class JwtRequest {
    private String username;
    private String password;
}
