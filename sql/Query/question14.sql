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
GROUP BY cust_grp_id;

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

