package com.geekbrains.spring_data1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// возврат токена в ответ на логин и пароль
public class JwtResponse {
    private String token;
}
