package com.shinhan.knockknock.domain.dto.conversation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResponse {

    private String content;
    private String audioData;
    private boolean actionRequired;
    private int totalTokens;

    private RedirectionResponse redirectionResult;
    private ReservationResponse reservationResult;

}
