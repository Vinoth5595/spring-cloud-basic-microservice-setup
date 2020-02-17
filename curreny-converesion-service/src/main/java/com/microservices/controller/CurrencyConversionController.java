package com.microservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.bean.CurrencyConversionBean;
import com.microservices.service.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {
	private static final String REST_URI = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";

	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("currency-convertor/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getCurrencyConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversionBean> forEntity = new RestTemplate().getForEntity(REST_URI,
				CurrencyConversionBean.class, uriVariables);
		CurrencyConversionBean response = forEntity.getBody();
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
				response.getQuantity(), quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

	@GetMapping("currency-convertor-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		CurrencyConversionBean response = proxy.getExchangeValue(from, to);
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
				response.getQuantity(), quantity.multiply(response.getConversionMultiple()), response.getPort());
	}
}
