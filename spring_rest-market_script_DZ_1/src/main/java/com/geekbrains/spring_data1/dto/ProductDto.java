package com.geekbrains.spring_data1.dto;

import com.geekbrains.spring_data1.entites.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Data
@NoArgsConstructor
// конструктор со всеми аргументами
@AllArgsConstructor
public class ProductDto {

    public long id;


    public String name;


    public Integer cost;




    @Override
    public String toString() {
        return "ProductDto{" +
                "id='" + id + '\'' +
                ", title='" + name + '\'' + ", cost = " + cost +
                '}';
    }
}
