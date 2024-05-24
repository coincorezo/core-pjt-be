package com.pjt.core.coin.service;

import com.pjt.core.coin.CoinException.CoinException;
import com.pjt.core.coin.dto.CreateCoinReqDto;
import com.pjt.core.coin.dto.CreateCoinResDto;
import com.pjt.core.coin.dto.PointsHistoryReqDto;
import com.pjt.core.coin.dto.PointsHistoryResDto;
import com.pjt.core.coin.repository.CoinMapper;
import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.user.dto.CurrentUser;
import com.pjt.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 코인 조회 저장 service
 * </pre>
 *
 * @author : KangMinJi
 * @date :  2024-05-24
 */
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
    public List<PointsHistoryResDto> getCoinSearch(PointsHistoryReqDto pointsHistoryReqDto) {
        List<PointsHistoryResDto> pointsHistoryResDto = new ArrayList<>();
        CurrentUser currentUser = userService.getLoginUser();

        // 코인 이력 조회
        if (currentUser.getId().equals(pointsHistoryReqDto.getUserId())) {
            pointsHistoryResDto = coinMapper.getCointSearch(pointsHistoryReqDto);
        }
        //coin change값 0일 때 0 지우기
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
        CurrentUser currentUser = userService.getLoginUser();
        // userId 일치 하는지 확인
        currentUser.validUserId(coinReqDto.getUserId());

        // 잔여 coin보다 차감될 코인이 더 클경우 exception 처리
        PointsHistoryReqDto pointsHistoryReqDto = new PointsHistoryReqDto();
        pointsHistoryReqDto.setUserId(coinReqDto.getUserId());

        // 잔여코인 조회
        PointsHistoryResDto pointsHistoryResDto = this.getMyCoin(pointsHistoryReqDto);
        if (pointsHistoryResDto == null && coinReqDto.getPointsChange() < 0) {
            throw new CoinException(ErrorCode.NO_COIN);
        }

        if (pointsHistoryResDto != null && pointsHistoryResDto.getPointsAmount() >= 0) {
            // 차감될 코인과 현재 잔여 코인 계산 하여 0보다 작을경우 exception 
            int calculatedCoin = coinReqDto.getPointsChange() + (pointsHistoryResDto.getPointsAmount());
            if (calculatedCoin < 0) {
                throw new CoinException(ErrorCode.NO_COIN);
            }
        }

        // coin
        CreateCoinResDto coinResDto = new CreateCoinResDto();
        coinReqDto.setUserId(currentUser.getId());
        coinReqDto.setCoinReason(coinReqDto.getReason().getName());
        coinReqDto.setCoinType(coinReqDto.getReason().getDescription());
        coinMapper.saveCoin(coinReqDto);

        // res 코인이 적립되었습니다.
        coinResDto.setCoin(coinReqDto.getPointsChange());

        return coinResDto;
    }

    /**
     * <pre>
     * 나의 코인 조회
     * </pre>
     *
     * @param : pointsHistoryReqDto
     * @return :PointsHistoryResDto
     * @throws :
     * @author : KangMinJi (kmj0701@coremethod.co.kr)
     * @date : 2024-05-24
     */
    public PointsHistoryResDto getMyCoin(PointsHistoryReqDto pointsHistoryReqDto) {

        CurrentUser currentUser = userService.getLoginUser();
        currentUser.validUserId(pointsHistoryReqDto.getUserId());

        return coinMapper.getMyCoin(pointsHistoryReqDto);
    }
}
