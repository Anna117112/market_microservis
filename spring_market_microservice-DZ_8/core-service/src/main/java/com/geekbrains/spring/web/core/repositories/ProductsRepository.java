package com.geekbrains.spring.web.core.repositories;

import com.geekbrains.spring.web.core.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
// создание запросов
// если запросы круд операций пропсываать не надо они сами создаются
// можно по названию если понятное
// через @Query
public interface ProductsRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
// указываем с какой сущьностью будем работаь и какого типа первичный ключ<Product, Long>
// для работы со спецификацией JpaSpecificationExecutor

// @Query("select s from Student s where s.score between ?1 and ?2")
// поиск по названию метода прописываем этоо медот в сервисе и контр
//    List<Product> findAllByCostBetween(Integer min, Integer max);
//

