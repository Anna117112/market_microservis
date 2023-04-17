package com.geekbrains.spring_data1.controllers;

import com.geekbrains.spring_data1.basket.ProductBasket;
import com.geekbrains.spring_data1.converter.ProductConverter;
import com.geekbrains.spring_data1.dto.ProductDto;
import com.geekbrains.spring_data1.entites.Product;
import com.geekbrains.spring_data1.exceptions.ResourceNotFoundException;
import com.geekbrains.spring_data1.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// можно не писать везде ссылку  - products - елси она одинаковая в методах
@RequestMapping("/api/v1/products")
// для полей final создает конструктор с этим набором полей -
//    }
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductBasket productBasket;




    @GetMapping()
    public Page<ProductDto> getAllProducts(
            // метод может применять параменты
            @RequestParam (name = "p", defaultValue = "1") Integer page,// номер страницы
            @RequestParam (name = "min_cost", required = false) Integer minCost,// мин значение если не указано то берем за 0
            @RequestParam (name = "max_cost", required = false)  Integer maxCost, // макс значение
            @RequestParam (name = "name_part", required = false)  String namePart // макс значение
    ) {
        if (page < 1) {
            page = 1;
        }
        // map(p->new ProductDto(p) преобразователь из продукта получим новый ProductDto и отдадим туда продукт
        return productService.findAll(minCost, maxCost, namePart, page).map(

                p -> productConverter.entityToDto(p));
    }

//    @PostMapping()
//    public Product saveNewProduct(@RequestBody Product product) {
//        product.setId(null);
//        return productService.save(product);
//    }
//
//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable Long id) {
//        //есл продукт по id найден возвращаем его,  если нет то кидаем инфо об ошибке
//        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found, id: " + id));
//    }


    @PostMapping()
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        // создаем новый продукт и передаем ему параметры нового созданного dto
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        // возвращаем dto
        return productConverter.entityToDto(product);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        //есл продукт по id найден возвращаем его,  если нет то кидаем инфо об ошибке пакуем в dto
        return productConverter.entityToDto(product);
    }

//    @DeleteMapping ("/{id}")
//    public void deleteById(@PathVariable Long id) {
//        productService.deleteById(id);
//    }


    @PutMapping()
    public ProductDto updateProducts (@RequestParam ProductDto productDto) {
        // дастаем продукт вносим изменения которые были внесены к dto
        Product product = productService.update(productDto);

        // возвращаем
        return productConverter.entityToDto(product);
    }



    }



