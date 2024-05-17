package com.pjt.core.coin.service;

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
}
