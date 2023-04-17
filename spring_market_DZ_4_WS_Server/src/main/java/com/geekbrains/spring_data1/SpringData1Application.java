package com.geekbrains.spring_data1;

import com.geekbrains.spring_data1.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/*

 */

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringData1Application  {
	public static void main(String[] args) {
		SpringApplication.run(SpringData1Application.class, args);
	}
}


