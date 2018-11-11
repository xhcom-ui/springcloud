package com.citydo.microcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka_StartSpringCloudApplication {
	public static void main(String[] args) {
		SpringApplication.run(Eureka_StartSpringCloudApplication.class, args);
	}
}
