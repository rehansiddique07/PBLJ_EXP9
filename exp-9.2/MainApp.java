package com.example;

import com.example.dao.StudentDAO;
import com.example.entity.Student;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static StudentDAO studentDAO = new StudentDAO();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("🎓 Hibernate Student CRUD Application");
        System.out.println("=====================================\n");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    getStudentById();
                    break;
                case 3:
                    getAllStudents();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    running = false;
                    System.out.println("👋 Thank you for using Student CRUD Application!");
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n📋 MENU:");
        System.out.println("1. Add Student");
        System.out.println("2. Get Student by ID");
        System.out.println("3. Get All Students");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }
    
    private static void addStudent() {
        System.out.println("\n➕ ADD NEW STUDENT");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        
        int marks = getIntInput("Enter Marks: ");
        
        Student student = new Student(firstName, lastName, email, department, marks);
        studentDAO.addStudent(student);
    }
    
    private static void getStudentById() {
        System.out.println("\n🔍 GET STUDENT BY ID");
        Long id = getLongInput("Enter Student ID: ");
        Student student = studentDAO.getStudentById(id);
        
        if (student != null) {
            System.out.println("✅ Student Found:");
            System.out.println(student);
        } else {
            System.out.println("❌ Student not found with ID: " + id);
        }
    }
    
    private static void getAllStudents() {
        System.out.println("\n📊 ALL STUDENTS");
        List<Student> students = studentDAO.getAllStudents();
        
        if (students != null && !students.isEmpty()) {
            System.out.println("Total Students: " + students.size());
            System.out.println("------------------------");
            for (Student student : students) {
                System.out.println(student);
            }
        } else {
            System.out.println("❌ No students found in database.");
        }
    }
    
    private static void updateStudent() {
        System.out.println("\n✏️ UPDATE STUDENT");
        Long id = getLongInput("Enter Student ID to update: ");
        Student student = studentDAO.getStudentById(id);
        
        if (student != null) {
            System.out.println("Current Details: " + student);
            
            System.out.print("Enter New First Name (current: " + student.getFirstName() + "): ");
            String firstName = scanner.nextLine();
            if (!firstName.isEmpty()) student.setFirstName(firstName);
            
            System.out.print("Enter New Last Name (current: " + student.getLastName() + "): ");
            String lastName = scanner.nextLine();
            if (!lastName.isEmpty()) student.setLastName(lastName);
            
            System.out.print("Enter New Email (current: " + student.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) student.setEmail(email);
            
            System.out.print("Enter New Department (current: " + student.getDepartment() + "): ");
            String department = scanner.nextLine();
            if (!department.isEmpty()) student.setDepartment(department);
            
            System.out.print("Enter New Marks (current: " + student.getMarks() + "): ");
            String marksInput = scanner.nextLine();
            if (!marksInput.isEmpty()) {
                student.setMarks(Integer.parseInt(marksInput));
            }
            
            studentDAO.updateStudent(student);
        } else {
            System.out.println("❌ Student not found with ID: " + id);
        }
    }
    
    private static void deleteStudent() {
        System.out.println("\n🗑️ DELETE STUDENT");
        Long id = getLongInput("Enter Student ID to delete: ");
        studentDAO.deleteStudent(id);
    }
    
    private static int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }
    
    private static Long getLongInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextLong()) {
            System.out.print("❌ Please enter a valid number: ");
            scanner.next();
        }
        Long value = scanner.nextLong();
        scanner.nextLine(); // consume newline
        return value;
    }
}
