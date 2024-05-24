package com.pjt.core.coin.repository;

import com.pjt.core.coin.dto.CreateCoinReqDto;
import com.pjt.core.coin.dto.PointsHistoryReqDto;
import com.pjt.core.coin.dto.PointsHistoryResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CoinMapper {

    void saveCoin(CreateCoinReqDto coinReqDto);

    List<PointsHistoryResDto> getCointSearch(PointsHistoryReqDto pointsHistoryReqDto);
    
    PointsHistoryResDto getMyCoin(PointsHistoryReqDto pointsHistoryReqDto);
}
