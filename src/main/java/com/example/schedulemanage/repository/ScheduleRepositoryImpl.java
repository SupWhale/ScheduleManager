package com.example.schedulemanage.repository;

import com.example.schedulemanage.dto.ScheduleResponseDto;
import com.example.schedulemanage.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{
    @Override
    public Schedule saveMemo(Schedule schedule) {
        return null;
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return List.of();
    }

    @Override
    public Schedule findScheduleById(Schedule schedule) {
        return null;
    }

    @Override
    public void deleteSchedule(Schedule schedule) {

    }
}
