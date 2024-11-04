package com.example.schedulemanage.dto;

import com.example.schedulemanage.entity.Schedule;

public class ScheduleResponseDto {

    private String id;
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
}
