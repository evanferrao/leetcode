# Write your MySQL query statement below
SELECT 
    C1.visited_on,
    SUM(C2.amount) AS amount,
    ROUND(SUM(C2.amount) / 7, 2) AS average_amount
FROM (SELECT DISTINCT visited_on FROM Customer) AS C1
JOIN Customer AS C2
ON C1.visited_on BETWEEN C2.visited_on AND DATE_ADD(C2.visited_on, INTERVAL 6 DAY)
WHERE C1.visited_on >= DATE_ADD((SELECT MIN(visited_on) FROM Customer), INTERVAL 6 DAY)
GROUP BY C1.visited_on
ORDER BY C1.visited_on