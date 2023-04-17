package com.geekbrains.spring.web.statistic.service;




import com.geekbrains.spring.web.statistic.entites.Cart;

import com.geekbrains.spring.web.statistic.integrations.ProductsServiceIntegration;
import com.geekbrains.spring.web.statistic.repositories.TopProductCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final TopProductCartRepository topProductCartRepository;
    private final ProductsServiceIntegration productsServiceIntegration;
    // список продуктов по id Optional - пишется если может быть ошибка что продуст не судествует

    public Optional<Cart> findById(Long id) {
        return topProductCartRepository.findById(id);
    }


    public Cart save (Cart topProductCart) {
//        ProductDto productDto = productsServiceIntegration.findById();
//        TopProductCart topProductCart = new TopProductCart();
//        topProductCart.setTitle(productDto.getTitle());
//        topProductCart.setQuantity(topProductCart.getQuantity()+1);
        // преверяем есть ли он в базе если нет то создаем нового
        // topProductCart = topProductCartRepository.findByTitle(topProductCart.getTitle()).orElse(topProductCartRepository.save(topProductCart));
        // возвращаем dto
        return topProductCartRepository.save(topProductCart);
    }

    public List<Cart> findTop() {

        return topProductCartRepository.findTopCart();
    }

    public List<Cart> findAll() {
        //System.out.println(topProductCartRepository.findTopCart());
        return topProductCartRepository.findAll();
    }

    public Cart findByName(String title) {

        return topProductCartRepository.findByName(title);
    }
// чтобы результаты сохранились то ставим  @Transactional
    @Transactional
    public Cart update(String title) {
       Cart topProductCart = topProductCartRepository.findByName(title);
        topProductCart.setQuantity(topProductCart.getQuantity() + 1);
        return topProductCart;
    }
}