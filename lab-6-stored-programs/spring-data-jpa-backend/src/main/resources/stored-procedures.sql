-- 2a parametrized insertion into mine_sight table
DROP PROCEDURE IF EXISTS insert_into_mine_sight;
DELIMITER //
CREATE PROCEDURE insert_into_mine_sight(
	IN country_p varchar(50),
    IN city_p varchar(100),
    IN title_p varchar(100),
    OUT id_p INT)
BEGIN
	INSERT INTO mine_sight(country, city, title) VALUES
    (country_p, city_p, title_p);
    SELECT id INTO id_p FROM mine_sight WHERE title=title_p;
END //
DELIMITER ;


-- 2b insert into m-to-m table by infering IDs from other real values;
DROP PROCEDURE IF EXISTS insert_into_company_mine_sight_m_to_m;
DELIMITER // 
CREATE PROCEDURE insert_into_company_mine_sight_m_to_m(
	IN company_title VARCHAR(50),
    IN mine_sight_title VARCHAR(50)
)
BEGIN
	DECLARE company_id_val INT;
    DECLARE mine_sight_id_val INT;
    SELECT id INTO company_id_val FROM company WHERE title = company_title LIMIT 1;
    SELECT id INTO mine_sight_id_val FROM mine_sight WHERE title = mine_sight_title LIMIT 1;
    IF company_id_val is NULL or mine_sight_id_val is NULL THEN
		SIGNAL SQLSTATE '22000'
			SET MESSAGE_TEXT = 'company or mine_sight record not found';
    END IF;
    INSERT INTO company_mine_sight (company_id, mine_sight_id) VALUES
    (company_id_val, mine_sight_id_val);
END //
DELIMITER ;


-- 2c insert 10 records into company table
DROP PROCEDURE IF EXISTS insert_10_records_into_company_table;
DELIMITER //
CREATE PROCEDURE insert_10_records_into_company_table()
BEGIN
	DECLARE i INT unsigned DEFAULT 0;
    START transaction;
	WHILE i < 10 DO
		INSERT INTO company(title) VALUE (concat("COMPANY", i));
        SET i = i + 1;
    END WHILE;
    COMMIT;
END //
DELIMITER ;


-- 2d custom function (product) and stored procedure that uses this function
DROP FUNCTION IF EXISTS ids_sum;
DELIMITER //
CREATE FUNCTION ids_sum()
RETURNS INT DETERMINISTIC
BEGIN
RETURN (SELECT sum(id) FROM company);
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS companies_ids_sum;
DELIMITER //
CREATE PROCEDURE companies_ids_sum(OUT result_sum INT)
BEGIN
	SELECT ids_sum() INTO result_sum;
END //
DELIMITER ;


-- 2ei stored procedure with cursor
DROP PROCEDURE IF EXISTS create_2_tables_and_insert_data_dinamically;
DELIMITER //
CREATE PROCEDURE create_2_tables_and_insert_data_dinamically()
BEGIN
	DECLARE table_name_1 VARCHAR(255);   
    DECLARE table_name_2 VARCHAR(255);
    DECLARE table_schema VARCHAR(255) DEFAULT '(
    id int NOT NULL AUTO_INCREMENT,
    title varchar(50) NOT NULL,
    CONSTRAINT company_pk PRIMARY KEY (id)
	);';
	DECLARE end_of_tables INT DEFAULT 0;       
    DECLARE comp_name_memorized VARCHAR(255);

	DECLARE cur CURSOR FOR                                                                                                                                                  
		SELECT title from  company;                                                                                              
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET end_of_tables = 1;                                                                                                           
	
	SET @tmstmp_prepared = REGEXP_REPLACE(current_timestamp(), "[-: ]", "_");
	SET table_name_1 = CONCAT('dynamic_', @tmstmp_prepared, '_number1');
	SET @s = CONCAT('CREATE TABLE ', table_name_1, table_schema);                                                                               
	PREPARE stmt FROM @s;                                                                                                                                               
	EXECUTE stmt;        
    
	SET @tmstmp_prepared = REGEXP_REPLACE(current_timestamp(), "[-: ]", "_");
	SET table_name_2 = CONCAT('dynamic_', @tmstmp_prepared, '_number2');
	SET @s = CONCAT('CREATE TABLE ', table_name_2, table_schema);                                                                               
	PREPARE stmt FROM @s;                                                                                                                                               
	EXECUTE stmt;      
    
	OPEN cur;                                                                                                                                                               
	tables_loop: LOOP                                                                                                                                                       
		FETCH cur INTO comp_name_memorized;                                                                                                                                          

		IF end_of_tables = 1 THEN                                                                                                                                           
			LEAVE tables_loop;                                                                                                                                              
		END IF;                                                                                                                                                             
		
        SET @rand_num_1_or_0 = FLOOR(RAND()*10)%2;
        
        IF @rand_num_1_or_0 = 0 THEN
			SET @s = CONCAT('INSERT INTO ', table_name_1, '(title) VALUES (''' , comp_name_memorized, ''')');                                                                               
			PREPARE stmt FROM @s;                                                                                                                                               
			EXECUTE stmt;     
        END IF;
        
        IF @rand_num_1_or_0 = 1 THEN
			SET @s = CONCAT('INSERT INTO ', table_name_2, '(title) VALUES (''' , comp_name_memorized, ''')');                                                                               
			PREPARE stmt FROM @s;                                                                                                                                               
			EXECUTE stmt;     
        END IF;
	END LOOP;                                                                                                                                                               

	CLOSE cur;       
END //
DELIMITER ;