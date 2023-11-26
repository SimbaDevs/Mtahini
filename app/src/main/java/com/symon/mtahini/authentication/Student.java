package com.symon.mtahini.authentication;

import java.util.List;

public class Student {
    private String name, regNo, email, pwd, gender;
    List<String> courses;

    public Student(String name, String regNo, String email, String pwd, List<String> courses) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
        this.pwd = pwd;
        this.courses = courses;
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



    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
