package com.shinhan.knockknock.domain.dto;

import com.shinhan.knockknock.domain.entity.CardCategoryEntity;
import com.shinhan.knockknock.domain.entity.CardEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadCardHistoryResponse {
    @Schema(example = "55000")
    private int cardHistoryAmount;
    @Schema(example = "올리브영 연남점")
    private String cardHistoryShopname;
    @Schema(example = "2024-08-08 17:30")
    private Timestamp cardHistoryApprove;
    @Schema(example = "1")
    private CardCategoryEntity cardCategoryNo;
    @Schema(example = "1")
    private CardEntity cardId;
}
