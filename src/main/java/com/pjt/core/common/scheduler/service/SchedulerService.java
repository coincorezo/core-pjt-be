package com.pjt.core.common.scheduler.service;

import com.pjt.core.common.scheduler.TestJob;
import com.pjt.core.common.scheduler.dto.SchedulerDto;
import com.pjt.core.common.scheduler.mapper.SchedulerMapper;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final Scheduler scheduler;
    private final SchedulerMapper schedulerMapper;

    // 스케줄러 기간 data 값 가져온다. */
    public int getSchedulerData(SchedulerDto schedulerDto) {

        int schedulerData = schedulerMapper.getSchedulerData(schedulerDto);

        return schedulerData;
    }


    public void initCoinScheduler() throws SchedulerException {

        int schedulerData = getSchedulerData(new SchedulerDto());

        //  TestJob.class 에서 사용된 data map
        JobDataMap jobDataMap = new JobDataMap();

        //  등록된  job scheduler 기간 data가 있을경우 해당 값으로 job 을 셋팅한다
        if (schedulerData != 0) {
            jobDataMap.put("dataKey", schedulerData);
        }

        //  trigger 와 매핑될 job 에 넘길 상세 정보를 설정한다.
        JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) TestJob.class)
                .withIdentity("CoinexpireJob", "Coin_JOB_GROUP")
                .setJobData(jobDataMap)
                .build();

        //  cron 형식으로 trigger 를 생성하고 job 을 매핑한다.
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("CoinexpireTiger", "CoinTriggerGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("/10 * * * * ?")) // 매 10초마다 실행
                .forJob(jobDetail)
                .build();

        //  위에서 설정된 trigger 에 job 을 매핑하고 scheduler 에 등록한다.
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
}
