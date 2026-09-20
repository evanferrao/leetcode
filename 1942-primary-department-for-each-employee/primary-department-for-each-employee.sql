# Write your MySQL query statement below
SELECT 
    E1.employee_id,
    E1.department_id
FROM Employee E1 
WHERE E1.primary_flag = 'Y'

UNION

SELECT 
    E2.employee_id,
    E2.department_id
FROM Employee E2
GROUP BY E2.employee_id
HAVING COUNT(E2.employee_id) = 1