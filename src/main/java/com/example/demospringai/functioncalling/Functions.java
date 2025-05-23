package com.example.demospringai.functioncalling;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration
public class Functions {

	@Bean
	@Description("Gets the stock price given a symbol")
	Function<StockRetrievalService.Request, StockRetrievalService.Response> stockRetrievalFunction() {
		return new StockRetrievalService();
	}

}