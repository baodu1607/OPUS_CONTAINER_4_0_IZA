--Câu 13
Select to_char(sysdate, 'YYYYMMDD') || nvl(lpad(substr(max(ord_dttm), 9, 4)+1,4),'0001') as ord_dttm
From tb_ord
Where ord_dttm like to_char(sysdate, 'YYYYMMDD') || '%';

SELECT to_char(sysdate, 'YYYYMMDD') || nvl(lpad(substr(max(ord_dttm), 9, 4)+1,4,000),'0001') as ord_dttm
FROM tb_ord
WHERE ord_dttm LIKE to_char(sysdate, 'YYYYMMDD') || '%';

SELECT substr(max(ord_dttm), 9, 4)+1 FROM tb_ord;

SELECT lpad(substr(max(ord_dttm), 9, 4)+1,4, 000) FROM tb_ord;

SELECT max(ord_dttm) FROM tb_ord;
SELECT 0001 + 1 FROM dual;
--Câu 15
SELECT
    MAX(prod_unit_amt) max_amt,
    MIN(prod_unit_amt) min_amt,
    AVG(prod_unit_amt) avg_amt,
    MIN(prod_nm)
        KEEP(DENSE_RANK LAST ORDER BY prod_unit_amt) AS max_name 
FROM tb_prod
WHERE prod_unit_amt IS NOT NULL;

--Câu 16a 
SELECT pro_cd, 
       DENSE_RANK()
       OVER(
            ORDER BY COUNT(*) DESC
            ) as rank 
FROM tb_ord
GROUP BY pro_cd;

SELECT * FROM (
    SELECT pro_cd, 
       DENSE_RANK()
       OVER(
            ORDER BY COUNT(*) DESC
            ) as rank
    FROM tb_ord
    GROUP BY pro_cd ) temp
WHERE temp.rank <=3;

--câu 16b
SELECT cust_no, 
       ord_dttm,
       ord_no,
       pro_cd
FROM (
    SELECT cust_no,
           ord_dttm,
           ord_no,
           pro_cd,
           ROW_NUMBER()
           OVER(PARTITION BY cust_no ORDER BY ord_dttm DESC) rank
    FROM tb_ord
) temp
WHERE temp.rank = 1;

--Câu 16c
WITH report as (
    SELECT '201906' as dt from dual
    UNION ALL 
    SELECT '201907' as dt from dual
    UNION ALL
    SELECT '201908' as dt from dual
    UNION ALL
    SELECT '201909' as dt from dual
)
SELECT report.dt, 
       nvl(pro_cd, '00001') pro_cd,
       nvl(ord.total, 0) total
FROM report left
    JOIN (
        SELECT pro_cd,
               substr(ord_dttm, 1, 6) as ord_dttm,
               COUNT(*) total
        FROM tb_ord
        WHERE pro_cd = '00001'
        GROUP BY pro_cd, substr(ord_dttm, 1, 6)
    ) ord ON report.dt = ord.ord_dttm;
    
--Câu 16d
WITH report as(
    SELECT '201906' as dt from dual
    UNION ALL
    SELECT '201907' as dt from dual
    UNION ALL
    SELECT '201908' as dt from dual
    UNION ALL
    SELECT '201909' as dt from dual
) 
SELECT report.dt,
       nvl(ord.total, 0) total,
       100 - nvl(SUM(ord.total)
               OVER(PARTITION BY ord.pro_cd ORDER BY report.dt), 0) AS remain
FROM report
    left join(
        select pro_cd,
            substr(ord_dttm, 1, 6) as ord_dttm,
            count(*) total 
        from tb_ord
        where pro_cd = '00001'
        group by pro_cd,
                 substr(ord_dttm, 1, 6) 
    ) ord ON report.dt = ord.ord_dttm;
   




