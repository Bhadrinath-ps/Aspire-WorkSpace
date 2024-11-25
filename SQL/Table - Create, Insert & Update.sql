-- DATABASE CREATION

CREATE DATABASE aspiresys;

USE aspiresys;

<<<<<<< HEAD
-- TABLE CREATION (EMPLOYEE)	
=======
-- TABLE CREATION
>>>>>>> 516cbab5117239afe3b37a1a24efce5f9b130c16

CREATE TABLE Employees (
Emp_ID INT PRIMARY KEY,
Emp_Name varchar(30) NOT NULL,
DOB Date NOT NULL,
Blood_Group varchar(5) NOT NULL,
Date_Of_Joining Date NOT NULL,
Designation varchar(20) NOT NULL,
Team varchar(20) NOT NULL,
Salary INT NOT NULL,
Address varchar(50) NOT NULL,
Location varchar(10) NOT NULL,
<<<<<<< HEAD
Pin_Code INT NOT NULL,
DEPT_ID varchar(10)
=======
Pin_Code INT NOT NULL
>>>>>>> 516cbab5117239afe3b37a1a24efce5f9b130c16
);

INSERT INTO Employees (Emp_ID, Emp_Name, DOB, Blood_Group, Date_Of_Joining, Designation, Team, Salary, Address, Location, Pin_Code) 
VALUES
(12311, 'Arun Kumar', '2001-09-19', 'O+', '2024-11-23', 'Manager', 'TN Team', 75000, '12, 2nd Street, Kotturpuram', 'Chennai', 600085),
(12312, 'Priya Raj', '2004-05-23', 'A-', '2024-11-22', 'Developer', 'SL Team', 65000, '24, Anna Nagar East', 'Chennai', 600102),
(12313, 'Suresh Babu', '2001-11-11', 'B+', '2023-11-11', 'Analyst', 'TN Team', 60000, '5, Muthurama Iyer Road, T Nagar', 'Chennai', 600017),
(12314, 'Nalini Devi', '2000-08-20', 'AB-', '2022-06-05', 'HR Specialist', 'HR Team', 55000, '45, Arumbakkam Main Road', 'Chennai', 600106),
(12315, 'Ravi Chandran', '2002-12-12', 'O-', '2019-09-09', 'Sales Executive', 'SE Team', 50000, '21, Sathyamangalam, Gandhinagar', 'Coimbatore', 641002);

SELECT * FROM Employees;

<<<<<<< HEAD
-- UPDATE COLUMN AND INSERTING VALUES

ALTER TABLE Employees
ADD COLUMN Dept_ID VARCHAR(10);

ALTER TABLE Employees
ADD CONSTRAINT fk_dept_id FOREIGN KEY (Dept_ID) REFERENCES Department(Dept_ID);

UPDATE Employees
SET DEPT_ID = "DEPT001" WHERE Emp_ID = 12311;
UPDATE Employees
SET DEPT_ID = "DEPT001" WHERE Emp_ID = 12312;
UPDATE Employees
SET DEPT_ID = "DEPT001" WHERE Emp_ID = 12313;
UPDATE Employees
SET DEPT_ID = "DEPT002" WHERE Emp_ID = 12314;
UPDATE Employees
SET DEPT_ID = "DEPT002" WHERE Emp_ID = 12315;
UPDATE Employees
SET DEPT_ID = "DEPT002" WHERE Emp_ID = 12316;
UPDATE Employees
SET DEPT_ID = "DEPT003" WHERE Emp_ID = 12317;

-- TABLE CREATION (DEPT)

CREATE TABLE Department (
    Dept_ID VARCHAR(10) PRIMARY KEY,
    Department VARCHAR(100) NOT NULL,
    Dept_Manager VARCHAR(10) NOT NULL
);

DROP TABLE Department;

ALTER TABLE Department
ADD COLUMN Dept_Manager VARCHAR(10);

UPDATE Department
SET Dept_Manager = "Silpa" WHERE Dept_ID = "DEPT001";
UPDATE Department
SET Dept_Manager = "Saraswathi" WHERE Dept_ID = "DEPT002";
UPDATE Department
SET Dept_Manager = "Anusiya" WHERE Dept_ID = "DEPT003";

INSERT INTO Department (Dept_ID, Department, Dept_Manager)
values 
('DEPT001', 'Insurance', 12311), 
('DEPT002', 'D&A', 12312),   
('DEPT003', 'Testing', 12313);

ALTER Table Department
ADD COLUMN Location VARCHAR(10) NOT NULL;

UPDATE Department SET Location = "New York" WHERE Dept_ID = "Dept001";
UPDATE Department SET Location = "London" WHERE Dept_ID = "Dept002";
UPDATE Department SET Location = "Europe" WHERE Dept_ID = "Dept003";

ALTER Table Department
ADD COLUMN Dept_Team VARCHAR(10) NOT NULL;

UPDATE Department SET Dept_Team = "HR" WHERE Dept_ID = "Dept001";
UPDATE Department SET Dept_Team = "Finance" WHERE Dept_ID = "Dept002";
UPDATE Department SET Dept_Team = "Developer" WHERE Dept_ID = "Dept003";

SELECT * FROM Department;

-- TABLE CREATION (PROJECT)

CREATE TABLE Projects (
    Project_ID INT PRIMARY KEY,
    Project_Name VARCHAR(100) NOT NULL,
    Project_Manager VARCHAR(10),
    Dept_ID VARCHAR(10),
    FOREIGN KEY (Dept_ID) REFERENCES Employees(Dept_ID)
);

INSERT INTO Projects (Project_ID, Project_Name, Project_Manager, Dept_ID) 
VALUES 
(1, 'Movie Ticket Booking', 'Silpa', 'DEPT001'),
(2, 'Fitness Equipments', 'Saraswathi', 'DEPT002'),
(3, 'Project C', 'Anusiya', 'DEPT003');

SELECT * FROM Projects;

ALTER TABLE Projects
DROP COLUMN Emp_ID;

ALTER TABLE Projects
DROP FOREIGN KEY fk_emp_id;
=======
>>>>>>> 516cbab5117239afe3b37a1a24efce5f9b130c16
