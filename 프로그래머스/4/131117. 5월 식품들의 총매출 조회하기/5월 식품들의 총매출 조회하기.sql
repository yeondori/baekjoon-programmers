-- 코드를 입력하세요
# SELECT PRODUCT_ID, PRODUCT_NAME, (PRICE * TOTAL_AMOUNT) TOTAL_SALES
# FROM FOOD_PRODUCT fp
# JOIN (
#     SELECT PRODUCT_ID, SUM(AMOUNT) TOTAL_AMOUNT
#     FROM FOOD_ORDER
#     WHERE date_format(IN_DATE, '%Y-%m') = '2022-05'
#     GROUP BY PRODUCT_ID
# ) fo
# USING (PRODUCT_ID)
# ORDER BY TOTAL_SALES desc, PRODUCT_ID;

SELECT 
    fp.PRODUCT_ID, 
    fp.PRODUCT_NAME, 
    SUM(fp.PRICE * fo.AMOUNT) AS TOTAL_SALES
FROM 
    FOOD_PRODUCT fp
JOIN 
    FOOD_ORDER fo ON fp.PRODUCT_ID = fo.PRODUCT_ID
WHERE 
    fo.PRODUCE_DATE >= '2022-05-01' AND fo.PRODUCE_DATE < '2022-06-01'
GROUP BY 
    fp.PRODUCT_ID, fp.PRODUCT_NAME
ORDER BY 
    TOTAL_SALES DESC, 
    fp.PRODUCT_ID ASC;