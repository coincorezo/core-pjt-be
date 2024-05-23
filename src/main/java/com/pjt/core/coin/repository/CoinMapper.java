package com.pjt.core.coin.repository;

import com.pjt.core.coin.dto.CreateCoinReqDto;
import com.pjt.core.coin.dto.PointsHistoryReqDto;
import com.pjt.core.coin.dto.PointsHistoryResDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoinMapper {

    void saveCoin(CreateCoinReqDto coinReqDto);

    PointsHistoryResDto getCointSearch(PointsHistoryReqDto pointsHistoryReqDto);
}
