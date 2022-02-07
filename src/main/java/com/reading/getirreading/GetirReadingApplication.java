package com.reading.getirreading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/*
	@Author Muhammet Feyzi Saglam
	05.02.2022
 */

@EnableSwagger2
@SpringBootApplication
public class GetirReadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetirReadingApplication.class, args);
		System.out.println("basladi");
	}

}
