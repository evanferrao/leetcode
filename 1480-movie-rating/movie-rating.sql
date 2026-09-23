# Write your MySQL query statement below

(
    SELECT u.name AS results 
    FROM Users u 
    JOIN MovieRating mr ON u.user_id = mr.user_id 
    GROUP BY u.user_id, u.name 
    ORDER BY COUNT(mr.movie_id) DESC, u.name ASC 
    LIMIT 1
)
UNION ALL
(
    SELECT
    M.title AS results
    FROM Movies M
    JOIN MovieRating MR 
    ON (M.movie_id = MR.movie_id)
    WHERE MR.created_at >= '2020-02-01' AND MR.created_at <= '2020-02-29' 
    GROUP BY M.movie_id
    ORDER BY AVG(MR.rating) DESC, M.title ASC
    LIMIT 1
)
