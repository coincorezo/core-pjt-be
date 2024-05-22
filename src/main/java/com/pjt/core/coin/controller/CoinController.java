package com.pjt.core.coin.controller;

import com.pjt.core.board.dto.CreateBoardResDto;
import com.pjt.core.coin.dto.CreateCoinReqDto;
import com.pjt.core.coin.dto.CreateCoinResDto;
import com.pjt.core.coin.dto.PointsHistoryReqDto;
import com.pjt.core.coin.dto.PointsHistoryResDto;
import com.pjt.core.coin.service.CoinService;
import com.pjt.core.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@Tag(name = "CoinController", description = "코인 API")
public class CoinController {

    @Autowired
    CoinService coinService;

    @GetMapping("/user/coin")
    @Operation(summary = "코인 조회", description = "user 코인을 조회합니다")
    public ApiResponse<PointsHistoryResDto> getCointSeach(PointsHistoryReqDto pointsHistoryReqDto) {

        PointsHistoryResDto pointsHistoryResDto = new PointsHistoryResDto();
        pointsHistoryResDto = coinService.getCoinSearch(pointsHistoryReqDto);
        return ApiResponse.ok(pointsHistoryResDto);
    }

    @PostMapping("/user/coin")
    @Operation(summary = "코인 조회", description = "user 코인을 조회합니다")
    public ApiResponse<CreateCoinResDto> saveCoin(@RequestBody CreateCoinReqDto coinReqDto) {

        CreateCoinResDto coinResDto = coinService.saveCoin(coinReqDto);
        return ApiResponse.ok(coinResDto);
    }


}
