package com.geekbrains.spring_data1.services;


import com.geekbrains.spring_data1.dto.Cart;
import com.geekbrains.spring_data1.entites.Product;
import com.geekbrains.spring_data1.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
// чтобы заинжектить final ProductService
@RequiredArgsConstructor
public class CartService {
    private final ProductsService productsService;
    private Cart cart;
// после старта приложинеия кладем сюда новую корзину
    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }
// добавляем продукт в корзину
    public void addProductByIdToCart(Long productId) {
        // если продукта нет в корзине
        if (!getCurrentCart().addProduct(productId)) {
            Product product = productsService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину. Продукт не найдет, id: " + productId));
            getCurrentCart().addProduct(product);
        }
    }

    public void clear() {
        getCurrentCart().clear();
    }
}
