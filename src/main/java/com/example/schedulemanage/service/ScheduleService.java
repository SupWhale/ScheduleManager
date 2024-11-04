package com.example.schedulemanage.service;

import com.example.schedulemanage.dto.ScheduleRequestDto;
import com.example.schedulemanage.dto.ScheduleResponseDto;

public interface ScheduleService {

    ScheduleResponseDto save(ScheduleRequestDto scheduleRequestDto);



}
