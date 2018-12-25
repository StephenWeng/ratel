package com.ratel;

import java.nio.charset.Charset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProtalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtalApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return converter;
	}

}
