package com.geekbrains.spring_data1.controllers;


import com.geekbrains.spring_data1.dto.ProfileDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @GetMapping
    // данные о пользователе текущем минимальные
    public ProfileDto getCurrentUserInfo(Principal principal) {
        // если нужна полная инфо о изере
        // User user = userService.findByUsername(principal.getName());
        return new ProfileDto(principal.getName());
    }
}
