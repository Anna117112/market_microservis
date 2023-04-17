package com.geekbrains.spring.web.core.services;



import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.core.dto.Cart;
import com.geekbrains.spring.web.core.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Consumer;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductsService productsService;
    // чтобы сожно было посылать в Redis запросы и получать ответы
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${utils.cart.prefix}")
    // чтобы наши отекты не перемешались с другими добавляем префикс
    private String cartPrefix;
    // метод формирует имя корзны из префикса и суф
    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }
    // генерируем случайный набо символов и возвращаем клиенту
    public String generateCartUuid() {
        return UUID.randomUUID().toString();
    }

    public Cart getCurrentCart(String cartKey) {
        // если в редисе нет такой корзиты то создаем
        if (!redisTemplate.hasKey(cartKey)) {
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }

    public void addToCart(String cartKey, Long productId) {
        // Get получаем обект корзина из редиса
        // Update делаем изменения добав товар, удаляем и тд
        // Set записали обратно в редис
        Product product = productsService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину. Продукт не найдет, id: " + productId));
        execute(cartKey, c -> {
            c.add(product);
        });
    }

    public void clearCart(String cartKey) {
        execute(cartKey, Cart::clear);
    }
    // удаление товара из корзины
    public void removeItemFromCart(String cartKey, Long productId) {
        execute(cartKey, c -> c.remove(productId));
    }
    // уменьшить item
    public void decrementItem(String cartKey, Long productId) {
        execute(cartKey, c -> c.decrement(productId));
    }
    // пользователь как гость - одна корзина
    // пользователь под своей учеткой другая корзина
    // это метод  склеивает 2 корзины при заходе как авторизованный
    public void merge(String userCartKey, String guestCartKey) {
        //текущая гостевая корзина
        Cart guestCart = getCurrentCart(guestCartKey);
        // корзина авторизировнного пользователя
        Cart userCart = getCurrentCart(userCartKey);
        // в корзину пользователя добавляем все продукты из гостевой корзины
        userCart.merge(guestCart);
        updateCart(guestCartKey, guestCart);
        // обновляем корзину клиента
        updateCart(userCartKey, userCart);
    }
    // Get, Update, Set описнные выше
    private void execute(String cartKey, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartKey);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    public void updateCart(String cartKey, Cart cart) {
        redisTemplate.opsForValue().set(cartKey, cart);
    }
}