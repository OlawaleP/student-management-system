package com.sms.model;

import java.util.*;
import java.io.Serializable;

/**
 * Represents a student in the management system
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String firstName;
    private String lastName;
    private Map<String, List<Double>> courseGrades; // Maps course name to list of grades
    
    public Student(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseGrades = new HashMap<>();
    }
    
    // Getters and setters
    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() { return firstName + " " + lastName; }
    
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    // Grade management
    public void addGrade(String course, double grade) {
        courseGrades.computeIfAbsent(course, k -> new ArrayList<>()).add(grade);
    }
    
    public List<Double> getGradesForCourse(String course) {
        return courseGrades.getOrDefault(course, Collections.emptyList());
    }
    
    public Map<String, List<Double>> getAllGrades() {
        return new HashMap<>(courseGrades);
    }
    
    public double calculateCourseAverage(String course) {
        List<Double> grades = getGradesForCourse(course);
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (Double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
    
    public double calculateGPA() {
        if (courseGrades.isEmpty()) {
            return 0.0;
        }
        
        double totalPoints = 0.0;
        int totalCourses = 0;
        
        for (String course : courseGrades.keySet()) {
            double avg = calculateCourseAverage(course);
            totalPoints += avg;
            totalCourses++;
        }
        
        return totalPoints / totalCourses;
    }
    
    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + getFullName() + "', GPA=" + String.format("%.2f", calculateGPA()) + "}";
    }
}
