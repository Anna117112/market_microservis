package com.geekbrains.spring_data1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
Реализуйте корзину на REST API, пока что в виде синглтон бина.
через api работает но не могу сделать чтобы на фронт выводился спсок из корзины . Не пойму что не так делаю . Подскажите пожалуйста
 */

@SpringBootApplication
public class SpringData1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringData1Application.class, args);
	}

}
