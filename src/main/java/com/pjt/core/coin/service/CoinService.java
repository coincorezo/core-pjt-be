package com.pjt.core.coin.service;

import com.pjt.core.coin.dto.CreateCoinReqDto;
import com.pjt.core.coin.dto.CreateCoinResDto;
import com.pjt.core.coin.dto.PointsHistoryReqDto;
import com.pjt.core.coin.dto.PointsHistoryResDto;
import com.pjt.core.coin.repository.CoinMapper;
import com.pjt.core.user.dto.CurrentUser;
import com.pjt.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinService {
    @Autowired
    CoinMapper coinMapper;

    @Autowired
    UserService userService;


    public PointsHistoryResDto getCoinSearch(PointsHistoryReqDto pointsHistoryReqDto) {
        PointsHistoryResDto pointsHistoryResDto = new PointsHistoryResDto();

        return pointsHistoryResDto;
    }

    public CreateCoinResDto saveCoin(CreateCoinReqDto coinReqDto) {
        CreateCoinResDto coinResDto = new CreateCoinResDto();
        CurrentUser currentUser = userService.getLoginUser();
        if (currentUser.getId().equals(coinReqDto.getUserId())) {
            coinReqDto.setUserId(currentUser.getId());
            // coinReqDto.

        }
        coinMapper.saveCoin(coinReqDto);


        return coinResDto;
    }
}
