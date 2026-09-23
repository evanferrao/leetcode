# Write your MySQL query statement below
SELECT E.employee_id
FROM Employees E 
WHERE E.salary < 30000 AND E.manager_id NOT IN (
    SELECT employee_id
    FROM Employees
)
ORDER BY E.employee_id ASC