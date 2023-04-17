package com.geekbrains.spring.web.statistic.repositories;

import com.geekbrains.spring.web.statistic.entites.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
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
public interface TopProductCartRepository extends JpaRepository<Cart, Long> {
    //    @Query("select p from Product p where p.title = ?1")
//    Optional<Product> findByName(String title);
// если прописываем запросы sql то ставим value = и nativeQuery = true
    @Query(value = "select *  from  Cart order by quantity desc limit 5 ", nativeQuery = true)

    List<Cart> findTopCart();

    //Optional<TopProductCart> findByTitle(String title);


    @Query( value = "select * from Cart  where title = ?1", nativeQuery = true)

    Cart findByName(String title);
    //  Query(value = "select *  from  TopProductCart  where title =  ", nativeQuery = true)
    // List<TopProductCart> findTopCart();
// поиск по названию метода прописываем этоо медот в сервисе и контр
}