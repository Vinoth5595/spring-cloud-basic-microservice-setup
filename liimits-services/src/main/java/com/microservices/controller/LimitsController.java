package com.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.beans.LimitsBean;
import com.microservices.config.LimitsConfig;

@RestController
public class LimitsController {

	@Autowired
	private LimitsConfig limitsConfig;

	@GetMapping("limits")
	public LimitsBean getLimitsConfiguration() {
		return new LimitsBean(limitsConfig.getMinimum(), limitsConfig.getMaximum());
	}
}
