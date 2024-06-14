package com.pjt.core.common.scheduler;

import com.pjt.core.common.scheduler.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync    //  비동기 처리를 위한 설정, ex ) 이메일 발송 로직에 사용
@Order(1)
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    //  트리거 이름 선언
    public static final String TEST_TRIGGER_NAME = "trigger-1";
    //  트리거 그룹 선언
    public static final String TEST_TRIGGER_GROUP = "trigger-group-1";
    //  잡 이름 선언
    public static final String TEST_JOB_NAME = "job-1";
    //  잡 그룹 선언
    public static final String TEST_JOB_GROUP = "job-group-1";
    //  잡에서 사용될 데이터의 키값 선언
    public static final String TEST_JOB_DATA_KEY = "integerValue";

    private final SchedulerService schedulerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        coinScheduler();
        System.err.println("출력 !!!!!!!!!!!!!!!!!!!!!");


    }

    // 코인 지우기용
    private void coinScheduler() throws SchedulerException {
        schedulerService.initCoinScheduler();
    }

    //메일용

}
