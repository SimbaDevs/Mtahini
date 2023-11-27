package com.symon.mtahini;

public class Lecturer {
    private String name, email, staffID, department, course;

    public Lecturer() {
    }

    public Lecturer(String name, String email, String staffID, String department, String course) {
        this.name = name;
        this.email = email;
        this.staffID = staffID;
        this.department = department;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
