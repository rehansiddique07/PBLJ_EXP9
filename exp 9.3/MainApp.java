package com.example;

import com.example.config.AppConfig;
import com.example.entity.Account;
import com.example.entity.Transaction;
import com.example.service.BankingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static BankingService bankingService;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🏦 Spring + Hibernate Banking System - DEMO MODE");
        System.out.println("================================================\n");
        
        System.out.println("⚠️  Note: Running in demo mode without database connection");
        System.out.println("   To use full features, configure MySQL database first\n");
        
        // Test basic functionality without Spring context
        testBasicFunctionality();
        
        scanner.close();
    }
    
    private static void testBasicFunctionality() {
        System.out.println("🧪 Testing Basic Banking Operations (No Database)\n");
        
        // Create sample accounts
        Account acc1 = new Account("ACC001", "John Doe", "john@email.com", "123-456-7890");
        acc1.setBalance(new BigDecimal("1000.00"));
        
        Account acc2 = new Account("ACC002", "Jane Smith", "jane@email.com", "123-456-7891");
        acc2.setBalance(new BigDecimal("500.00"));
        
        System.out.println("✅ Created Sample Accounts:");
        System.out.println("   " + acc1);
        System.out.println("   " + acc2);
        
        // Test operations
        System.out.println("\n💸 Testing Money Transfer Simulation:");
        try {
            // Simulate transfer
            acc1.withdraw(new BigDecimal("200.00"));
            acc2.deposit(new BigDecimal("200.00"));
            
            System.out.println("✅ Transfer successful!");
            System.out.println("   " + acc1.getAccountHolderName() + " new balance: $" + acc1.getBalance());
            System.out.println("   " + acc2.getAccountHolderName() + " new balance: $" + acc2.getBalance());
            
            // Create transaction record
            Transaction tx = new Transaction(acc1, acc2, new BigDecimal("200.00"), "Test transfer");
            tx.setStatus("SUCCESS");
            System.out.println("   Transaction: " + tx);
            
        } catch (Exception e) {
            System.out.println("❌ Transfer failed: " + e.getMessage());
        }
        
        // Test error case
        System.out.println("\n🧪 Testing Error Handling:");
        try {
            acc1.withdraw(new BigDecimal("5000.00"));
        } catch (RuntimeException e) {
            System.out.println("✅ Correctly prevented overdraw: " + e.getMessage());
        }
        
        System.out.println("\n🎉 Demo completed successfully!");
        System.out.println("\n📝 To run full application with database:");
        System.out.println("   1. Setup MySQL database");
        System.out.println("   2. Update database credentials in AppConfig.java");
        System.out.println("   3. Run: mvn exec:java -Dexec.mainClass=\"com.example.MainApp\"");
    }
}
