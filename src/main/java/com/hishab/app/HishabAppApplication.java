package com.hishab.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HishabAppApplication {

	public static void main(String[] args) {

		
		SpringApplication.run(HishabAppApplication.class, args);
	}
}