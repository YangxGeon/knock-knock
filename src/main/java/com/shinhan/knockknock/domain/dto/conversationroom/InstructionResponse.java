package com.shinhan.knockknock.domain.dto.conversationroom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructionResponse {
    private boolean actionRequired;
    private String serviceNumber;
}
