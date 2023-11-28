package com.symon.mtahini;

public class Unit {
    String name;
    Short assignment1, assignment2, CAT1, CAT2, exam;

    public Unit(String name, Short assignment1, Short assignment2, Short CAT1, Short CAT2, Short exam) {
        this.name = name;
        this.assignment1 = assignment1;
        this.assignment2 = assignment2;
        this.CAT1 = CAT1;
        this.CAT2 = CAT2;
        this.exam = exam;
    }
    public Unit(){}

    public Unit(String unitName) {
        this.name = unitName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAssignment1() {
        return assignment1;
    }

    public void setAssignment1(Short assignment1) {
        this.assignment1 = assignment1;
    }

    public Short getAssignment2() {
        return assignment2;
    }

    public void setAssignment2(Short assignment2) {
        this.assignment2 = assignment2;
    }

    public Short getCAT1() {
        return CAT1;
    }

    public void setCAT1(Short CAT1) {
        this.CAT1 = CAT1;
    }

    public Short getCAT2() {
        return CAT2;
    }

    public void setCAT2(Short CAT2) {
        this.CAT2 = CAT2;
    }

    public Short getExam() {
        return exam;
    }

    public void setExam(Short exam) {
        this.exam = exam;
    }
}
