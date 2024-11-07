package com.example.schedulemanage.service;

import com.example.schedulemanage.dto.ScheduleRequestDto;
import com.example.schedulemanage.dto.ScheduleResponseDto;
import com.example.schedulemanage.entity.Schedule;
import com.example.schedulemanage.entity.User;
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
        result.setUserNm(scheduleRequestDto.getUserNm());
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
    public List<ScheduleResponseDto> findPageSchedules(int pageno, int pagesize) {
        return scheduleRepository.findPagingSchedule(pageno, pagesize);
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
    public User findUserById(Long id) {
        Optional<User> result = scheduleRepository.findUserById(id);

        // NPE 방지
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return result.get();
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Optional<Schedule> updateTargetInfo = scheduleRepository.findScheduleById(id);

        if(requestDto.getSchedule_pw().equals(updateTargetInfo.get().getSchedule_pw())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong password");
        }

        Schedule updateTargetSchedule = new Schedule();
        updateTargetSchedule.setId(id);
        updateTargetSchedule.setSchedule_name(requestDto.getSchedule_name());
        updateTargetSchedule.setSchedule_content(requestDto.getSchedule_content());

        // NPE 방지
        if (updateTargetSchedule.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        // 필수값 검증
        if (updateTargetSchedule.getSchedule_name() == null || updateTargetSchedule.getSchedule_content() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        int updatedRow = scheduleRepository.updateSchedule(updateTargetSchedule);

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
