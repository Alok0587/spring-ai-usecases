package com.example.demospringai.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospringai.services.AiService;
import reactor.core.publisher.Flux;

@RestController
public class AnswerAnyThingStreamingController {

	@Autowired
	AiService service;

	@GetMapping("/stream")
	public Flux<String> askAnything(@RequestParam("message") String message) {
		return service.streamAnswer(message);
	}

}