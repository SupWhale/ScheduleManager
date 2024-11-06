package com.example.schedulemanage.entity;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@AllArgsConstructor
public class Schedule {

    @Id
    private Long id;
    private String userId;
    private String schedule_name;
    private String schedule_content;
    private String schedule_pw;
    private String schedule_st_date;
    private String schedule_ed_date;
    private String schedule_cr_date;
    private String schedule_up_date;
    private String message;

    public Schedule(Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public Schedule() {

    }
    public Schedule(long id, String schedule_name, String schedule_content) {
        this.id = id;
        //this.userId = schedule.getUserId();
        this.schedule_name = schedule_name;
        this.schedule_content = schedule_content;
        //this.schedule_pw = schedule.getSchedule_pw();
        //this.schedule_st_date = schedule_st_date;
        //this.schedule_ed_date = schedule_ed_date;
        this.message = "success";
    }

    public Schedule(long id, String schedule_name, String schedule_content, String schedule_cr_date, String schedule_up_date) {
        this.id = id;
        //this.userId = schedule.getUserId();
        this.schedule_name = schedule_name;
        this.schedule_content = schedule_content;
        //this.schedule_pw = schedule.getSchedule_pw();
        this.schedule_cr_date = schedule_cr_date;
        this.schedule_up_date = schedule_up_date;
        this.message = "success";
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
