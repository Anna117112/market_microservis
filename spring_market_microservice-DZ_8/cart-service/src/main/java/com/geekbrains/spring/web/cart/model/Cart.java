package com.geekbrains.spring.web.cart.model;

import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<com.geekbrains.spring.web.cart.models.CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(ProductDto productDto) {
        if (add(productDto.getId())) {
            return;
        }
        items.add(new com.geekbrains.spring.web.cart.models.CartItem(productDto));
        recalculate();
    }

    public boolean add(Long id) {
        for (com.geekbrains.spring.web.cart.models.CartItem o : items) {
            if (o.getProductId().equals(id)) {
                o.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void decrement(Long productId) {
        Iterator<com.geekbrains.spring.web.cart.models.CartItem> iter = items.iterator();
        while (iter.hasNext()) {
            com.geekbrains.spring.web.cart.models.CartItem o = iter.next();
            if (o.getProductId().equals(productId)) {
                o.changeQuantity(-1);
                if (o.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        items.removeIf(o -> o.getProductId().equals(productId));
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = 0;
    }

    private void recalculate() {
        totalPrice = 0;
        for (com.geekbrains.spring.web.cart.models.CartItem o : items) {
            totalPrice += o.getPrice();
        }
    }

    public void merge(Cart another) {
        for (com.geekbrains.spring.web.cart.models.CartItem anotherItem : another.items) {
            boolean merged = false;
            for (com.geekbrains.spring.web.cart.models.CartItem myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())) {
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }
}
