Select to_char(sysdate, 'YYYYMMDD') || nvl(lpad(substr(max(ord_dttm), 9, 4)+1,4),'0001') as ord_dttm
From tb_ord
Where ord_dttm like to_char(sysdate, 'YYYYMMDD') || '%';

SELECT
    MAX(prod_unit_amt) max_amt,
    MAX(prod_unit_amt) min_amt,
    AVG(prod_unit_amt) avg_amt,
    MIN(prod_nm)
    KEEP(DENSE_RANK LAST ORDER BY prod_unit_amt) AS max_name 
FROM tb_prod
WHERE prod_unit_amt IS NOT NULL;

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

SELECT cust_no,
       ord_dttm,
       ord_no,
       pro_cd,
       ROW_NUMBER()
       OVER(PARTITION BY cust_no ORDER BY ord_dttm DESC) rank
FROM tb_ord;

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
    

select cust_grp_id,
       count(*) 
       over(partition by cust_grp_id) volumn,
       cust_grp_hrchy_cd
from mdm_customer
group by cust_grp_id, cust_grp_hrchy_cd;

select cust_grp_id
from (
    select cust_grp_id,
       count(*) 
       over(partition by cust_grp_id) volumn,
       cust_grp_hrchy_cd
    from mdm_customer
    group by cust_grp_id, cust_grp_hrchy_cd
)
where cust_grp_hrchy_cd != 'G';

with temp as (
    select cust_grp_id,
            count(*)
            over(partition by cust_grp_id) volumn,
            cust_grp_hrchy_cd
    from mdm_customer
    group by cust_grp_id,
            cust_grp_hrchy_cd
    order by cust_grp_id,
            cust_grp_hrchy_cd
)
select cust_grp_id
from (
    select cust_grp_id,
            cust_grp_hrchy_cd,
            lead (cust_grp_id)
            over(
                order by cust_grp_id
            ) next_cust_grp_id,
            lead(cust_grp_hrchy_cd)
            over( order by cust_grp_id) next_cust_grp_hrchy_cd
    from
        temp
    where volumn = 2
)
where cust_grp_id = next_cust_grp_id and cust_grp_hrchy_cd != 'C'
        and next_cust_grp_id != 'C';


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



