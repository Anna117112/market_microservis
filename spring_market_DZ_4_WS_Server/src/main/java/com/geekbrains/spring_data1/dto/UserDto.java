package com.geekbrains.spring_data1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
// конструктор со всеми аргументами
@AllArgsConstructor
public class UserDto {

        private Long id;


        private String username;


        private String password;


        private String email;
}
