-- VIEW CONCEPT 01

CREATE VIEW Chennai_TN_Employees AS
SELECT * 
FROM Employees
WHERE Location = 'Chennai' AND Team LIKE '%TN%';

SELECT * FROM Chennai_TN_Employees;

-- VIEW CONCEPT 02

CREATE VIEW SeniorMost_Employees AS
SELECT *
FROM Employees
ORDER BY Date_Of_Joining 
LIMIT 1;

SELECT * FROM SeniorMost_Employees;