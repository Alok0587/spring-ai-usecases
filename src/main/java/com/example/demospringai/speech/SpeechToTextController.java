package com.example.demospringai.speech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demospringai.services.AiService;
@Controller
public class SpeechToTextController {

    // Define the folder where images will be saved
    private static final String UPLOAD_DIR = "/Users/bharaththippireddy/Documents/springai/images/uploads/";
    
    @Autowired
    private AiService service;

    // Display the image upload form
    @GetMapping("/showSpeechToText")
    public String showUploadForm() {
        return "speechToText";
    }

    @PostMapping("/speechToText")
    public String uploadImage(String prompt, @RequestParam("file") MultipartFile file, Model model, 
    		RedirectAttributes redirectAttributes) {
        return "speechToText";
    }
}