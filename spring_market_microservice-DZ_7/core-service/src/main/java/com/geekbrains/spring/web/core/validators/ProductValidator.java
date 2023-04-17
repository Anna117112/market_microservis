package com.geekbrains.spring.web.core.validators;


import com.geekbrains.spring.web.core.dto.ProductDto;
import com.geekbrains.spring.web.core.exceptions.ValidationException;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice() < 1) {
            errors.add("Цена продукта не может быть меньше 1");
        }
        // если поле пустое
        if (productDto.getTitle().isBlank()) {
            errors.add("Продукт не может иметь пустое название");
        }
        if (!errors.isEmpty()) {
            // если список не пустой передаем его в обработчик ошибок созданный нами класс
            throw new ValidationException(errors);
        }
    }
}
