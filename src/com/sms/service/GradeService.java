package com.sms.service;

import com.sms.model.Student;
import com.sms.expception.StudentNotFoundException;
import com.sms.model.Grade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for grade-related operations
 */
public class GradeService {
    private StudentService studentService;
    
    public GradeService(StudentService studentService) {
        this.studentService = studentService;
    }
    
    public void addGrade(String studentId, String course, double grade) throws StudentNotFoundException {
        studentService.addGrade(studentId, course, grade);
    }
    
    public List<Double> getStudentGradesForCourse(String studentId, String course) throws StudentNotFoundException {
        Student student = studentService.getStudent(studentId);
        return student.getGradesForCourse(course);
    }
    
    public double getStudentCourseAverage(String studentId, String course) throws StudentNotFoundException {
        Student student = studentService.getStudent(studentId);
        return student.calculateCourseAverage(course);
    }
    
    public double getStudentGPA(String studentId) throws StudentNotFoundException {
        Student student = studentService.getStudent(studentId);
        return student.calculateGPA();
    }
    
    public Map<String, Double> getCourseAverages() {
        Map<String, Double> courseAverages = new HashMap<>();
        Map<String, List<Double>> allCourseGrades = new HashMap<>();
        
        try {
            // Collect all grades for all courses
            for (Student student : studentService.getAllStudents()) {
                Map<String, List<Double>> studentGrades = student.getAllGrades();
                
                for (Map.Entry<String, List<Double>> entry : studentGrades.entrySet()) {
                    String course = entry.getKey();
                    List<Double> grades = entry.getValue();
                    
                    allCourseGrades.computeIfAbsent(course, k -> new ArrayList<>())
                                  .addAll(grades);
                }
            }
            
            // Calculate averages for each course
            for (Map.Entry<String, List<Double>> entry : allCourseGrades.entrySet()) {
                String course = entry.getKey();
                List<Double> grades = entry.getValue();
                
                if (!grades.isEmpty()) {
                    double sum = 0.0;
                    for (Double grade : grades) {
                        sum += grade;
                    }
                    courseAverages.put(course, sum / grades.size());
                }
            }
        } catch (Exception e) {
            System.err.println("Error calculating course averages: " + e.getMessage());
        }
        
        return courseAverages;
    }
}