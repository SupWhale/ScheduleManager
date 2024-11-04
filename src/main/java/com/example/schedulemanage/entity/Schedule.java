package com.example.schedulemanage.entity;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Schedule {

    @Setter
    private String id;
    private String userId;
    private String schedule_name;
    private String schedule_content;
    private String schedule_pw;
    private String schedule_st_date;
    private String schedule_ed_date;
    private String schedule_cr_date;
    private String schedule_up_date;
    private String message;

    public Schedule(String id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public void updateSchedule(String schedule_name,
                          String schedule_content,
                          String schedule_st_date,
                          String schedule_ed_date) {
        this.schedule_name = schedule_name;
        this.schedule_content = schedule_content;
        this.schedule_st_date = schedule_st_date;
        this.schedule_ed_date = schedule_ed_date;
    }

}
