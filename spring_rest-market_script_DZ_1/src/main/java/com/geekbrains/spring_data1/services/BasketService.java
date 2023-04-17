package com.geekbrains.spring_data1.services;


import com.geekbrains.spring_data1.basket.ProductBasket;
import com.geekbrains.spring_data1.converter.ProductConverter;
import com.geekbrains.spring_data1.dto.ProductDto;
import com.geekbrains.spring_data1.entites.Product;
import com.geekbrains.spring_data1.exceptions.ResourceNotFoundException;
import com.geekbrains.spring_data1.services.specifications.ProductsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
// для полей final создает конструктор с этим набором полей -   public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
@RequiredArgsConstructor
public class BasketService {
    // бин
    private final ProductBasket productBasket;
    private final ProductService productService;
    private final ProductConverter productConverter;


    public void addProduct(Long id){
        Product product = productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Невозможно обновить продукт не найден в базе id"));
        productBasket.addProduct(productConverter.entityToDto(product));
    }
    public ProductBasket showBasket(){
        return productBasket.showBasket();

    }


    public void deleteById(Long id) {
        Product product = productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Невозможно обновить продукт не найден в базе id"));
        productBasket.delete(productConverter.entityToDto(product));
    }


}
