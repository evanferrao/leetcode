# Write your MySQL query statement below
SELECT
    S.product_id,
    S.year AS first_year,
    S.quantity,
    S.price
FROM Sales S
WHERE (S.product_id, S.year) IN (
    SELECT
        product_id,
        MIN(year)
    FROM Sales
    GROUP BY product_id
);