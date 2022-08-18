--Câu 6
SELECT * FROM tb_prod;

select * from mdm_customer;

select to_number(prod_cd) from tb_prod;

--c1
SELECT NVL(SUM(prod_unit_amt),0) + NVL(SUM(prod_unit_amt),0) 
FROM tb_prod;
--ex1
SELECT SUM(NVL(TO_NUMBER(prod_cd) + prod_unit_amt,0))
FROM tb_prod;
--ex2
SELECT NVL(SUM(TO_NUMBER(prod_cd) + prod_unit_amt),0) 
FROM tb_prod;

--Câu 7
SELECT A.CUST_NO, A.ORD_NO, A.PRO_CD, B.PROD_NM
FROM TB_ORD A,
  TB_PROD B
WHERE 1 = 1
  AND A.PRO_CD = B.PROD_CD
  AND B.PROD_CD IN (SELECT PROD_CD FROM TB_PROD D WHERE D.PROD_CD = A.PRO_CD AND PROD_UNIT_AMT < 800);

SELECT A.CUST_NO, A.ORD_NO, A.PRO_CD, B.PROD_NM
FROM TB_ORD A,
  TB_PROD B
WHERE 1 = 1
  AND A.PRO_CD = B.PROD_CD
  AND EXISTS (SELECT D.PROD_CD FROM TB_PROD D WHERE D.PROD_CD = A.PRO_CD AND D.PROD_UNIT_AMT < 800);

--Câu 8
SELECT A.CUST_NO, A.ORD_NO, A.PRO_CD, B.PROD_NM
FROM TB_ORD A,
  TB_PROD B
WHERE 1 = 1
  AND A.PRO_CD = B.PROD_CD
  AND B.PROD_CD IN ('00001','00002');
  
SELECT  A.CUST_NO, A.ORD_NO, A.PRO_CD
  , (SELECT B.PROD_NM FROM TB_PROD B WHERE B.PROD_CD = A.PRO_CD) AS PROD_NM
FROM TB_ORD A
WHERE 1 = 1
  AND A.PRO_CD IN ('00001','00002');

--câu 15d:
SELECT MAX(prod_unit_amt)
FROM tb_prod;
    
SELECT MAX(PROD_NM)
KEEP( DENSE_RANK FIRST ORDER BY PROD_UNIT_AMT DESC NULLS LAST) AS
max_name
FROM tb_prod
WHERE prod_unit_amt IS NOT NULL;
    
select * from dual;

--16c:
select * from tb_ord;

SELECT '201906' AS DT FROM DUAL
UNION ALL
SELECT '201907' AS DT FROM DUAL
UNION ALL
SELECT '201908' AS DT FROM DUAL
UNION ALL
SELECT '201909' AS DT FROM DUAL;

SELECT pro_cd, substr(ord_dttm,1,6) AS ord_dttm, COUNT(*) AS total
FROM tb_ord
WHERE pro_cd = '00001'
GROUP BY pro_cd, substr(ord_dttm,1,6);

SELECT A.dt AS mon, NVL(B.pro_cd, '00001') AS pro_cd, NVL(B.total,
0)
FROM(
SELECT '201906' AS DT FROM DUAL
UNION ALL
SELECT '201907' AS DT FROM DUAL
UNION ALL
SELECT '201908' AS DT FROM DUAL
UNION ALL
SELECT '201909' AS DT FROM DUAL
) A LEFT JOIN (
SELECT pro_cd, substr(ord_dttm,1,6) AS ord_dttm, COUNT(*) AS
total
FROM tb_ord
WHERE pro_cd = '00001'
GROUP BY pro_cd, substr(ord_dttm,1,6)
) B ON A.dt = B.ord_dttm
ORDER BY mon;

--16a:





