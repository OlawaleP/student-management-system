package com.sms.service;

import com.sms.expception.StudentNotFoundException;
import com.sms.model.Student;

import java.util.List;
import java.util.Map;

/**
 * Service for generating reports
 */
public class ReportService {
    private StudentService studentService;
    private GradeService gradeService;
    
    public ReportService(StudentService studentService, GradeService gradeService) {
        this.studentService = studentService;
        this.gradeService = gradeService;
    }
    
    public void generateStudentReport(String studentId) {
        try {
            Student student = studentService.getStudent(studentId);
            
            System.out.println("===== Student Report =====");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getFullName());
            System.out.println("Overall GPA: " + String.format("%.2f", student.calculateGPA()));
            System.out.println("\nCourse Grades:");
            
            Map<String, List<Double>> allGrades = student.getAllGrades();
            for (String course : allGrades.keySet()) {
                System.out.println("  " + course + ": " + allGrades.get(course) + 
                        " (Average: " + String.format("%.2f", student.calculateCourseAverage(course)) + ")");
            }
            System.out.println("=========================");
            
        } catch (StudentNotFoundException e) {
            System.out.println("Student not found with ID: " + studentId);
        }
    }
    
    public void generateClassReport() {
        System.out.println("===== Class Report =====");
        
        List<Student> students = studentService.getAllStudents();
        System.out.println("Total students: " + students.size());
        
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            System.out.println("=======================");
            return;
        }
        
        double averageGPA = studentService.getClassAverageGPA();
        System.out.println("Class Average GPA: " + String.format("%.2f", averageGPA));
        System.out.println("\nStudent List (sorted by GPA):");
        
        List<Student> sortedStudents = studentService.getStudentsSortedByGPA();
        
        for (Student student : sortedStudents) {
            System.out.println("  " + student.getFullName() + 
                    " (ID: " + student.getId() + ") - GPA: " + 
                    String.format("%.2f", student.calculateGPA()));
        }
        
        System.out.println("\nCourse Averages:");
        Map<String, Double> courseAverages = gradeService.getCourseAverages();
        for (Map.Entry<String, Double> entry : courseAverages.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + String.format("%.2f", entry.getValue()));
        }
        
        System.out.println("=======================");
    }
}