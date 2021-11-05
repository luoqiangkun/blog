package com.luospace.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private int id;

    private String name;

    private String email;

    private String emailVerifiedAt;

    private String password;

    private String rememberToken;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date createdAt;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date updatedAt;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public String getPassword() {
        return password;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailVerifiedAt(String emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", emailVerifiedAt='" + emailVerifiedAt + '\'' +
                ", password='" + password + '\'' +
                ", rememberToken='" + rememberToken + '\'' +
                '}';
    }
}
