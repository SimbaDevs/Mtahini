package com.symon.mtahini.authentication;

import java.util.HashMap;
import java.util.List;

public class Student {
    private String name, regNo, email, pwd, gender;
    HashMap<String, Integer> courses;

    public Student(String name, String regNo, String email, String pwd, String gender, HashMap<String, Integer> courses) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
        this.pwd = pwd;
        this.gender = gender;
        this.courses = courses;
    }

    public Student() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public HashMap<String, Integer> getCourses() {
        return courses;
    }

    public void setCourses(HashMap<String, Integer> courses) {
        this.courses = courses;
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
}
