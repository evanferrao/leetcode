# Write your MySQL query statement below
SELECT 
    A.activity_date AS day,
    COUNT(DISTINCT user_id) as active_users
FROM Activity A
WHERE (A.activity_date BETWEEN DATE_SUB('2019-07-27', INTERVAL 29 DAY) AND '2019-07-27')
GROUP BY A.activity_date