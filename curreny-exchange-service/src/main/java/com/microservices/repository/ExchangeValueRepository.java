package com.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.bean.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Integer> {
	ExchangeValue findByFromAndTo(String from, String to);
}
