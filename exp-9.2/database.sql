-- Create database and user for Hibernate application
CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;

-- Create user (adjust password as needed)
CREATE USER IF NOT EXISTS 'hibernate_user'@'localhost' IDENTIFIED BY 'hibernate123';
GRANT ALL PRIVILEGES ON studentdb.* TO 'hibernate_user'@'localhost';
FLUSH PRIVILEGES;

-- The students table will be automatically created by Hibernate
-- due to hibernate.hbm2ddl.auto=update configuration

-- Verify database creation
SHOW DATABASES;
SELECT User, Host FROM mysql.user WHERE User = 'hibernate_user';
