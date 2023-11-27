package com.symon.mtahini.authentication;

import java.util.HashMap;
import java.util.List;

public class Student {
    private String name, regNo, email, pwd, userId;
    HashMap<String, Integer> courses;

    public Student(String name, String regNo, String email, String pwd, String userId, HashMap<String, Integer> courses) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
        this.pwd = pwd;
        this.userId = userId;
        this.courses = courses;
    }

    public Student(String name, String regNo, String email, String pwd, String userId) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
        this.pwd = pwd;
        this.userId = userId;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public HashMap<String, Integer> getCourses() {
        return courses;
    }

    public void setCourses(HashMap<String, Integer> courses) {
        this.courses = courses;
    }
}
