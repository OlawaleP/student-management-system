package main.java.com.sms.repository;

public package com.sms.repository;

import com.sms.model.Student;
import com.sms.model.Course;

import java.io.*;
import java.util.*;

/**
 * Handles data persistence for the application
 */
public class DataStore {
    private static final String STUDENTS_FILE = "students.dat";
    private static final String COURSES_FILE = "courses.dat";
    
    public void saveStudents(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(STUDENTS_FILE))) {
            oos.writeObject(students);
            System.out.println("Students data saved successfully");
        } catch (IOException e) {
            System.err.println("Error saving students data: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        
        File file = new File(STUDENTS_FILE);
        if (!file.exists()) {
            return students;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(STUDENTS_FILE))) {
            students = (List<Student>) ois.readObject();
            System.out.println("Students data loaded successfully");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading students data: " + e.getMessage());
        }
        
        return students;
    }
    
    public void saveCourses(List<Course> courses) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(COURSES_FILE))) {
            oos.writeObject(courses);
            System.out.println("Courses data saved successfully");
        } catch (IOException e) {
            System.err.println("Error saving courses data: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();
        
        File file = new File(COURSES_FILE);
        if (!file.exists()) {
            return courses;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(COURSES_FILE))) {
            courses = (List<Course>) ois.readObject();
            System.out.println("Courses data loaded successfully");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading courses data: " + e.getMessage());
        }
        
        return courses;
    }
} {
  
}
