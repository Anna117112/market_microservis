package com.geekbrains.spring_data1.validator;


import com.geekbrains.spring_data1.dto.ProductDto;
import com.geekbrains.spring_data1.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ProductValidator {
    public void validate(ProductDto productDto){
        List<String> errors = new ArrayList<>();
        if (productDto.getCost()<1){
            errors.add("Цена продукта не может быть меньше 1");
            // если поле пустое
            if (productDto.getName().isBlank()){
                errors.add("Продукт не может иметь пустое название ");
            }
            if (!errors.isEmpty()){
                // если список не пустой передаем его в обработчик ошибок созданный нами класс
                throw new ValidationException(errors);
            }

        }
    }

}
