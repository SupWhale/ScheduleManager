package com.example.schedulemanage.entity;

public class User {
    private Long id;
    private String username;
    private String email;
    private String crDate;
    private String upDate;

    public User(long userId, String email, String userCrDate, String userUpDate) {
        this.id = userId;
        this.email = email;
        this.crDate = userCrDate;
        this.upDate = userUpDate;
    }
}
