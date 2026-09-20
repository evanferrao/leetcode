# Write your MySQL query statement below
SELECT 'Low Salary' AS category, COUNT(LOW) AS accounts_count FROM (
    SELECT (CASE WHEN income < 20000 THEN 'low' END) AS LOW FROM Accounts
) sub1

UNION

SELECT 'Average Salary' AS category, COUNT(MEDIUM) AS accounts_count FROM (
    SELECT (CASE WHEN income BETWEEN 20000 AND 50000 THEN 'medium' END) AS MEDIUM FROM Accounts
) sub2

UNION

SELECT 'High Salary' AS category, COUNT(HIGH) AS accounts_count FROM (
    SELECT (CASE WHEN income > 50000 THEN 'high' END) AS HIGH FROM Accounts
) sub3