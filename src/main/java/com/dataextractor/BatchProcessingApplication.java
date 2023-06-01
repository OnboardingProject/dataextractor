package com.dataextractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BatchProcessingApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BatchProcessingApplication.class, args);
	}
}
