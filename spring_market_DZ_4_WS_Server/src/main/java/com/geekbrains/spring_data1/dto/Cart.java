package com.geekbrains.spring_data1.dto;


import com.geekbrains.spring_data1.entites.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    // лист заказов
    private List<OrderItemDto> items;
    private int totalPrice;
// при зоздании карзины создается пустой лист
    public Cart() {
        this.items = new ArrayList<>();
    }
// добавление по продукту
    public void addProduct(Product product) {
        // если в корзине уже есть товар то увеличиваем кол во и увеличиваем стоимость
        if (addProduct(product.getId())) {
            return;
        }
        // если нет то добавляем позицию
        items.add(new OrderItemDto(product));
        // пересчет стоимости
        recalculate();
    }
// добавление продукта по id
    public boolean addProduct(Long id) {
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(id)) {
                // если продукт нашелся то добавляем кол во метод из класса OrderItemDto
                o.changeQuantity(1);
                // пересчитываем стоимость
                recalculate();
                return true;
            }
        }
        return false;
    }
// уменьшение кол во товаров в корзине
    public void decreaseProduct(Long id) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto o = iter.next();
            if (o.getProductId().equals(id)) {
                o.changeQuantity(-1);
                if (o.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }
// удаление продукта
    public void removeProduct(Long id) {
        items.removeIf(o -> o.getProductId().equals(id));
        recalculate();
    }
// очистка корзины
    public void clear() {
        items.clear();
        totalPrice = 0;
    }

    //итоговая сумма покупок
    private void recalculate() {
        totalPrice = 0;
        for (OrderItemDto o : items) {
            totalPrice += o.getPrice();
        }
    }
}
