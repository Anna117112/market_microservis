package com.geekbrains.spring_data1.services;


import com.geekbrains.spring_data1.dto.Cart;
import com.geekbrains.spring_data1.dto.OrderDetailsDto;
import com.geekbrains.spring_data1.entites.Order;
import com.geekbrains.spring_data1.entites.OrderItem;
import com.geekbrains.spring_data1.entites.User;
import com.geekbrains.spring_data1.exceptions.ResourceNotFoundException;
import com.geekbrains.spring_data1.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
   private final OrdersRepository ordersRepository;
   private final CartService cartService;
   private final ProductsService productsService;
// создаем заказ. Если произойдет ошибка то заказ не сохранится транзакция не пройдет
   @Transactional
   public void createOrder(User user, OrderDetailsDto orderDetailsDto) {
       // получаем текущую корзину
      Cart currentCart = cartService.getCurrentCart();
      Order order = new Order();
      order.setAddress(orderDetailsDto.getAddress());
      order.setPhone(orderDetailsDto.getPhone());
      order.setUser(user);
      // берем из корзины
      order.setTotalPrice(currentCart.getTotalPrice());
      // у текущей карзины запрашиваем лист продуктов
      List<OrderItem> items = currentCart.getItems().stream()
              .map(o -> {
                 OrderItem item = new OrderItem();
                 // привязали к заказу
                 item.setOrder(order);
                 item.setQuantity(o.getQuantity());
                 item.setPricePerProduct(o.getPricePerProduct());
                 item.setPrice(o.getPrice());
                 item.setProduct(productsService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
                 return item;
              }).collect(Collectors.toList());
      order.setItems(items);
      ordersRepository.save(order);
      // после оформления заказа чистим текущую корзину
      currentCart.clear();
   }

   public List<Order> findOrdersByUsername(String username) {
       return ordersRepository.findAllByUsername(username);
   }
}
