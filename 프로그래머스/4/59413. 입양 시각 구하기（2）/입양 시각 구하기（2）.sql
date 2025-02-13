WITH RECURSIVE HOUR_LIST (HOUR) AS (
    SELECT 0 AS HOUR
    UNION ALL
    SELECT HOUR + 1
    FROM HOUR_LIST
    WHERE HOUR < 23
)
SELECT 
    HL.HOUR
    , IFNULL(CNT, 0) AS COUNT
FROM HOUR_LIST HL
LEFT JOIN (
    SELECT 
        HOUR(DATETIME) AS HOUR
        , COUNT(HOUR(DATETIME)) AS CNT
    FROM ANIMAL_OUTS AO
    GROUP BY HOUR(DATETIME) 
) AO
ON AO.HOUR = HL.HOUR
ORDER BY HOUR ASC;