package com.example.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.HelloWorldBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/hello-world")
	public String helloWorld() {
		return "hello world!!!";
	}
	
	@GetMapping("/hello-world-i18n")
	public String helloWorldI18n() {
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping("/hello-world-bean/{input}")
	public HelloWorldBean helloWorldBean(@PathVariable("input") String inputName) {
		log.debug("inside:"+this.getClass().getSimpleName());;
		return new HelloWorldBean(String.format("hello world!!! %s", inputName));
	}

}
