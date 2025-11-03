package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;
    
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    
    @Column(name = "email", unique = true, length = 100)
    private String email;
    
    @Column(name = "department", length = 50)
    private String department;
    
    @Column(name = "marks")
    private Integer marks;
    
    // Default constructor (required by Hibernate)
    public Student() {}
    
    // Parameterized constructor
    public Student(String firstName, String lastName, String email, String department, Integer marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.marks = marks;
    }
    
    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public Integer getMarks() {
        return marks;
    }
    
    public void setMarks(Integer marks) {
        this.marks = marks;
    }
    
    @Override
    public String toString() {
        return String.format("Student [ID: %d, Name: %s %s, Email: %s, Department: %s, Marks: %d]",
                studentId, firstName, lastName, email, department, marks);
    }
}
