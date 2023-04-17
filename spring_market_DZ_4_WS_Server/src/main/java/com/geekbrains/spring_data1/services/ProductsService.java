package com.geekbrains.spring_data1.services;


import com.geekbrains.spring_data1.dto.ProductDto;
import com.geekbrains.spring_data1.entites.Product;
import com.geekbrains.spring_data1.exceptions.ResourceNotFoundException;
import com.geekbrains.spring_data1.repositories.ProductsRepository;
import com.geekbrains.spring_data1.repositories.specifications.ProductsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@EnableAspectJAutoProxy
@Service
// для полей final создает конструктор с этим набором полей -   public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
@RequiredArgsConstructor
public class ProductsService {
    // Page список обектов с доп инфо - это продвинутый лист
    private final ProductsRepository productsRepository;


    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer page) {
        // хотим собрать спецификацию при null вытаскиваем всех
        Specification<Product> spec = Specification.where(null);
        // добавляем проверку для спецификации
        if (minPrice != null) {
            // добавим к спецификации проверку
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            // добавим к спецификации проверку
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }
        // вывод с первой страницы, на страницы по 5 обектов PageRequest.of(page-1, 5) можно добавить sort
        return productsRepository.findAll(spec, PageRequest.of(page - 1, 50));
    }
    // список продуктов по id Optional - пишется если может быть ошибка что продуст не судествует
    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productsRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productsRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукта, не надйен в базе, id: " + productDto.getId()));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        return product;
    }

    public static final Function<Product, com.geekbrains.spring_data1.soap.Product> functionEntityToSoap = pe -> {
        com.geekbrains.spring_data1.soap.Product p = new com.geekbrains.spring_data1.soap.Product();
        p.setId(pe.getId());
        p.setTitle(pe.getTitle());
        p.setPrice(pe.getPrice());
        return p;
    };
    public List<com.geekbrains.spring_data1.soap.Product> getAllProducts() {
        return productsRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public com.geekbrains.spring_data1.soap.Product getByName(String name) {
        return productsRepository.findByName(name).map(functionEntityToSoap).get();
    }
}


