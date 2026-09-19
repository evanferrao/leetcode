# Write your MySQL query statement below
SELECT 
    T.teacher_id,
    COUNT(DISTINCT subject_id) as cnt
FROM Teacher T
GROUP BY T.teacher_id