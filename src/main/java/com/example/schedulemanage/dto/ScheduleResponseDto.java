package com.example.schedulemanage.dto;

import com.example.schedulemanage.entity.Schedule;
import lombok.Getter;
import org.springframework.web.service.annotation.GetExchange;

@Getter
public class ScheduleResponseDto {

    private Long id;
    private String userId;
    private String schedule_name;
    private String schedule_content;
    private String schedule_pw;
    private String schedule_st_date;
    private String schedule_ed_date;
    private String message;


    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.userId = schedule.getUserId();
        this.schedule_name = schedule.getSchedule_name();
        this.schedule_content = schedule.getSchedule_content();
        this.schedule_pw = schedule.getSchedule_pw();
        this.schedule_st_date = schedule.getSchedule_st_date();
        this.schedule_ed_date = schedule.getSchedule_ed_date();
        this.message =schedule.getMessage();
    }

    public ScheduleResponseDto(String message){
        this.message = message;
    }

    public ScheduleResponseDto(long id, String schedule_name, String schedule_content) {
        this.id = id;
        //this.userId = schedule.getUserId();
        this.schedule_name = schedule_name;
        this.schedule_content = schedule_content;
        //this.schedule_pw = schedule.getSchedule_pw();
        //this.schedule_st_date = schedule_st_date;
        //this.schedule_ed_date = schedule_ed_date;
        this.message = "success";

    }
}
