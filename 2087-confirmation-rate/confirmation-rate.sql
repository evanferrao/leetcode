# Write your MySQL query statement below
SELECT
    S.user_id,
    ROUND(COALESCE((COUNT(CASE WHEN C.action = 'confirmed' THEN 1 END)/COUNT(S.user_id)),0),2) AS confirmation_rate
FROM Signups S
LEFT JOIN Confirmations C
ON (S.user_id = C.user_id)
GROUP BY S.user_id
