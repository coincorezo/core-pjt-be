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

    /**
     * <pre>
     *  기간별,  코인 type별  코인 사용 내역
     * </pre>
     *
     * @param pointsHistoryReqDto
     * @return pointsHistoryResDto
     * @author KangMinJi (kmj0701@coremethod.co.kr)
     */
    public PointsHistoryResDto getCoinSearch(PointsHistoryReqDto pointsHistoryReqDto) {
        PointsHistoryResDto pointsHistoryResDto = new PointsHistoryResDto();

        CreateCoinResDto coinResDto = new CreateCoinResDto();
        CurrentUser currentUser = userService.getLoginUser();
        /* 코인 이력 조회 */
        if (currentUser.getId().equals(pointsHistoryReqDto.getUserId())) {
            pointsHistoryResDto = coinMapper.getCointSearch(pointsHistoryReqDto);
        }
        // todo 적립 타입(enum)만들기 적립 or차감 ?(사용) , 일정 디폴트로 최근 한달 지정
        // todo total 코인 따로 service 빼기

        return pointsHistoryResDto;
    }

    /**
     * <pre>
     *  코인 적립 & 사용  이력 저장
     * </pre>
     *
     * @param coinReqDto
     * @return CreateCoinResDto
     * @author KangMinJi (kmj0701@coremethod.co.kr)
     */
    public CreateCoinResDto saveCoin(CreateCoinReqDto coinReqDto) {
        CreateCoinResDto coinResDto = new CreateCoinResDto();
        CurrentUser currentUser = userService.getLoginUser();
        //적립 없을 경우 if문
        if (currentUser.getId().equals(coinReqDto.getUserId())) {
            coinReqDto.setUserId(currentUser.getId());
            coinReqDto.setCoinReason(coinReqDto.getReason().getName());
        }
        coinMapper.saveCoin(coinReqDto);
        return coinResDto;
    }
}
