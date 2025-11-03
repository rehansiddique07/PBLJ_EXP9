package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("=== Spring Dependency Injection Demo ===\n");
        
        // Initialize Spring Context
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // Retrieve Student bean
        Student student = context.getBean(Student.class);
        
        // Display student info (demonstrates DI)
        student.displayStudentInfo();
        
        System.out.println("Dependency Injection successful!");
        System.out.println("Course was automatically injected into Student\n");
        
        ((AnnotationConfigApplicationContext) context).close();
    }
}
