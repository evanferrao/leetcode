# Write your MySQL query statement below
SELECT
    E1.employee_id,
    E1.name, 
    COUNT(E2.employee_id) as reports_count, # we can also use COUNT(E1.employee_id) as they will have the same number after the join. E2 just makes more sense as we're counting the number of employees reporting. E1.employee_id will count the manager for every employee present.
    ROUND(AVG(E2.age)) as average_age
FROM Employees E1
JOIN Employees E2
ON E1.employee_id = E2.reports_to
GROUP BY E1.employee_id
ORDER BY E1.employee_id ASC