WITH RANK_TABLE AS (
    SELECT 
        ID, 
        PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) AS PER_RANK
    FROM ECOLI_DATA
)

SELECT 
    ID,
    CASE 
        WHEN (PER_RANK <= 0.25) THEN 'CRITICAL'
        WHEN (PER_RANK <= 0.50) THEN 'HIGH'
        WHEN (PER_RANK <= 0.75) THEN 'MEDIUM'
        ELSE 'LOW'
    END AS COLONY_NAME
FROM RANK_TABLE
ORDER BY ID;
    