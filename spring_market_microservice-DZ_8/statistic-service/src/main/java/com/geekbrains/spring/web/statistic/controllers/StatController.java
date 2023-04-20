package com.geekbrains.spring.web.statistic.controllers;

import com.geekbrains.spring.web.api.core.ProductDto;

import com.geekbrains.spring.web.statistic.entites.Cart;

import com.geekbrains.spring.web.statistic.integrations.ProductsServiceIntegration;
import com.geekbrains.spring.web.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
public class StatController {
    private final StatisticService statisticService;
    private final ProductsServiceIntegration productsServiceIntegration;



    @GetMapping
    public List<Cart> topProductCarts() {
        return statisticService.findTop();
    }
    @GetMapping ("/all")
    public List<Cart> findAll() {
        return statisticService.findAll();
    }


    @GetMapping("/top/{productId}")
    public ProductDto saveNewProductStatisticDto(@PathVariable Long productId) {
        // запрос в productsService для поиска продукта по id
        ProductDto productDto = productsServiceIntegration.findById(productId);


// находим продукт в нашей базе топ продуктов из карзины по имени
        Cart topProductCart = statisticService.findByName(productDto.getTitle());
        if (topProductCart != null) {
            // если он есть то изменяем количество
            statisticService.update(productDto.getTitle());
            return productDto;
        } else {
            // если не найден создаем новый
            topProductCart = new Cart();
            topProductCart.setTitle(productDto.getTitle());
            topProductCart.setQuantity(+1);
            // создаем нового user и передаем ему параметры нового созданного dto
            // сохраняем
            statisticService.save(topProductCart);
        }
        // возвращаем ответ на запрос cart-servica
            return productDto;
        }

    }


