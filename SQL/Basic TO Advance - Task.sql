SELECT * FROM aspiresys.employees;
SELECT * FROM aspiresys.department;
SELECT * FROM aspiresys.projects;

-- SQL Questions

-- Basic Queries

-- 1. Write a query to display all rows and columns from the employees table.
SELECT * FROM aspiresys.employees;

-- 2. Retrieve only the name and salary of all employees from the employees table.
SELECT Emp_Name, Salary FROM aspiresys.employees;

-- 3. Write a query to find all employees whose salary is greater than 50,000.
SELECT * FROM aspiresys.employees
WHERE Salary > 50000;

-- 4. List all employees who joined the company in the year 2020.
SELECT * FROM aspiresys.employees
WHERE YEAR(Date_Of_Joining) = 2020;

-- 5. Retrieve the details of employees whose names start with the letter 'A'.
SELECT * FROM aspiresys.employees
WHERE Emp_Name LIKE "A%";

-- Aggregate Functions

-- 1. Write a query to calculate the average salary of all employees.
SELECT AVG(Salary) FROM aspiresys.employees;

-- 2. Find the total number of employees in the company.
SELECT COUNT(*) FROM aspiresys.employees;

-- 3. Write a query to find the highest salary in the employees table.
SELECT MAX(Salary) FROM aspiresys.employees;

-- 4. Calculate the total salary paid by the company for all employees.
SELECT SUM(Salary) FROM aspiresys.employees;

-- 5. Find the count of employees in each department.
SELECT Dept_ID, count(*) AS Total_Emp FROM aspiresys.employees
GROUP BY Dept_ID;

-- Joins

-- 1. Write a query to retrieve employee names along with their department names (using employees and departments tables).
SELECT Emp_Name, Department FROM Employees LEFT JOIN Department 
ON Employees.Dept_ID = Department.Dept_ID;

-- 2. List all employees who have a manager (self-join on employees table).
SELECT E1.Emp_Name, E2.Dept_Manager FROM Employees E1 JOIN Department E2
WHERE E1.Dept_ID = E2.Dept_ID;

-- 3. Find the names of employees who are working on multiple projects (using employees and projects tables).
SELECT Emp_Name FROM Employees JOIN Projects 
WHERE Employees.Dept_ID = Projects.Dept_ID
GROUP BY Emp_Name
HAVING COUNT(DISTINCT Project_Name)>1;

-- 4. Write a query to display all projects and the employees assigned to them.
SELECT Emp_Name, Project_Name FROM Employees JOIN Projects 
WHERE Employees.Dept_ID = Projects.Dept_ID;

-- 5. Retrieve the names of employees who do not belong to any department.
SELECT Emp_Name, d1.Department FROM Employees JOIN Department d1
ON Employees.Dept_ID = d1.Dept_ID AND d1.Department is NULL;

-- Subqueries

-- 1. Write a query to find the employees with the second-highest salary.
SELECT Salary FROM Employees 
WHERE Salary < (
	SELECT MAX(Salary) FROM Employees
) LIMIT 1;

-- 2. Retrieve the names of employees whose salary is above the department average salary.
SELECT e1.Emp_Name, Avg_Salary.Sal FROM Employees e1 JOIN (
	SELECT Dept_ID, AVG(Salary) AS Sal FROM Employees
    GROUP BY Dept_ID 
) AS Avg_Salary 
ON e1.Dept_ID = Avg_Salary.Dept_ID 
WHERE e1.Salary > Avg_Salary.Sal;

-- 3. Find employees who earn more than the average salary of the entire company.
SELECT Emp_Name, Salary FROM Employees 
WHERE Salary > (
	SELECT AVG(Salary) FROM Employees
);

-- 4. Write a query to find the department with the highest number of employees.

SELECT Dept_ID, COUNT(*) AS Employee_Count FROM Employees
GROUP BY Dept_ID
LIMIT 1;

-- 5. List all employees who work in a department located in 'New York'.
SELECT Emp_Name, d1.Location FROM Employees JOIN Department d1 
ON Employees.Dept_ID = d1.Dept_ID 
WHERE d1.Location = 'New York';

-- Set Operators

-- 1. Write a query to find employees who work in either the 'HR' or 'Finance' department.
SELECT Emp_Name, d1.Dept_Team FROM Employees
WHERE Dept_ID IN (
	SELECT Dept_ID FROM Department d1
	WHERE d1.Dept_Team IN ("HR", "Finance")
);
