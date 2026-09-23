# Write your MySQL query statement below
SELECT 
    R.contest_id,
    ROUND(
        COALESCE(
            (
                100 * COUNT(R.user_id) / (SELECT COUNT(*) FROM Users)
            ),
            0
        ),
        2
    ) as percentage
FROM Register R
LEFT JOIN Users U
ON (U.user_id = R.user_id)
GROUP BY R.contest_id
ORDER BY percentage DESC, R.contest_id ASC