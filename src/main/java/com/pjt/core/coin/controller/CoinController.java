package com.pjt.core.coin.controller;

import com.pjt.core.coin.service.CoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Tag(name = "CoinController", description = "코인 API")
public class CoinController {

    @Autowired
    CoinService coinService;

//    @GetMapping("/user/coin")
//    @Operation(summary="코인 조회", description = "user 코인을 조회합니다")
//    public


}
