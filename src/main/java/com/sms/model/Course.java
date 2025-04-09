package main.java.com.sms.model;

public package com.sms.model;

import java.io.Serializable;

/**
 * Represents a course in the system
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String courseCode;
    private String courseName;
    private String description;
    private int creditHours;
    
    public Course(String courseCode, String courseName, int creditHours) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.description = "";
    }
    
    public Course(String courseCode, String courseName, String description, int creditHours) {
        this(courseCode, courseName, creditHours);
        this.description = description;
    }
    
    // Getters and setters
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public String getDescription() { return description; }
    public int getCreditHours() { return creditHours; }
    
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setDescription(String description) { this.description = description; }
    public void setCreditHours(int creditHours) { this.creditHours = creditHours; }
    
    @Override
    public String toString() {
        return courseName + " (" + courseCode + ") - " + creditHours + " credits";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }
} {
  
}
import java.util.Objects;
