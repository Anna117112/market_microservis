package com.geekbrains.spring_data1.converter;

import com.geekbrains.spring_data1.dto.ProductDto;
import com.geekbrains.spring_data1.entites.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    // преобразуем в продукт
    public Product dtoToEntity(ProductDto productDto){
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());

    }
    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());

    }
}
