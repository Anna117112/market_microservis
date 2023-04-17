package com.geekbrains.spring_data1.repositories;


import com.geekbrains.spring_data1.entites.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
    // поиск заказов по имени пользователя
    @Query("select o from Order o where o.user.username = ?1")
    List<Order> findAllByUsername(String username);
}
