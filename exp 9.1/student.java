package com.example;

public class Student {
    private String name;
    private int rollNumber;
    private Course course;
    
    public Student() {}
    
    public void setCourse(Course course) {
        this.course = course;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    public void displayStudentInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.print("Enrolled in: ");
        if (course != null) {
            course.displayCourseInfo();
        }
    }
}
