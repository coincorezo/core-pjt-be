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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
@Tag(name = "CoinController", description = "코인 API")
public class CoinController {

    @Autowired
    CoinService coinService;

    /**
     * <pre>
     *  기간별,  코인 type별  코인 사용 내역
     * </pre>
     *
     * @param : pointsHistoryReqDto
     * @return : pointsHistoryResDto
     * @throws :
     * @author : KangMinJi (kmj0701@coremethod.co.kr)
     * @date : 2024-05-24
     */
    @GetMapping("/user/coin")
    @Operation(summary = "코인 조회", description = "user 코인을 조회한다")
    public ApiResponse<List<PointsHistoryResDto>> getCointSeach(PointsHistoryReqDto pointsHistoryReqDto) {
        List<PointsHistoryResDto> pointsHistoryResDto = new ArrayList<PointsHistoryResDto>();
        pointsHistoryResDto = coinService.getCoinSearch(pointsHistoryReqDto);

        return ApiResponse.ok(pointsHistoryResDto);
    }

    /**
     * <pre>
     *  코인 적립 & 사용  이력 저장
     * </pre>
     *
     * @param : coinReqDto
     * @return : coinResDto
     * @throws :
     * @author : KangMinJi (kmj0701@coremethod.co.kr)
     * @date : 2024-05-24
     */
    @PostMapping("/user/coin")
    @Operation(summary = "코인 조회", description = "user 코인 적립 & 사용 이력을 저장한다")
    public ApiResponse<CreateCoinResDto> saveCoin(@RequestBody CreateCoinReqDto coinReqDto) {
        CreateCoinResDto coinResDto = coinService.saveCoin(coinReqDto);

        return ApiResponse.ok(coinResDto);
    }

    @GetMapping("/user/coin/mycoin")
    @Operation(summary = "나의 코인 조회", description = "user total 코인을 조회한다")
    public ApiResponse<PointsHistoryResDto> getMyCoin(PointsHistoryReqDto pointsHistoryReqDto) {
        PointsHistoryResDto reqDto = new PointsHistoryResDto();
        reqDto = coinService.getMyCoin(pointsHistoryReqDto);

        return ApiResponse.ok(reqDto);
    }

}
