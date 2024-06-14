package com.pjt.core.coin.service;

import com.pjt.core.coin.CoinException.CoinException;
import com.pjt.core.coin.dto.*;
import com.pjt.core.coin.repository.CoinMapper;
import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.common.scheduler.service.SchedulerService;
import com.pjt.core.user.dto.CurrentUser;
import com.pjt.core.user.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CoinService {

    private final CoinMapper coinMapper;

    private final UserService userService;

    private final SchedulerService schedulerService;


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

        // userId 일치 하는지 확인
        currentUser.validUserId(pointsHistoryReqDto.getUserId());
        // 코인 이력 조회
        pointsHistoryResDto = coinMapper.getCointSearch(pointsHistoryReqDto);

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

        // 잔여코인 조회
        PointsHistoryReqDto pointsHistoryReqDto = new PointsHistoryReqDto();
        pointsHistoryReqDto.setUserId(coinReqDto.getUserId());
        PointsHistoryResDto pointsHistoryResDto = this.getMyCoin(pointsHistoryReqDto);

        // 잔여 coin보다 차감될 코인이 더 클경우 exception 처리
        if (pointsHistoryResDto == null && coinReqDto.getPointsChange() < 0) {
            throw new CoinException(ErrorCode.NO_COIN);
        }
        // 잔여코인이 있는경우에 차감될 코인이  잔여코인보다  0보다 작을경우(마이너스 인경우) exception
        if (pointsHistoryResDto != null && pointsHistoryResDto.getPointsAmount() >= 0) {
            int calculatedCoin = coinReqDto.getPointsChange() + (pointsHistoryResDto.getPointsAmount());
            if (calculatedCoin < 0) {
                throw new CoinException(ErrorCode.NO_COIN);
            }
        }

        // coin insert
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
        PointsHistoryResDto pointsHistoryResDto = coinMapper.getMyCoin(pointsHistoryReqDto);
        CoinExpireReqDto coinExpireReqDto = new CoinExpireReqDto();
        coinExpireReqDto.setUserId(pointsHistoryReqDto.getUserId());
        List<PointsHistoryResDto> coinResDto = this.getDisappearCoin(coinExpireReqDto);
        if (coinResDto.size() > 0) {
            pointsHistoryResDto.setDisappear(coinResDto.get(0).getDisappear());
        }
        return pointsHistoryResDto;
    }

    /**
     * <pre>
     * 나의 소멸 예정 코인 조회
     * </pre>
     *
     * @param : pointsHistoryReqDto
     * @return :PointsHistoryResDto
     * @throws :
     * @author : KangMinJi (kmj0701@coremethod.co.kr)
     * @date : 2024-05-24
     */
    public List<PointsHistoryResDto> getDisappearCoin(CoinExpireReqDto coinExpireReqDto) {
        // scheduler

        // 가입자  중  총 소멸 코인, 총 코인 값  or userId 있을경우 값 비교해서 userId값 구하기
        if (!("").equals(coinExpireReqDto.getUserId()) && coinExpireReqDto.getUserId() != null) {
            CurrentUser currentUser = userService.getLoginUser();
            currentUser.validUserId(coinExpireReqDto.getUserId());
        }
        //    소멸코인 , 총 코인 값 구하기 (ID에따라서)
        List<PointsHistoryResDto> pointsHistoryResDto = coinMapper.getDisappearCoin(coinExpireReqDto);

        // 총      소멸
        //2000 -3000 => -1000   =>총 - 처리
        //2000 -1000 => 1000 =>양수 나올 경우 소멸 포인트를 -처리
        //2000 -2000 => 0  => 0이 나올경우 총 포인트 마이너스 처리
        //50000 - 2000 =>48,000
        for (PointsHistoryResDto resDto : pointsHistoryResDto) {
            //  소멸 포인트 값 구하기  => A: 총코인 , B: 소멸예정포인트
            int disappear = resDto.getPointsAmount() - resDto.getDisappear() <= 0 ? resDto.getPointsAmount() : resDto.getDisappear();
            if (disappear == resDto.getPointsAmount()) {
                resDto.setDisappear(disappear);

            } else {
                resDto.setDisappear(disappear);

            }

        }
        return pointsHistoryResDto;
    }
    /**
     * <pre>
     *  코인 소멸 조회
     * </pre>
     *
     * @param : pointsHistoryReqDto
     * @return :PointsHistoryResDto
     * @throws :
     * @author : KangMinJi (kmj0701@coremethod.co.kr)
     * @date : 2024-05-24
     */
}