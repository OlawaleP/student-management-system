package main.java.com.sms.repository;

public package com.sms.repository;

import com.sms.model.Student;
import com.sms.exception.DuplicateStudentException;
import com.sms.exception.StudentNotFoundException;

import java.util.*;

/**
 * Repository for managing student data
 */
public class StudentRepository {
    private Map<String, Student> students;
    
    public StudentRepository() {
        this.students = new HashMap<>();
    }
    
    public void addStudent(Student student) throws DuplicateStudentException {
        if (students.containsKey(student.getId())) {
            throw new DuplicateStudentException("Student with ID " + student.getId() + " already exists");
        }
        students.put(student.getId(), student);
    }
    
    public void updateStudent(Student student) throws StudentNotFoundException {
        if (!students.containsKey(student.getId())) {
            throw new StudentNotFoundException("Student with ID " + student.getId() + " not found");
        }
        students.put(student.getId(), student);
    }
    
    public Student getStudentById(String studentId) throws StudentNotFoundException {
        Student student = students.get(studentId);
        if (student == null) {
            throw new StudentNotFoundException("Student with ID " + studentId + " not found");
        }
        return student;
    }
    
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }
    
    public void removeStudent(String studentId) throws StudentNotFoundException {
        if (students.remove(studentId) == null) {
            throw new StudentNotFoundException("Student with ID " + studentId + " not found");
        }
    }
    
    public boolean studentExists(String studentId) {
        return students.containsKey(studentId);
    }
    
    public int getStudentCount() {
        return students.size();
    }
} {
  
}
