package com.microservices.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.bean.ExchangeValue;
import com.microservices.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getExchangeValue(@PathVariable("from") String from,@PathVariable("to") String to) {
		
		ExchangeValue findByFromAndTo = exchangeValueRepository.findByFromAndTo(from, to);
		findByFromAndTo.setPort(Integer.parseInt(environment.getProperty("server.port")));
		return findByFromAndTo; 
	}
}
