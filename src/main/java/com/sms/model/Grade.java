package com.sms.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a grade entry for a student in a specific course
 */
public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String studentId;
    private String courseCode;
    private double score;
    private LocalDate dateRecorded;
    
    public Grade(String studentId, String courseCode, double score) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.score = score;
        this.dateRecorded = LocalDate.now();
    }
    
    // Getters and setters
    public String getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }
    public double getScore() { return score; }
    public LocalDate getDateRecorded() { return dateRecorded; }
    
    public void setScore(double score) { this.score = score; }
    
    @Override
    public String toString() {
        return "Grade{student=" + studentId + ", course=" + courseCode + ", score=" + score + "}";
    }
}