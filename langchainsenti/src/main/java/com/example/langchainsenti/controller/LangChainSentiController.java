package com.example.langchainsenti.controller;

import com.example.langchainsenti.dto.FormInputDTO;
import com.example.langchainsenti.service.ChatService;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class LangChainSentiController {
    @Autowired
    private ChatService chatService;
    @Value("${openai.api.key}")
    private String openaiApiKey;

    //http://localhost:8080/prompt?prompt="Hello world!"
    @GetMapping("/prompt")

    public String getMessage(@RequestParam String prompt) {
        // Create an instance of a model
        ChatLanguageModel model = OpenAiChatModel.withApiKey(openaiApiKey);
        // Start interacting
        String answer = model.generate(prompt);
        return answer;
    }
    @GetMapping("/review")
    public ModelAndView loadChatPage(@ModelAttribute FormInputDTO formInputDTO) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @PostMapping("/")
    public ModelAndView getResponseFromOpenAiAPi(@ModelAttribute FormInputDTO formInputDTO) {

        Optional<String> response = chatService.callToOpenAiApi(formInputDTO.getPrompt());
        ModelAndView mv = new ModelAndView();
        mv.addObject("response", response.get());
        mv.setViewName("index");
        return mv;
    }
}
