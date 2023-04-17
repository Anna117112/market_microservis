package com.geekbrains.spring_data1.aop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data

// конструктор со всеми аргументами

// Аспект который будет выводить в лог время выполнения метода
@Aspect
@Component
public class LogEsecutionTimeAspect {



    public static long timeProductsService =0;
    public static long timeOrderService =0;
    public static long timeUserService =0;
    public static InfoAspect infoAspectProduct = new InfoAspect(" Время работы методов ProductsService ",timeProductsService );
    public static InfoAspect infoAspectOrder = new InfoAspect(" Время работы методов OrdersService ",timeOrderService);
    public static InfoAspect infoAspectUser = new InfoAspect(" Время работы методов UsersService ",timeUserService );
    public static List<InfoAspect> infoAspectList = new ArrayList<>(Arrays.asList(infoAspectProduct,infoAspectOrder,infoAspectUser ));

    // выводит инфо до и после выполениня метода
    @Around("execution( * com.geekbrains.spring_data1.services.ProductsService.*(..))")
    public Object TimeProductsService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();
        long duration = end - begin;
        timeProductsService +=duration;
        System.out.println( " Время работы методов ProductsService " + timeProductsService);
        infoAspectProduct.setTime(timeProductsService);


       //("Времы выполения методов сервиса{} заняло {} mc.", proceedingJoinPoint.getSignature(),System.currentTimeMillis() - begin);
        return out;
    }
    @Around("execution( * com.geekbrains.spring_data1.services.OrderService.*(..))")
    public Object TimeOrderService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();
        long duration = end - begin;
        timeOrderService +=duration;
        System.out.println( " Время работы методов OrderService " + timeOrderService);
        infoAspectOrder.setTime(timeOrderService);

        //("Времы выполения методов сервиса{} заняло {} mc.", proceedingJoinPoint.getSignature(),System.currentTimeMillis() - begin);
        return out;
    }
    @Around("execution( * com.geekbrains.spring_data1.services.UserService.*(..))")
    public Object TimeUserService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();
        long duration = end - begin;
        timeUserService +=duration;
        System.out.println( " Время работы методов UserService " + timeUserService);
       infoAspectUser.setTime(timeUserService);
        //("Времы выполения методов сервиса{} заняло {} mc.", proceedingJoinPoint.getSignature(),System.currentTimeMillis() - begin);
        return out;
    }


}





