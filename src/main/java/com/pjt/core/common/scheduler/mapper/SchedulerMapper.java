package com.pjt.core.common.scheduler.mapper;

import com.pjt.core.common.scheduler.dto.SchedulerDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchedulerMapper {

    /*data type 가지고오기*/
    int getSchedulerData(SchedulerDto schedulerDto);
}
