SELECT DATETIME AS '시간'

FROM ANIMAL_INS

WHERE DATETIME IN (

    SELECT MIN(DATETIME)
    
    FROM ANIMAL_INS
);