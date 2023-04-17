package com.geekbrains.spring_data1.services.specifications;


import com.geekbrains.spring_data1.entites.Product;
import org.springframework.data.jpa.domain.Specification;

// спецификация критерий поиска
public class ProductsSpecifications {
    public static Specification<Product> costGreaterOrEqualsThan(Integer cost) {
      //  цена больше либо равна чему то criteriaBuilder.greaterThanOrEqualTo это инфо из конря root.get("price") указывается в названии метода <Product>
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> costLessThanOrEqualsThan(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> nameLike(String namePart) {
        // String.format("%%%s%%", namePart)) 2й процент это икранированный процент будет поиск по начальному названия Pro...
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart));
    }
}
