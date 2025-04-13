package com.example.demospringai.embeddings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demospringai.services.AiService;


@Controller
public class SimilarityFinder {

	@Autowired
	private AiService service;
	
	@GetMapping("/showSimilarityFinder")
	public String showSimilarityFinder() {
		return "similarityFinder";

	}

	@PostMapping("/similarityFinder")
	public String findSimilarity(@RequestParam String text1,@RequestParam String text2,Model model) {
		Double response = service.findSimilarity(text1, text2);
		model.addAttribute("response", response);
		return "similarityFinder";

	}
	

}