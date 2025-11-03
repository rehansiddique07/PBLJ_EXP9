# 🏦 Spring + Hibernate Banking System with Transaction Management

A comprehensive banking application demonstrating Spring Framework's declarative transaction management integrated with Hibernate ORM for secure money transfers with ACID properties.

## 📋 Project Overview

This enterprise-grade banking system showcases real-world financial transaction processing with guaranteed data consistency through Spring's `@Transactional` management and Hibernate ORM.

### 🎯 Key Business Features
- **Secure Money Transfers** between accounts with atomic operations
- **Automatic Rollback** on insufficient funds or system failures
- **Complete Audit Trail** of all financial transactions
- **Account Management** with balance tracking
- **Transaction History** with detailed records
- **ACID Compliance** for all financial operations

## 🏗️ System Architecture

### Technology Stack
- **Backend Framework**: Spring Framework 5.3.30
- **ORM**: Hibernate 5.6.15.Final
- **Database**: MySQL 8.0.33
- **Connection Pool**: HikariCP 5.0.1
- **Build Tool**: Maven 3.8.1
- **Java Version**: 11+

### Architectural Pattern
Presentation Layer (Console) → Business Layer (@Service) → Data Access Layer (@Repository) → Database

text

## 📁 Project Structure
spring-hibernate-banking/
├── src/main/java/com/example/
│ ├── config/
│ │ └── AppConfig.java # Spring & Hibernate Configuration
│ ├── entity/ # JPA Entity Classes
│ │ ├── Account.java # Bank Account Entity
│ │ └── Transaction.java # Financial Transaction Entity
│ ├── dao/ # Data Access Objects
│ │ ├── AccountDAO.java # Account DAO Interface
│ │ ├── AccountDAOImpl.java # Account DAO Implementation
│ │ ├── TransactionDAO.java # Transaction DAO Interface
│ │ └── TransactionDAOImpl.java # Transaction DAO Implementation
│ ├── service/ # Business Logic Layer
│ │ ├── BankingService.java # Service Interface
│ │ └── BankingServiceImpl.java # Service Implementation (@Transactional)
│ ├── MainApp.java # Main Application
│ ├── SimpleTest.java # Basic Functionality Test
│ └── TestApp.java # Comprehensive Test Suite
├── src/main/resources/ # Configuration Files
├── setup_banking_db.sql # Database Setup Script
├── pom.xml # Maven Dependencies
└── README.md # Project Documentation

text

## 🚀 Quick Start Guide

### Prerequisites
- **Java Development Kit** (JDK 11 or higher)
- **Apache Maven** (3.6 or higher)
- **MySQL Server** (8.0 or higher)
- **Git** (optional, for version control)

### Step 1: Environment Setup
```bash
# Verify installations
java -version
mvn -version
mysql --version
