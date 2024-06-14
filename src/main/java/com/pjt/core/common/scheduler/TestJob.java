package com.pjt.core.common.scheduler;

import com.pjt.core.coin.dto.CoinExpireReqDto;
import com.pjt.core.coin.dto.PointsHistoryResDto;
import com.pjt.core.coin.service.CoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestJob implements Job {

    private final CoinService coinService;

    // 소멸 job
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();

        for (Map.Entry<String, Object> entry : jobDataMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            log.info("job data key = {}, job data value = {}", key, value);
        }
        //if (jobDataMap.get("dataKey") != null) {
        CoinExpireReqDto coinExpireReqDto = new CoinExpireReqDto();
        //coinExpireReqDto.setDateFlg((Integer) jobDataMap.get("dataKey"));
        List<PointsHistoryResDto> pointsHistoryResDto = coinService.getDisappearCoin(coinExpireReqDto);

        //insert 만들기
        //coinMapper.bulkInsert(pointsHistoryResDto);
//
//            <insert id="bulkInsertUsers">
//                    INSERT INTO users (id, name, email)
//                     VALUES
//                    <foreach collection="userList" item="user" separator=",">
//                    (#{user.id}, #{user.name}, #{user.email})
//                    </foreach>
//            </insert>


        //}
    }
}
