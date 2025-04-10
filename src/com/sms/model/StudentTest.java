package com.sms.model;

public package com.sms.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student student;
    
    @BeforeEach
    void setUp() {
        student = new Student("TEST001", "Test", "Student");
    }
    
    @Test
    void testStudentCreation() {
        assertEquals("TEST001", student.getId());
        assertEquals("Test", student.getFirstName());
        assertEquals("Student", student.getLastName());
        assertEquals("Test Student", student.getFullName());
    }
    
    @Test
    void testAddGrade() {
        student.addGrade("Math", 85.0);
        student.addGrade("Math", 90.0);
        
        assertEquals(2, student.getGradesForCourse("Math").size());
        assertEquals(85.0, student.getGradesForCourse("Math").get(0));
        assertEquals(90.0, student.getGradesForCourse("Math").get(1));
    }
    
    @Test
    void testCalculateCourseAverage() {
        student.addGrade("Math", 80.0);
        student.addGrade("Math", 90.0);
        
        assertEquals(85.0, student.calculateCourseAverage("Math"));
    }
    
    @Test
    void testCalculateGPA() {
        student.addGrade("Math", 80.0);
        student.addGrade("English", 90.0);
        student.addGrade("Science", 70.0);
        
        assertEquals(80.0, student.calculateGPA());
    }
    
    @Test
    void testEmptyGrades() {
        assertEquals(0.0, student.calculateGPA());
        assertEquals(0.0, student.calculateCourseAverage("NonExistentCourse"));
        assertTrue(student.getGradesForCourse("NonExistentCourse").isEmpty());
    }
} {
  
}
