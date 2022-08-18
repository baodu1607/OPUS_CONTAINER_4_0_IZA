
select cust_seq from mdm_customer;

select to_char(sysdate, 'YYYYMMDD') || to_char(count(*) +1, 'fm0000') as seq
From tb_ord
Where ord_dttm like to_char(sysdate, 'YYYYMMDD') || '%';

--14a
SELECT cust_grp_id FROM (
	SELECT cust_grp_id, cust_grp_hrchy_cd
	FROM mdm_customer
	WHERE cust_grp_id NOT IN (
		SELECT cust_grp_id
		FROM mdm_customer
		WHERE cust_grp_hrchy_cd LIKE 'G'
	)
	GROUP BY cust_grp_id, cust_grp_hrchy_cd
)
GROUP BY cust_grp_id
HAVING COUNT(cust_grp_id) = 1;

--??u tiên ch?n nh?ng th có CUST_GRP_HRCHY_CD = G
SELECT cust_grp_id, cust_grp_hrchy_cd
FROM mdm_customer
WHERE cust_grp_hrchy_cd LIKE 'G';
--sau ?ó m?i ch?n nh?ng th không có trong CUST_GRP_HRCHY_CD = G
SELECT cust_grp_id, cust_grp_hrchy_cd
FROM mdm_customer
WHERE cust_grp_id NOT IN (
    SELECT cust_grp_id
    FROM mdm_customer
    WHERE cust_grp_hrchy_cd LIKE 'G'
)
GROUP BY cust_grp_id, cust_grp_hrchy_cd;
--sau ?ó thì c? l?y ra thôi
SELECT cust_grp_id
FROM (
    SELECT cust_grp_id, cust_grp_hrchy_cd
    FROM mdm_customer
    WHERE cust_grp_id NOT IN (
        SELECT cust_grp_id
        FROM mdm_customer
        WHERE cust_grp_hrchy_cd LIKE 'G'
    )
    GROUP BY cust_grp_id, cust_grp_hrchy_cd
) GROUP BY cust_grp_id;

--14b
SELECT cust_grp_id
FROM (
    SELECT cust_grp_id, cust_grp_hrchy_cd
    FROM mdm_customer
    WHERE cust_grp_id NOT IN (
        SELECT cust_grp_id
        FROM mdm_customer
        WHERE cust_grp_hrchy_cd LIKE 'C'
    )
    GROUP BY cust_grp_id, cust_grp_hrchy_cd
) GROUP BY cust_grp_id;

--??u tiên tìm nh?ng cái có CUST_GRP_HRCHY_CD = 'C'
SELECT cust_grp_id
FROM mdm_customer
WHERE cust_grp_hrchy_cd LIKE 'C';
--tìm nh?ng th có CUST_GRP_HRCHY_CD không có trong cái tr??c
SELECT cust_grp_id, cust_grp_hrchy_cd
FROM mdm_customer
WHERE cust_grp_id NOT IN (
    SELECT cust_grp_id
    FROM mdm_customer
    WHERE cust_grp_hrchy_cd LIKE 'C'
)
GROUP BY cust_grp_id, cust_grp_hrchy_cd;

--15d
SELECT prod_unit_amt FROM tb_prod;

SELECT MIN(prod_nm)
    KEEP(DENSE_RANK LAST ORDER BY prod_unit_amt) AS max_name 
FROM tb_prod
WHERE prod_unit_amt IS NOT NULL;

SELECT MAX(prod_nm) KEEP (DENSE_RANK FIRST ORDER BY prod_unit_amt DESC NULLS LAST) as max_name
FROM tb_prod
WHERE prod_unit_amt IS NOT NULL;

SELECT prod_unit_amt FROM tb_prod ORDER BY prod_unit_amt DESC NULLS LAST;

--16a
SELECT * FROM (
    SELECT pro_cd, 
       DENSE_RANK()
       OVER(
            ORDER BY COUNT(*) DESC
            ) as rank
    FROM tb_ord
    GROUP BY pro_cd ) temp
WHERE temp.rank <=3;

-- sx, x?p h?ng
SELECT pro_cd, 
       DENSE_RANK()
       OVER(
            ORDER BY COUNT(*) DESC
       )as rank
FROM tb_ord
GROUP BY pro_cd;

--16
