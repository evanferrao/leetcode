# Write your MySQL query statement below
SELECT 
    Q1.query_name,
    ROUND(
        COALESCE(
            (SUM(Q1.rating/Q1.position) / COUNT(Q1.query_name)),
            0
        ),
        2
    ) AS quality,
    ROUND (
        COALESCE(
            ( 100 * COUNT(CASE WHEN Q1.rating < 3 THEN 1 END) / COUNT(Q1.query_name)),
            0
        ),
        2
    ) AS poor_query_percentage
FROM Queries Q1
GROUP BY Q1.query_name
