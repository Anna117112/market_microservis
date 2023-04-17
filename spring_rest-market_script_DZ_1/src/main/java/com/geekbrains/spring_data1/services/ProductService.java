package com.geekbrains.spring_data1.services;


import com.geekbrains.spring_data1.dto.ProductDto;
import com.geekbrains.spring_data1.entites.Product;
import com.geekbrains.spring_data1.exceptions.ResourceNotFoundException;
import com.geekbrains.spring_data1.repositories.ProductRepository;
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
public class ProductService {
    // бин
    private final ProductRepository productRepository;


// Page список обектов с доп инфо - это продвинутый лист
    public Page<Product> findAll(Integer minСost, Integer maxCost, String partName, Integer page){
        // хотим собрать спецификацию при null вытаскиваем всех
        Specification <Product> spec = Specification.where(null);
        // добавляем проверку для спецификации
        if (minСost!=null){
            // добавим к спецификации проверку
            spec = spec.and(ProductsSpecifications.costGreaterOrEqualsThan(minСost));

        }
        if (maxCost!=null) {
            // добавим к спецификации проверку
            spec = spec.and(ProductsSpecifications.costLessThanOrEqualsThan(maxCost));
        }
        if (partName!=null) {
            // добавим к спецификации проверку
            spec = spec.and(ProductsSpecifications.nameLike(partName));
        }
        // вывод с первой страницы, на страницы по 5 обектов PageRequest.of(page-1, 5) можно добавить sort
        return productRepository.findAll(spec, PageRequest.of(page-1, 5));

    }
// получить всех студентов это метод findAll() взяли из наслодованного класса рекозитория JpaRepository
    public List<Product> findAll() {
        return productRepository.findAll();
    }


// список продуктов по id Optional - пишется если может быть ошибка что продуст не судествует
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }



    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
@Transactional
    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(()->new ResourceNotFoundException("Невозможно обновить продукт не найден в базе id"));
        product.setCost(productDto.getCost());
        product.setName(productDto.getName());
        return product;
    }
//
//// списко между мин и макс ценой
//    public List<Product> findByCostBetween(Integer min, Integer max) {
//        return productRepository.findAllByCostBetween(min, max);
//    }
//// продукт с ценой больше мин
//    public List<Product> findByProductMoreMinCost() {
//        return productRepository.findAllProductMoreMinCost();
//    }
//    // продукт с ценой меньше макс
//    public List<Product> findByProductLessMaxCost() {
//        return productRepository.findAllProductLessMaxCost();
//    }

}
