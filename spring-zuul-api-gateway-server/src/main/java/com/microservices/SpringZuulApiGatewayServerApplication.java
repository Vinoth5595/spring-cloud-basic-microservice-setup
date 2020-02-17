package com.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SpringZuulApiGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringZuulApiGatewayServerApplication.class, args);
	}

}
