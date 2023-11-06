package com.example.langchainsenti.service;

import com.example.langchainsenti.dto.FormInputDTO;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ChatServiceImpl implements ChatService {

    @Value("${openai.api.key}")
    private String openaiApiKey;
    @Override
    public Optional<String> callToOpenAiApi(String prompt) {
        ChatLanguageModel model = OpenAiChatModel.withApiKey(openaiApiKey);
        // Start interacting
        String answer = model.generate(prompt);
        return answer.describeConstable();
//        return response.firstAnswer();
    }


}
