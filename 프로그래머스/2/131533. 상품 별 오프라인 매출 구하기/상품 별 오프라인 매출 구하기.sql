-- 코드를 입력하세요
SELECT PRODUCT_CODE, (PRICE * SALES_AMOUNT) SALES 
FROM PRODUCT 
JOIN (
    SELECT PRODUCT_ID, SUM(SALES_AMOUNT) SALES_AMOUNT
    FROM OFFLINE_SALE
    GROUP BY PRODUCT_ID
    ) sale
USING (PRODUCT_ID)
ORDER BY SALES desc, PRODUCT_CODE asc;
