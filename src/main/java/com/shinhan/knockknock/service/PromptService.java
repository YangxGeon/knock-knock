package com.shinhan.knockknock.service;

import com.shinhan.knockknock.domain.dto.conversationroom.ConversationLogResponse;
import com.shinhan.knockknock.exception.ChatbotException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PromptService {

    private final ResourceLoader resourceLoader;

    public PromptService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<Map<String, String>> classificationPrompt(String input) {
        String classificationPrompt = loadPrompt("prompts/classification.prompt");

        List<Map<String, String>> messagesList = new ArrayList<>();

        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", classificationPrompt);
        messagesList.add(systemMessage);

        // 사용자 입력 메시지 추가
        Map<String, String> userMessage1 = new HashMap<>();
        userMessage1.put("role", "user");
        userMessage1.put("content", input);
        messagesList.add(userMessage1);

        return messagesList;
    }

    public List<Map<String, String>> chatbotPrompt(List<String> promptFilePathList, String input, List<ConversationLogResponse> conversationLogs) {
        String systemPrompt = loadPrompts(promptFilePathList);

        List<Map<String, String>> messagesList = new ArrayList<>();

        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", systemPrompt);
        messagesList.add(systemMessage);

        // 대화 로그 추가
        for (ConversationLogResponse log : conversationLogs) {
            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", log.getConversationLogInput());
            messagesList.add(userMessage);

            Map<String, String> assistantMessage = new HashMap<>();
            assistantMessage.put("role", "assistant");
            assistantMessage.put("content", log.getConversationLogResponse());
            messagesList.add(assistantMessage);
        }

        // 사용자 입력 메시지 추가
        Map<String, String> userMessage1 = new HashMap<>();
        userMessage1.put("role", "user");
        userMessage1.put("content", input);
        messagesList.add(userMessage1);

        return messagesList;
    }

    public String loadPrompts(List<String> promptFilePathList) {
        try {
            return promptFilePathList.stream()
                    .map(this::loadPrompt)
                    .collect(Collectors.joining("\n"));
        } catch (RuntimeException e) {
            throw new ChatbotException("Failed to load prompts", e);
        }
    }

    public String loadPrompt(String promptFilePath) {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + promptFilePath);
            Path path = Paths.get(resource.getURI());
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ChatbotException("Failed to load prompt from: " + promptFilePath, e);
        }
    }
}
