package com.nagarro.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InventoryManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagmentApplication.class, args);
	}

}
