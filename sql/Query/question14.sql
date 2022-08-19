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

--??u ti�n ch?n nh?ng th c� CUST_GRP_HRCHY_CD = G
SELECT cust_grp_id, cust_grp_hrchy_cd
FROM mdm_customer
WHERE cust_grp_hrchy_cd LIKE 'G';
--sau ?� m?i ch?n nh?ng th kh�ng c� trong CUST_GRP_HRCHY_CD = G
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

--??u ti�n t�m nh?ng c�i c� CUST_GRP_HRCHY_CD = 'C'
SELECT cust_grp_id
FROM mdm_customer
WHERE cust_grp_hrchy_cd LIKE 'C';
--t�m nh?ng th c� CUST_GRP_HRCHY_CD kh�ng c� trong c�i tr??c
SELECT cust_grp_id, cust_grp_hrchy_cd
FROM mdm_customer
WHERE cust_grp_id NOT IN (
    SELECT cust_grp_id
    FROM mdm_customer
    WHERE cust_grp_hrchy_cd LIKE 'C'
)
GROUP BY cust_grp_id, cust_grp_hrchy_cd;

