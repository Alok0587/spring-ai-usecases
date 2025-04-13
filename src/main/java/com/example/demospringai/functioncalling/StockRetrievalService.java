package com.example.demospringai.functioncalling;

import java.util.function.Function;

public class StockRetrievalService implements Function<StockRetrievalService.Request, StockRetrievalService.Response> {

	public record Request(String symbol) {
	}

	public record Response(Double price) {
	}

	@Override
	public Response apply(Request t) {
		return new Response(5000D);
	}

}