package com.shinhan.knockknock.service;

import com.shinhan.knockknock.domain.dto.conversationroom.ChatbotResponse;
import com.shinhan.knockknock.domain.dto.conversationroom.ConversationLogRequest;
import com.shinhan.knockknock.domain.dto.conversationroom.ConversationLogResponse;
import com.shinhan.knockknock.domain.dto.conversationroom.ConversationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ConversationService {

    @Autowired
    ChatbotService chatbotService;

    @Autowired
    TextToSpeechService textToSpeechService;
    @Autowired
    ConversationLogService conversationLogService;

    public byte[] conversation(ConversationRequest request) {
        log.info("📌 Received conversation request: input={}, conversationRoomNo={}", request.getInput(), request.getConversationRoomNo());

        // 이전 대화내용 조회
        List<ConversationLogResponse> conversationLogs = conversationLogService.readByConversationRoomId(request.getConversationRoomNo());

        // Chatbot 답변 생성
        ChatbotResponse response = chatbotService.chatbot(request, conversationLogs);

        // 대화 내역 저장
        ConversationLogRequest conversationLog = ConversationLogRequest.builder()
                .conversationLogInput(request.getInput())
                .conversationLogResponse(response.getContent())
                .conversationLogToken(response.getTotalTokens())
                .conversationRoomNo(request.getConversationRoomNo())
                .build();
        conversationLogService.createConversationLog(conversationLog);

        // 음성 데이터 생성
        byte[] audioData = textToSpeechService.convertTextToSpeech(response.getContent());

        log.info("📌 Chatbot response: content={}, totalTokens={}", response.getContent(), response.getTotalTokens());

        return audioData;
    }

}
