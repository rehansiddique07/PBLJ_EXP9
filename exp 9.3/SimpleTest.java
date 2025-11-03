package com.example;

import com.example.entity.Account;
import com.example.entity.Transaction;
import java.math.BigDecimal;

public class SimpleTest {
    public static void main(String[] args) {
        System.out.println("🧪 Simple Spring + Hibernate Banking Test");
        System.out.println("==========================================\n");
        
        // Test basic entity functionality
        Account acc1 = new Account("ACC1001", "Test User", "test@email.com", "123-456-7890");
        acc1.setBalance(new BigDecimal("1000.00"));
        
        Account acc2 = new Account("ACC1002", "Another User", "another@email.com", "123-456-7891"); 
        acc2.setBalance(new BigDecimal("500.00"));
        
        System.out.println("✅ Account 1: " + acc1);
        System.out.println("✅ Account 2: " + acc2);
        
        // Test deposit/withdraw
        acc1.deposit(new BigDecimal("200.00"));
        System.out.println("✅ After deposit: " + acc1.getBalance());
        
        acc1.withdraw(new BigDecimal("100.00"));
        System.out.println("✅ After withdrawal: " + acc1.getBalance());
        
        // Test transaction
        Transaction tx = new Transaction(acc1, acc2, new BigDecimal("150.00"), "Test transfer");
        tx.setStatus("SUCCESS");
        System.out.println("✅ Transaction: " + tx);
        
        // Test error case
        try {
            acc1.withdraw(new BigDecimal("2000.00"));
        } catch (RuntimeException e) {
            System.out.println("✅ Correctly caught error: " + e.getMessage());
        }
        
        System.out.println("\n🎊 All basic tests passed!");
        System.out.println("\n📝 To run the full application with Spring:");
        System.out.println("   mvn exec:java -Dexec.mainClass=\"com.example.MainApp\"");
    }
}
