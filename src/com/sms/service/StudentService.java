package main.java.com.sms.service;

public package com.sms.service;

import com.sms.expception.DuplicateStudentException;
import com.sms.expception.StudentNotFoundException;
import com.sms.model.Student;
import com.sms.repository.DataStore;
import com.sms.repository.StudentRepository;

import java.util.List;
import java.util.ArrayList;

/**
 * Service class for student-related operations
 */
public class StudentService {
    private StudentRepository studentRepository;
    private DataStore dataStore;
    
    public StudentService() {
        this.studentRepository = new StudentRepository();
        this.dataStore = new DataStore();
        
        // Load existing students from storage
        List<Student> loadedStudents = dataStore.loadStudents();
        for (Student student : loadedStudents) {
            try {
                studentRepository.addStudent(student);
            } catch (DuplicateStudentException e) {
                System.err.println("Warning: " + e.getMessage());
            }
        }
    }
    
    public void addStudent(String id, String firstName, String lastName) throws DuplicateStudentException {
        Student student = new Student(id, firstName, lastName);
        studentRepository.addStudent(student);
        saveData();
    }
    
    public void updateStudent(Student student) throws StudentNotFoundException {
        studentRepository.updateStudent(student);
        saveData();
    }
    
    public Student getStudent(String studentId) throws StudentNotFoundException {
        return studentRepository.getStudentById(studentId);
    }
    
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }
    
    public void removeStudent(String studentId) throws StudentNotFoundException {
        studentRepository.removeStudent(studentId);
        saveData();
    }
    
    public boolean addGrade(String studentId, String course, double grade) throws StudentNotFoundException {
        Student student = studentRepository.getStudentById(studentId);
        student.addGrade(course, grade);
        saveData();
        return true;
    }
    
    public List<Student> getStudentsSortedByGPA() {
        List<Student> students = studentRepository.getAllStudents();
        students.sort((s1, s2) -> Double.compare(s2.calculateGPA(), s1.calculateGPA()));
        return students;
    }
    
    public double getClassAverageGPA() {
        List<Student> students = studentRepository.getAllStudents();
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double totalGPA = 0.0;
        for (Student student : students) {
            totalGPA += student.calculateGPA();
        }
        
        return totalGPA / students.size();
    }
    
    private void saveData() {
        dataStore.saveStudents(studentRepository.getAllStudents());
    }
} {
  
}
