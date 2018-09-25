package com.example.demo;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HouseAgentsSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseAgentsSystemApplication.class, args);
	}
}
