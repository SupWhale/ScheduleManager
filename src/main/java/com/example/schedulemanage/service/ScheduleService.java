package com.example.schedulemanage.service;

import com.example.schedulemanage.dto.ScheduleRequestDto;
import com.example.schedulemanage.dto.ScheduleResponseDto;
import com.example.schedulemanage.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto save(ScheduleRequestDto scheduleRequestDto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(Schedule id);

    ScheduleResponseDto updateSchedule(Schedule result);

    void deleteSchedule(Schedule result);


}
