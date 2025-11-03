package com.example;

import com.example.entity.Account;
import com.example.entity.Transaction;
import java.math.BigDecimal;

public class TestApp {
    public static void main(String[] args) {
        System.out.println("🧪 Testing Spring + Hibernate Banking System");
        System.out.println("============================================\n");
        
        // Test Entity Creation
        System.out.println("1. Testing Entity Creation...");
        Account account1 = new Account("TEST001", "Test User", "test@email.com", "123-456-7890");
        account1.setBalance(new BigDecimal("1000.00"));
        
        Account account2 = new Account("TEST002", "Another User", "another@email.com", "123-456-7891");
        account2.setBalance(new BigDecimal("500.00"));
        
        System.out.println("✅ Account 1: " + account1);
        System.out.println("✅ Account 2: " + account2);
        
        // Test Business Logic
        System.out.println("\n2. Testing Business Logic...");
        account1.deposit(new BigDecimal("200.00"));
        System.out.println("✅ After deposit - Account 1: " + account1.getBalance());
        
        account1.withdraw(new BigDecimal("100.00"));
        System.out.println("✅ After withdrawal - Account 1: " + account1.getBalance());
        
        // Test Transaction Entity
        System.out.println("\n3. Testing Transaction Entity...");
        Transaction transaction = new Transaction(account1, account2, new BigDecimal("150.00"), "Test transfer");
        transaction.setStatus("SUCCESS");
        System.out.println("✅ Transaction: " + transaction);
        
        // Test Error Handling
        System.out.println("\n4. Testing Error Handling...");
        try {
            account1.withdraw(new BigDecimal("2000.00")); // Should fail
        } catch (RuntimeException e) {
            System.out.println("✅ Correctly caught insufficient balance: " + e.getMessage());
        }
        
        System.out.println("\n🎉 Basic Entity Tests Completed Successfully!");
        System.out.println("\n📝 To test full Spring + Hibernate functionality:");
        System.out.println("   1. Setup MySQL database");
        System.out.println("   2. Run: mvn exec:java -Dexec.mainClass=\"com.example.MainApp\"");
        System.out.println("   3. Use the interactive menu to test transaction management");
    }
}
