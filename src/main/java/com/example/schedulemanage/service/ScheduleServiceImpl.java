package com.example.schedulemanage.service;

import com.example.schedulemanage.dto.ScheduleRequestDto;
import com.example.schedulemanage.dto.ScheduleResponseDto;
import com.example.schedulemanage.entity.Schedule;
import com.example.schedulemanage.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule result = new Schedule();
        result.setUserId(scheduleRequestDto.getUserId());
        result.setSchedule_name(scheduleRequestDto.getSchedule_name());
        result.setSchedule_content(scheduleRequestDto.getSchedule_content());
        result.setSchedule_pw(scheduleRequestDto.getSchedule_pw());
        result.setSchedule_st_date(scheduleRequestDto.getSchedule_st_date());
        result.setSchedule_ed_date(scheduleRequestDto.getSchedule_ed_date());
        return scheduleRepository.saveSchedule(result);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedule();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Optional<Schedule> result = scheduleRepository.findScheduleById(id);

        // NPE 방지
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new ScheduleResponseDto(result.get());
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Optional<Schedule> result = scheduleRepository.findScheduleById(id);

        if(requestDto.getSchedule_pw().equals(result.get().getSchedule_pw())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong password");
        }

        Schedule result2 = new Schedule();
        result2.setId(id);
        result2.setSchedule_name(requestDto.getSchedule_name());
        result2.setSchedule_content(requestDto.getSchedule_content());

        // NPE 방지
        if (result2.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        // 필수값 검증
        if (result2.getSchedule_name() == null || result2.getSchedule_content() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        int updatedRow = scheduleRepository.updateSchedule(result2);

        //ScheduleResponseDto returmi = scheduleRepository.findScheduleById(id);

        return findScheduleById(id);
    }

    @Override
    public ScheduleResponseDto deleteSchedule(Long id, ScheduleRequestDto requestDto) {

        Optional<Schedule> result = scheduleRepository.findScheduleById(id);

        if(requestDto.getSchedule_pw().equals(result.get().getSchedule_pw())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong password");
        }

        int deletedRow = scheduleRepository.deleteSchedule(id);
        // 삭제된 row가 0개 라면
        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return new ScheduleResponseDto("success");
    }
}
