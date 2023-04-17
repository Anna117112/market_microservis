//package com.geekbrains.spring.web;
//
//
//import com.geekbrains.spring.web.core.services.ProductsService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//
//
//import java.util.Optional;
////
//// это значит что работаем в Spring Boot окружении
////(classes = CartService.class) - эта строчка означает что для работы этоготеста нужен только этот бин CartService - ускоряет работу
//    @SpringBootTest
//    public class CartTesr {
//        // внедряем бинн CartService
//        @Autowired
//        private CartService cartService;
////  можем создать обект заглушку lesson_6time 49:00
//        @MockBean
//        private ProductsService productsService;
//
//
//  //  String cartKey = "test_cart";
//
//        @BeforeEach
//        public void initCart() {
//            cartService.clearCart("test_cart");
//        }
////    @BeforeEach
////    public void setUp() {
////        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
////        Mockito.doNothing().when(valueOperations).set(cartKey, cartKey);
////    }
//
//        @Test
//        public void addToCartTest() {
//            Product product = new Product();
//            product.setId(5L);
//            product.setTitle("X");
//            product.setPrice(100);
//
//
//
//
//
////            OrderItemDto itemDto = new OrderItemDto();
////            itemDto.setProductId(5l);
////            itemDto.setProductTitle("X");
////            itemDto.setPrice(10);
////            itemDto.setQuantity(1);
////            itemDto.setPricePerProduct(product.getPrice());
////            List<OrderItemDto> orderItemDtos = new ArrayList<>();
////            orderItemDtos.add(itemDto);
////            Cart cart = new Cart();
////            cart.setItems(orderItemDtos);
////            cart.setTotalPrice(20);
//
////когда у (productsService). вызовут метод findById(5L);productsService вернет обект Optional(product)
//            // как задано в (productsService)
//            Mockito.doReturn(Optional.of(product)).when(productsService).findById(5L);
//
//            cartService.addToCart("test_cart", 5L);
//            cartService.addToCart("test_cart", 5L);
//            cartService.addToCart("test_cart", 5L);
//
//// проверяем что метод findById был вызван 1 раз, так как мы добовляли товар который уже положили в карзину
//            // 1 раз при повторном вызове проверяем корзину и увеличиваем счетчик на 1
//    //        Mockito.verify(productsService,Mockito.times(1)).findById(ArgumentMatchers.eq(5L));
//            //когда смросим у корзиты количество item должен быть 1
//            Assertions.assertEquals(1, cartService.getCurrentCart("test_cart").getItems().size());
//        }
//@Test
//    public void removeItemFromCart(){
//    Product milk = new Product();
//    milk.setId(1L);
//    milk.setTitle("X");
//    milk.setPrice(100);
//
//    Product bread = new Product();
//    bread.setId(2L);
//    bread.setTitle("X");
//    bread.setPrice(100);
//    Mockito.doReturn(Optional.of(milk)).when(productsService).findById(1L);
//    Mockito.doReturn(Optional.of(bread)).when(productsService).findById(2L);
//    cartService.addToCart("test_cart",1l);
//    cartService.addToCart("test_cart",2l);
//    cartService.removeItemFromCart("test_cart",1l);
//    Assertions.assertEquals(1, cartService.getCurrentCart("test_cart").getItems().size());
//}
//@Test
//public void decrementItem(){
//    Product product = new Product();
//    product.setId(5L);
//    product.setTitle("X");
//    product.setPrice(100);
//    Mockito.doReturn(Optional.of(product)).when(productsService).findById(5L);
//
//    cartService.addToCart("test_cart", 5L);
//    cartService.addToCart("test_cart", 5L);
//    cartService.decrementItem("test_cart",5l);
//    Assertions.assertEquals(100, cartService.getCurrentCart("test_cart").getTotalPrice());
//}
//@Test
//public void merge(){
//            // чистим  корзины если они есть елс их нет они создаются
//    cartService.clearCart("user_cart");
//    cartService.clearCart("guest_cart");
//    Product milk = new Product();
//    milk.setId(1L);
//    milk.setTitle("X");
//    milk.setPrice(100);
//
//    Product bread = new Product();
//    bread.setId(2L);
//    bread.setTitle("X");
//    bread.setPrice(100);
//    Mockito.doReturn(Optional.of(milk)).when(productsService).findById(1L);
//    Mockito.doReturn(Optional.of(bread)).when(productsService).findById(2L);
//
//    cartService.addToCart("user_cart",1l);
//    cartService.addToCart("guest_cart",2l);
//    cartService.merge("user_cart","guest_cart");
//    Assertions.assertEquals(2, cartService.getCurrentCart("user_cart").getItems().size());
//}
//    }
//
