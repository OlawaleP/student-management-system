package com.sms.service;

public package com.sms.service;

import com.sms.expception.DuplicateStudentException;
import com.sms.expception.StudentNotFoundException;
import com.sms.model.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService studentService;
    
    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }
    
    @Test
    void testAddAndRetrieveStudent() throws DuplicateStudentException, StudentNotFoundException {
        studentService.addStudent("TEST001", "Test", "Student");
        
        Student student = studentService.getStudent("TEST001");
        assertNotNull(student);
        assertEquals("TEST001", student.getId());
        assertEquals("Test Student", student.getFullName());
    }
    
    @Test
    void testDuplicateStudent() throws DuplicateStudentException {
        studentService.addStudent("TEST001", "Test", "Student");
        
        Exception exception = assertThrows(DuplicateStudentException.class, () -> {
            studentService.addStudent("TEST001", "Another", "Student");
        });
        
        assertTrue(exception.getMessage().contains("already exists"));
    }
    
    @Test
    void testRemoveStudent() throws DuplicateStudentException, StudentNotFoundException {
        studentService.addStudent("TEST001", "Test", "Student");
        assertTrue(studentService.getAllStudents().size() > 0);
        
        studentService.removeStudent("TEST001");
        
        Exception exception = assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudent("TEST001");
        });
        
        assertTrue(exception.getMessage().contains("not found"));
    }
    
    @Test
    void testAddGrade() throws DuplicateStudentException, StudentNotFoundException {
        studentService.addStudent("TEST001", "Test", "Student");
        studentService.addGrade("TEST001", "Math", 85.0);
        
        Student student = studentService.getStudent("TEST001");
        assertEquals(1, student.getGradesForCourse("Math").size());
        assertEquals(85.0, student.getGradesForCourse("Math").get(0));
    }
} {
  
}
