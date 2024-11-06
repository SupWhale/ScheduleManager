package com.example.schedulemanage.service;

import com.example.schedulemanage.dto.ScheduleRequestDto;
import com.example.schedulemanage.dto.ScheduleResponseDto;
import com.example.schedulemanage.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto);

    ScheduleResponseDto deleteSchedule(Long id, ScheduleRequestDto requestDto);


}
