package com.geekbrains.spring_data1.repositories;

import com.geekbrains.spring_data1.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
// создание запросов
// если запросы круд операций пропсываать не надо они сами создаются
// можно по названию если понятное
// через @Query

@Repository
// указываем с какой сущьностью будем работаь и какого типа первичный ключ<Product, Long>
// для работы со спецификацией JpaSpecificationExecutor
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    // @Query("select s from Student s where s.score between ?1 and ?2")
// поиск по названию метода прописываем этоо медот в сервисе и контр
//    List<Product> findAllByCostBetween(Integer min, Integer max);
//
//
//    // @Quer поиск если не стандартный запрос
//    // продукт у которых цена больше мин
//    @Query("select p from Product p where p.cost > (select min(cost) from Product)")
//    List<Product> findAllProductMoreMinCost();
//    // продукт у которых цена меньше макс
//    @Query("select p from Product p where p.cost < (select max(cost) from Product)")
//    List<Product> findAllProductLessMaxCost();
//   // Optional<Student> findByIdWithBooks(String name);
}
