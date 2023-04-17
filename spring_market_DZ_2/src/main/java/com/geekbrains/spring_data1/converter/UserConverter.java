package com.geekbrains.spring_data1.converter;

import com.geekbrains.spring_data1.dto.UserDto;
import com.geekbrains.spring_data1.entites.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    // преобразуем в продукт
//    public User dtoToEntity(UserDto userDto){
//        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail());
//
//    }
    public UserDto entityToDto(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getEmail());

    }
}
