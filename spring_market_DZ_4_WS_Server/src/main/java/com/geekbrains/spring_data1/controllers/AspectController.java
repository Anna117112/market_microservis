package com.geekbrains.spring_data1.controllers;

import com.geekbrains.spring_data1.aop.InfoAspect;
import com.geekbrains.spring_data1.aop.LogEsecutionTimeAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
// можно не писать везде ссылку  - products - елси она одинаковая в методах
@RequestMapping("/api/v1/secured")
// для полей final создает конструктор с этим набором полей -
//    }
public class AspectController {

    public final LogEsecutionTimeAspect logEsecutionTimeAspect;

    @GetMapping()
    public List<InfoAspect> result(){

// выводим имя класси и время работы его методов
      return LogEsecutionTimeAspect.infoAspectList;
    }

}
