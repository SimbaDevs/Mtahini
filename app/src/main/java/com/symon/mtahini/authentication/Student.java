package com.symon.mtahini.authentication;

import com.symon.mtahini.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student {
    private String name, regNo, email, pwd, userId;
    List<Unit> unitList = new ArrayList<>();

    public Student() {
    }

    public Student(String name, String regNo, String email, String pwd, String userId, List<Unit> unitList) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
        this.pwd = pwd;
        this.userId = userId;
        this.unitList = unitList;
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

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }
}
