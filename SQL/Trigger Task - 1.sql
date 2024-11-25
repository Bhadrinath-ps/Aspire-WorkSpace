-- Create a Trigger that displays the count of rows after inserting a new record to a employee table.

DELIMITER $$

CREATE TRIGGER after_employee_insert
AFTER INSERT ON Employees
FOR EACH ROW
BEGIN
    DECLARE Row_Count INT;

    -- Get the current number of rows in the Employees table
    SELECT COUNT(*) INTO Row_Count
    FROM Employees;

    -- Insert the row count into the EmployeeLog table
    INSERT INTO EmployeeLog (row_count) 
    VALUES (Row_Count);
END$$

DELIMITER ;

CREATE TABLE EmployeeLog (
    row_count INT
);

INSERT INTO Employees (Emp_ID, Emp_Name, DOB, Blood_Group, Date_Of_Joining, Designation, Team, Salary, Address, Location, Pin_Code) 
VALUES
(12317, 'Ravi Shankar', '1995-05-10', 'A-', '2024-11-23', 'Senior Developer', 'DL Team', 90000, '45, 1st Cross, Adyar', 'Chennai', 600020);

SELECT * FROM EmployeeLog;

