package com.example.dao;

import com.example.entity.Student;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class StudentDAO {
    
    // CREATE - Add new student
    public Long addStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(student);
            transaction.commit();
            System.out.println(" Student added successfully with ID: " + id);
            return id;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println(" Error adding student: " + e.getMessage());
            return null;
        }
    }
    
    // READ - Get student by ID
    public Student getStudentById(Long studentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, studentId);
        } catch (Exception e) {
            System.err.println(" Error fetching student: " + e.getMessage());
            return null;
        }
    }
    
    // READ - Get all students
    public List<Student> getAllStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery("from Student", Student.class);
            return query.list();
        } catch (Exception e) {
            System.err.println(" Error fetching students: " + e.getMessage());
            return null;
        }
    }
    
    // UPDATE - Update student
    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
            System.out.println(" Student updated successfully");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating student: " + e.getMessage());
        }
    }
    
    // DELETE - Delete student by ID
    public void deleteStudent(Long studentId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                session.delete(student);
                System.out.println(" Student deleted successfully");
            } else {
                System.out.println(" Student not found with ID: " + studentId);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println(" Error deleting student: " + e.getMessage());
        }
    }
}
