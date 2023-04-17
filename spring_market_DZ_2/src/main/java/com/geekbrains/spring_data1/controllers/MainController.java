package com.geekbrains.spring_data1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// можно не писать везде ссылку  - products - елси она одинаковая в методах
@RequestMapping("/api/v1/authenticated")
// для полей final создает конструктор с этим набором полей -
//    }
public class MainController {
    @GetMapping()
    public String homePage(){
        return "hellow world";
    }
    @GetMapping("/secured/")
    public String authenticated(){
        return "securedi";
    }
}
