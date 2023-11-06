package com.example.langchainsenti.service;

import java.util.Optional;

public interface ChatService {
    public Optional<String> callToOpenAiApi(String prompt);

}
