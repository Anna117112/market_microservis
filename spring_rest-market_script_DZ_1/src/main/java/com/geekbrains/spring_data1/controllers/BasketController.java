package com.geekbrains.spring_data1.controllers;

import com.geekbrains.spring_data1.basket.ProductBasket;
import com.geekbrains.spring_data1.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
// можно не писать везде ссылку  - products - елси она одинаковая в методах
@RequestMapping("/api/v1/basket")
// для полей final создает конструктор с этим набором полей -
//    }
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @GetMapping("/add/{id}")
    public void addProduct(@PathVariable Long id) {

       basketService.addProduct(id);

    }
    @GetMapping()
        public ProductBasket showBasket(){
            return basketService.showBasket();
        }



        @GetMapping("/delete/{id}")
            public void deleteById(@PathVariable Long id){
                basketService.deleteById(id);

            }

        }

   







