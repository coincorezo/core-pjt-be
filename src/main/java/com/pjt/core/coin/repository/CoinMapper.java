package com.pjt.core.coin.repository;

import com.pjt.core.coin.dto.CreateCoinReqDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoinMapper {

    void saveCoin(CreateCoinReqDto coinReqDto);
}
