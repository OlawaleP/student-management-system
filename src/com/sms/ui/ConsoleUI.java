package com.sms.ui;

import com.sms.service.StudentService;
import com.sms.service.GradeService;
import com.sms.service.ReportService;
import com.sms.expception.DuplicateStudentException;
import com.sms.expception.StudentNotFoundException;
import com.sms.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Console-based user interface for the Student Management System
 */
public class ConsoleUI {
    private Scanner scanner;
    private StudentService studentService;
    private GradeService gradeService;
    private ReportService reportService;
    private boolean running;
    
    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.studentService = new StudentService();
        this.gradeService = new GradeService(studentService);
        this.reportService = new ReportService(studentService, gradeService);
        this.running = true;
    }
    
    public void start() {
        while (running) {
            displayMainMenu();
            int choice = getUserChoice(1, 6);
            
            switch (choice) {
                case 1:
                    handleStudentManagement();
                    break;
                case 2:
                    handleGradeManagement();
                    break;
                case 3:
                    handleReports();
                    break;
                case 4:
                    displayHelp();
                    break;
                case 5:
                    loadSampleData();
                    break;
                case 6:
                    exit();
                    break;
            }
        }
        
        scanner.close();
    }
    
    private void displayMainMenu() {
        System.out.println("\n===== Student Management System =====");
        System.out.println("1. Student Management");
        System.out.println("2. Grade Management");
        System.out.println("3. Reports");
        System.out.println("4. Help");
        System.out.println("5. Load Sample Data");
        System.out.println("6. Exit");
        System.out.print("Enter your choice (1-6): ");
    }
    
    private void handleStudentManagement() {
        boolean back = false;
        
        while (!back) {
            System.out.println("\n----- Student Management -----");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. List All Students");
            System.out.println("4. Remove Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice (1-5): ");
            
            int choice = getUserChoice(1, 5);
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudent();
                    break;
                case 3:
                    listAllStudents();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    back = true;
                    break;
            }
        }
    }
    
    private void handleGradeManagement() {
        boolean back = false;
        
        while (!back) {
            System.out.println("\n----- Grade Management -----");
            System.out.println("1. Add Grade");
            System.out.println("2. View Student Grades");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice (1-3): ");
            
            int choice = getUserChoice(1, 3);
            
            switch (choice) {
                case 1:
                    addGrade();
                    break;
                case 2:
                    viewStudentGrades();
                    break;
                case 3:
                    back = true;
                    break;
            }
        }
    }
    
    private void handleReports() {
        boolean back = false;
        
        while (!back) {
            System.out.println("\n----- Reports -----");
            System.out.println("1. Student Report");
            System.out.println("2. Class Report");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice (1-3): ");
            
            int choice = getUserChoice(1, 3);
            
            switch (choice) {
                case 1:
                    generateStudentReport();
                    break;
                case 2:
                    generateClassReport();
                    break;
                case 3:
                    back = true;
                    break;
            }
        }
    }
    
    private void addStudent() {
        System.out.println("\n--- Add Student ---");
        
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        
        try {
            studentService.addStudent(id, firstName, lastName);
            System.out.println("Student added successfully!");
        } catch (DuplicateStudentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void viewStudent() {
        System.out.println("\n--- View Student ---");
        
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        try {
            Student student = studentService.getStudent(id);
            System.out.println("\nStudent Details:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getFullName());
            System.out.println("GPA: " + String.format("%.2f", student.calculateGPA()));
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void listAllStudents() {
        System.out.println("\n--- All Students ---");
        
        var students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }
        
        System.out.println("Total students: " + students.size());
        for (Student student : students) {
            System.out.println(student);
        }
    }
    
    private void removeStudent() {
        System.out.println("\n--- Remove Student ---");
        
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        try {
            System.out.print("Are you sure you want to remove this student? (y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (confirm.equals("y") || confirm.equals("yes")) {
                studentService.removeStudent(id);
                System.out.println("Student removed successfully!");
            } else {
                System.out.println("Operation cancelled.");
            }
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void addGrade() {
        System.out.println("\n--- Add Grade ---");
        
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter course name: ");
        String course = scanner.nextLine();
        
        System.out.print("Enter grade (0-100): ");
        double grade = getUserDouble(0, 100);
        
        try {
            gradeService.addGrade(id, course, grade);
            System.out.println("Grade added successfully!");
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void viewStudentGrades() {
        System.out.println("\n--- View Student Grades ---");
        
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        try {
            Student student = studentService.getStudent(id);
            System.out.println("\nGrades for " + student.getFullName() + ":");
            
            Map<String, List<Double>> allGrades = student.getAllGrades();
            if (allGrades.isEmpty()) {
                System.out.println("No grades recorded for this student.");
                return;
            }
            
            for (String course : allGrades.keySet()) {
                System.out.println(course + ": " + allGrades.get(course) + 
                        " (Average: " + String.format("%.2f", student.calculateCourseAverage(course)) + ")");
            }
            
            System.out.println("Overall GPA: " + String.format("%.2f", student.calculateGPA()));
            
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void generateStudentReport() {
        System.out.println("\n--- Generate Student Report ---");
        
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        reportService.generateStudentReport(id);
    }
    
    private void generateClassReport() {
        System.out.println("\n--- Generate Class Report ---");
        reportService.generateClassReport();
    }
    
    private void displayHelp() {
        System.out.println("\n===== Student Management System Help =====");
        System.out.println("This system allows you to manage students and their grades.");
        System.out.println("\nMain Features:");
        System.out.println("1. Student Management - Add, view, and remove students");
        System.out.println("2. Grade Management - Record and view student grades");
        System.out.println("3. Reports - Generate detailed reports for students and the class");
        System.out.println("\nTips:");
        System.out.println("- Student IDs must be unique");
        System.out.println("- Grades should be entered on a scale of 0-100");
        System.out.println("- The system automatically calculates GPAs based on course averages");
        System.out.println("======================================");
    }
    
    private void loadSampleData() {
        System.out.println("\nLoading sample data...");
        
        try {
            // Add sample students
            try {
                studentService.addStudent("S001", "John", "Doe");
                studentService.addStudent("S002", "Jane", "Smith");
                studentService.addStudent("S003", "Alice", "Johnson");
            } catch (DuplicateStudentException e) {
                // If students already exist, that's fine, continue adding grades
                System.out.println("Some students already exist. Adding grades only.");
            }
            
            // Add sample grades
            try {
                gradeService.addGrade("S001", "Math", 85.5);
                gradeService.addGrade("S001", "English", 92.0);
                gradeService.addGrade("S001", "Science", 78.5);
                
                gradeService.addGrade("S002", "Math", 90.0);
                gradeService.addGrade("S002", "English", 88.5);
                gradeService.addGrade("S002", "Science", 95.0);
                
                gradeService.addGrade("S003", "Math", 72.5);
                gradeService.addGrade("S003", "English", 80.0);
                gradeService.addGrade("S003", "Science", 85.5);
            } catch (StudentNotFoundException e) {
                System.out.println("Error adding grades: " + e.getMessage());
            }
            
            System.out.println("Sample data loaded successfully!");
            
        } catch (Exception e) {
            System.out.println("Error loading sample data: " + e.getMessage());
        }
    }
    
    private void exit() {
        System.out.println("\nExiting Student Management System. Goodbye!");
        running = false;
    }
    
    private int getUserChoice(int min, int max) {
        int choice = -1;
        boolean valid = false;
        
        while (!valid) {
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
                
                if (choice >= min && choice <= max) {
                    valid = true;
                } else {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        
        return choice;
    }
    
    private double getUserDouble(double min, double max) {
        double value = -1;
        boolean valid = false;
        
        while (!valid) {
            try {
                String input = scanner.nextLine();
                value = Double.parseDouble(input);
                
                if (value >= min && value <= max) {
                    valid = true;
                } else {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        
        return value;
    }
}