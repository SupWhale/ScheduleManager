package com.example.schedulemanage.service;

import com.example.schedulemanage.dto.ScheduleRequestDto;
import com.example.schedulemanage.dto.ScheduleResponseDto;
import com.example.schedulemanage.entity.Schedule;
import com.example.schedulemanage.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public ScheduleResponseDto save(ScheduleRequestDto scheduleRequestDto) {
        return null;
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return List.of();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Schedule id) {
        Schedule result = scheduleRepository.findScheduleById(id);

        // NPE 방지
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new ScheduleResponseDto(result);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Schedule id) {
        Schedule result = scheduleRepository.findScheduleById(id);

        // NPE 방지
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        // 필수값 검증
        if (result.getSchedule_name() == null || result.getSchedule_content() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        // memo 수정
        result = id;

        return new ScheduleResponseDto(result);
    }

    @Override
    public void deleteSchedule(Schedule result) {
        // memo 조회
        Schedule result2 = scheduleRepository.findScheduleById(result);

        // NPE 방지
        if (result2 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + result);
        }

        scheduleRepository.deleteSchedule(result);
    }
}
