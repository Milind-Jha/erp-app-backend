package com.milind.deprtmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DeprtmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeprtmentServiceApplication.class, args);
	}

}
