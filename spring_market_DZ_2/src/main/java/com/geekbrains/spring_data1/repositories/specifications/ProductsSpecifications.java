package com.geekbrains.spring_data1.repositories.specifications;


import com.geekbrains.spring_data1.entites.Product;
import org.springframework.data.jpa.domain.Specification;

// спецификация критерий поиска
public class ProductsSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(Integer price) {
      //  цена больше либо равна чему то criteriaBuilder.greaterThanOrEqualTo это инфо из конря root.get("price") указывается в названии метода <Product>
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLessThanOrEqualsThan(Integer price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> titleLike(String titlePart) {
        // String.format("%%%s%%", namePart)) 2й процент это икранированный процент будет поиск по начальному названия Pro...
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

}
