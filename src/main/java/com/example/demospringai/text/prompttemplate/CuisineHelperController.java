package com.example.demospringai.text.prompttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demospringai.services.AiService;
@Controller
public class CuisineHelperController {
	@Autowired
    private AiService chatService;

    @GetMapping("/showCuisineHelper")
    public String showChatPage() {
         return "cuisineHelper";
    }

    @PostMapping("/cuisineHelper")
    public String getChatResponse(@RequestParam("country") String country, @RequestParam("numCuisines") String numCuisines,@RequestParam("language") String language,Model model) {
        CountryCuisines countryCuisines = chatService.getCuisines(country,numCuisines,language);
        model.addAttribute("countryCuisines",countryCuisines);
        return "cuisineHelper";
    }
}
