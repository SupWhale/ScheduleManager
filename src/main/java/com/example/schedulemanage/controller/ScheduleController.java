package com.example.schedulemanage.controller;


import com.example.schedulemanage.dto.ScheduleRequestDto;
import com.example.schedulemanage.dto.ScheduleResponseDto;
import com.example.schedulemanage.entity.User;
import com.example.schedulemanage.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;


    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    @PostMapping // 요청
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // ServiceLayer 호출 및 응답
        return new ResponseEntity<>(scheduleService.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    @GetMapping // 요청
    public List<ScheduleResponseDto> SelectAllSchedule() {
        // ServiceLayer 호출 및 응답
        return scheduleService.findAllSchedules();
    }

    @GetMapping("/page") // 요청
    public List<ScheduleResponseDto> SelectPageSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // ServiceLayer 호출 및 응답
        return scheduleService.findPageSchedules(requestDto.getPageNo(), requestDto.getPageSize());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{userid}")
    public ResponseEntity<User> findUserById(@PathVariable Long userid) {
        return new ResponseEntity<>(scheduleService.findUserById(userid), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updatedSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, requestDto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        // 성공한 경우
        return new ResponseEntity<>(scheduleService.deleteSchedule(id, requestDto),HttpStatus.OK);
    }
}
