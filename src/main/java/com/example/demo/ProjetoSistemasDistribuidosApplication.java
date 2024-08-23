package com.example.demo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ProjetoSistemasDistribuidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSistemasDistribuidosApplication.class, args);
	}

}
