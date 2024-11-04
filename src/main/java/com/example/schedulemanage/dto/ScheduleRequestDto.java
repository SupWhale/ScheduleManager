package com.example.schedulemanage.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private String id;
    private String userId;
    private String schedule_name;
    private String schedule_content;
    private String schedule_pw;
    private String schedule_st_date;
    private String schedule_ed_date;

}
