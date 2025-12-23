package com.schoolar.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableRetry
@EnableAsync
@EnableCaching
@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run( WebApplication.class, args);
	}

}
