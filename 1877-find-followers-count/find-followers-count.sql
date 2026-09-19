# Write your MySQL query statement below
SELECT 
    F.user_id,
    COUNT(F.user_id) as followers_count
FROM Followers F
GROUP BY F.user_id
ORDER BY F.user_id ASC