package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public Course course() {
        Course course = new Course();
        course.setCourseName("Spring Framework & Hibernate");
        course.setCredits(4);
        return course;
    }
    
    @Bean
    public Student student() {
        Student student = new Student();
        student.setName("MD MABUD");
        student.setRollNumber(13610);
        student.setCourse(course());
        return student;
    }
}
