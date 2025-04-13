package com.example.demospringai.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demospringai.services.OpenAiService;
@RestController
public class AnswerAnyThingStreamingController {

	@Autowired
	OpenAiService service;

}