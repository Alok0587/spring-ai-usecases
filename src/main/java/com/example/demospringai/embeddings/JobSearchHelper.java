package com.example.demospringai.embeddings;

import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demospringai.services.OpenAiService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class JobSearchHelper {

	@Autowired
	private OpenAiService service;

	@GetMapping("/showJobSearchHelper")
	public String showJobSearchHelper() {
		return "jobSearchHelper";

	}

	@PostMapping("/jobSearchHelper")
	public String jobSearchHelper(@RequestParam String query, Model model) {

		List<Document> response= service.searchJobs(query);
		model.addAttribute("response", response);

		return "jobSearchHelper";

	}

}