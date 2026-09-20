# Write your MySQL query statement below
SELECT
    P1.product_id,
    COALESCE(
        (
            SELECT P2.new_price
            FROM Products P2
            WHERE (P1.product_id = P2.product_id AND P2.change_date <= '2019-08-16')
            ORDER BY P2.change_date DESC
            LIMIT 1
        ),
        10
    ) AS price
FROM (
    SELECT DISTINCT product_id
    FROM Products
) AS P1