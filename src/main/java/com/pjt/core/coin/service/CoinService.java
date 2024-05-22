package com.pjt.core.coin.service;

import com.pjt.core.coin.dto.CreateCoinReqDto;
import com.pjt.core.coin.dto.CreateCoinResDto;
import com.pjt.core.coin.dto.PointsHistoryReqDto;
import com.pjt.core.coin.dto.PointsHistoryResDto;
import com.pjt.core.coin.repository.CoinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinService {
    @Autowired
    CoinMapper coinMapper;

    public PointsHistoryResDto getCoinSearch(PointsHistoryReqDto pointsHistoryReqDto) {
        PointsHistoryResDto pointsHistoryResDto = new PointsHistoryResDto();

        return pointsHistoryResDto;
    }

    public CreateCoinResDto saveCoin(CreateCoinReqDto coinReqDto) {
        CreateCoinResDto coinResDto = new CreateCoinResDto();
        coinMapper.saveCoin(coinReqDto);


        return coinResDto;
    }
}
