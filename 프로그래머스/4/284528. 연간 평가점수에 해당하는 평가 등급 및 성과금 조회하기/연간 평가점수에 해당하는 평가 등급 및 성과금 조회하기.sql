WITH EMP_GRADE AS (
    SELECT 
        EM.EMP_NO
        , EMP_NAME 
        , (CASE WHEN AVG(SCORE) >= 96 THEN 'S'
            WHEN AVG(SCORE) >= 90 THEN 'A'
            WHEN AVG(SCORE) >= 80 THEN 'B'
            ELSE 'C'
       END) AS GRADE
        , SAL
    FROM HR_EMPLOYEES EM
    JOIN HR_GRADE GD
    ON EM.EMP_NO = GD.EMP_NO
    GROUP BY EMP_NO
)
SELECT 
    EMP_NO
    , EMP_NAME
    , GRADE 
    , SAL * (
        CASE WHEN GRADE = 'S' THEN 0.2
             WHEN GRADE = 'A' THEN 0.15
             WHEN GRADE = 'B' THEN 0.1
             ELSE 0
        END
        ) AS BONUS
FROM EMP_GRADE
ORDER BY EMP_NO ASC;
