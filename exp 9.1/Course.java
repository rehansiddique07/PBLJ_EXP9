package com.example;

public class Course {
    private String courseName;
    private int credits;
    
    public Course() {}
    
    public Course(String courseName, int credits) {
        this.courseName = courseName;
        this.credits = credits;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    public void displayCourseInfo() {
        System.out.println("Course: " + courseName + " | Credits: " + credits);
    }
}
