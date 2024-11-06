package com.example.schedulemanage.repository;

import com.example.schedulemanage.dto.ScheduleResponseDto;
import com.example.schedulemanage.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedule();

    Optional<Schedule> findScheduleById(Long id);

    int updateSchedule(Schedule schedule);

    int deleteSchedule(Long id);

}
