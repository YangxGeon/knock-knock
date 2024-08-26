package com.shinhan.knockknock.domain.dto.welfarebook;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWelfareBookRequest {

    @Schema(example = "2024-08-08 09:00")
    @NotNull
    private Date welfareBookStartDate;

    @Schema(example = "2024-08-08 13:00")
    @NotNull
    private Date welfareBookEndDate;

    @Schema(example = "false")
    private boolean welfareBookIsCansle;

    @Schema(example = "false")
    private boolean welfareBookIsComplete;

    @Schema(example = "1")
    @NotNull
    private Long userNo;

    @Schema(example = "19550813")
    @NotNull
    private Date userBirth;

    @Schema(example = "서울시 마포구 월드컵대로")
    @NotBlank
    private String userAddress;

    @Schema(example = "2")
    @NotNull
    private int userGender;

    @Schema(example = "161")
    @NotNull
    private int userHeight;

    @Schema(example = "54")
    @NotNull
    private int userWeight;

    @Schema(example = "고혈압")
    @NotBlank
    private String userDisease;

    @Schema(example = "75")
    @NotNull
    private Long welfareNo;

    @Schema(example = "가정간병")
    @NotBlank
    private String welfareName;

    @Schema(example = "6000")
    @NotNull
    private Long welfarePrice;

    @Schema(example = "3")
    @NotNull
    private Integer welfareBookUseTime;
}
