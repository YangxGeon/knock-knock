package com.shinhan.knockknock.controller;

import com.shinhan.knockknock.auth.JwtProvider;
import com.shinhan.knockknock.domain.dto.consumption.ReadConsumptionResponse;
import com.shinhan.knockknock.service.consumption.ConsumptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/consumption")
@RequiredArgsConstructor
@Tag(name = "소비리포트", description = "소비리포트 API")
public class ConsumptionController {
    private final ConsumptionService consumptionService;
    private final JwtProvider jwtProvider;

    @Operation(summary = "본인 소비 리포트 조회", description="userNo로 카드번호를 찾고 해당 카드 번호의 조회하고자 하는 월의 소비리포트 조회")
    @GetMapping("/{startDate}/{endDate}")
    public List<ReadConsumptionResponse> ReadConsumption(// 프론트에서 잭슨이 List<ReadConsumptionResponse> 배열을 json으로 파싱
            @RequestHeader("Authorization") String header,
            @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String startDateStr,
            @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String endDateStr) {

        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(startDateStr);
            endDate = LocalDate.parse(endDateStr);

            if (endDate.isBefore(startDate)) {
                throw new IllegalArgumentException("종료 날짜는 시작 날짜보다 이후여야 합니다.");
            }

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("잘못된 날짜 형식입니다: " + e.getParsedString());
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("존재하지 않는 날짜입니다.");
        }

        Long userNo = jwtProvider.getUserNoFromHeader(header);
        return consumptionService.readConsumptionReport(userNo, java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));
    }




}