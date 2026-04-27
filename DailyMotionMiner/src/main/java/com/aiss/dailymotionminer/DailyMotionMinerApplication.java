package com.aiss.dailymotionminer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DailyMotionMinerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyMotionMinerApplication.class, args);
	}

	public RestTemplate restTemplate(RestTemplateBuilder builder) { return builder.build(); }
}
