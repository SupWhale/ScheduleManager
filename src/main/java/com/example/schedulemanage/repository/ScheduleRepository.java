package com.example.schedulemanage.repository;

import com.example.schedulemanage.dto.ScheduleResponseDto;
import com.example.schedulemanage.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    Schedule saveMemo(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedule();

    Schedule findScheduleById(Schedule schedule);

    void deleteSchedule(Schedule schedule);

}
