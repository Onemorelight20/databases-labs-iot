drop schema if exists `bohdan_boretskyi_smartcap`;
create schema `bohdan_boretskyi_smartcap`;
use bohdan_boretskyi_smartcap;

drop table if exists company_mine_sight;
drop table if exists fatigue_monitoring;
drop table if exists medical_info;
drop table if exists driver;
drop table if exists company;
drop table if exists fatigue_level;
drop table if exists mine_sight;
drop table if exists vehicle;
drop table if exists vehicle_type;

CREATE TABLE company (
    id int NOT NULL AUTO_INCREMENT,
    title varchar(50) NOT NULL,
    CONSTRAINT company_pk PRIMARY KEY (id)
);

-- Table: company_mine_sight
CREATE TABLE company_mine_sight (
    company_id int NOT NULL,
    mine_sight_id int NOT NULL,
    CONSTRAINT company_mine_sight_pk PRIMARY KEY (company_id,mine_sight_id)
);

-- Table: driver
CREATE TABLE driver (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    surname varchar(50) NOT NULL,
    company_id int NOT NULL,
    phone_number varchar(40) NOT NULL,
    CONSTRAINT id PRIMARY KEY (id)
);

-- Table: fatigue_level
CREATE TABLE fatigue_level (
    level_title varchar(40) NOT NULL,
    CONSTRAINT fatigue_level_pk PRIMARY KEY (level_title)
);

-- Table: fatigue_monitoring
CREATE TABLE fatigue_monitoring (
    id int NOT NULL AUTO_INCREMENT,
    driver_id int NOT NULL,
    vehicle_id int NOT NULL,
    mine_sight_id int NOT NULL,
    fatigue_level_title varchar(40) NOT NULL,
    record_time timestamp NOT NULL,
    CONSTRAINT fatigue_monitoring_pk PRIMARY KEY (id)
);

-- Table: medical_info
CREATE TABLE medical_info (
    id int NOT NULL AUTO_INCREMENT,
    driver_id int NOT NULL,
    sight_state varchar(50) NOT NULL,
    blood_type varchar(50) NOT NULL,
    updated_at date NOT NULL,
    CONSTRAINT medical_info_pk PRIMARY KEY (id)
);

-- Table: mine_sight
CREATE TABLE mine_sight (
    id int NOT NULL AUTO_INCREMENT,
    country varchar(50) NOT NULL,
    city varchar(100) NOT NULL,
    title varchar(100) NOT NULL,
    CONSTRAINT mine_sight_pk PRIMARY KEY (id)
);


-- Table: vehicle
CREATE TABLE vehicle (
    id int NOT NULL AUTO_INCREMENT,
    brand varchar(50) NOT NULL,
    model varchar(50) NOT NULL,
    manufacturing_date date NOT NULL,
    serial_number varchar(50) NOT NULL,
    vehicle_type_id int NOT NULL,
    CONSTRAINT vehicle_pk PRIMARY KEY (id)
);

-- Table: vehicle_type
CREATE TABLE vehicle_type (
    id int NOT NULL AUTO_INCREMENT,
    type varchar(50) NOT NULL,
    CONSTRAINT vehicle_type_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: company_mine_sight_company (table: company_mine_sight)
ALTER TABLE company_mine_sight ADD CONSTRAINT company_mine_sight_company FOREIGN KEY company_mine_sight_company (company_id)
    REFERENCES company (id);

-- Reference: company_mine_sight_mine_sight (table: company_mine_sight)
ALTER TABLE company_mine_sight ADD CONSTRAINT company_mine_sight_mine_sight FOREIGN KEY company_mine_sight_mine_sight (mine_sight_id)
    REFERENCES mine_sight (id);

-- Reference: driver_company (table: driver)
ALTER TABLE driver ADD CONSTRAINT driver_company FOREIGN KEY driver_company (company_id)
    REFERENCES company (id);

-- Reference: fatigue_monitoring_driver (table: fatigue_monitoring)
ALTER TABLE fatigue_monitoring ADD CONSTRAINT fatigue_monitoring_driver FOREIGN KEY fatigue_monitoring_driver (driver_id)
    REFERENCES driver (id);

-- Reference: fatigue_monitoring_driver (table: fatigue_monitoring)
ALTER TABLE fatigue_monitoring ADD CONSTRAINT fatigue_monitoring_vehicle FOREIGN KEY fatigue_monitoring_vehicle (vehicle_id)
    REFERENCES vehicle (id);

-- Reference: fatigue_monitoring_fatigue_level (table: fatigue_monitoring)
ALTER TABLE fatigue_monitoring ADD CONSTRAINT fatigue_monitoring_fatigue_level FOREIGN KEY fatigue_monitoring_fatigue_level (fatigue_level_title)
    REFERENCES fatigue_level (level_title);

-- Reference: fatigue_monitoring_mine_sight (table: fatigue_monitoring)
ALTER TABLE fatigue_monitoring ADD CONSTRAINT fatigue_monitoring_mine_sight FOREIGN KEY fatigue_monitoring_mine_sight (mine_sight_id)
    REFERENCES mine_sight (id);

-- Reference: medical_info_driver (table: medical_info)
ALTER TABLE medical_info ADD CONSTRAINT medical_info_driver FOREIGN KEY medical_info_driver (driver_id)
    REFERENCES driver (id);


-- Insert some data into tables
use bohdan_boretskyi_smartcap;

-- Fill in the vehicle_type table
insert into vehicle_type (type) value ('truck');
insert into vehicle_type (type) value ('excavator');
insert into vehicle_type (type) value ('dozer');
insert into vehicle_type (type) value ('grader');
insert into vehicle_type (type) value ('water truck');

-- Fill in the vehicle table
insert into vehicle (brand, model, manufacturing_date, serial_number, vehicle_type_id) values
    ('Haul', '2422', '2000-12-30', 'FEOFEK34FW2424', (select id from vehicle_type where type='truck' limit 1)),
    ('ALMA', 'Transporter', '2010-10-22', 'AOOFEL34FW2424', (select id from vehicle_type where type='truck' limit 1)),
    ('BelAZ', '75710', '2013-03-21', 'BEOFEK34FW2424', (select id from vehicle_type where type='truck' limit 1)),
    ('Komatsu', 'PC8000-6', '2008-10-02', 'AOOFDL34FW2424', (select id from vehicle_type where type='excavator' limit 1)),
    ('Demag', 'H740 OS', '2019-04-30', 'EXCFEK34FW2424', (select id from vehicle_type where type='excavator' limit 1)),
    ('Liebherr', 'R9800', '2005-08-12', 'AOOFEL34FW2333', (select id from vehicle_type where type='excavator' limit 1)),
    ('Bucyrus', 'RH400', '2022-01-02', 'BEOCEK34FW2424', (select id from vehicle_type where type='excavator' limit 1)),
    ('Galion', 'T-700', '1955-10-02', 'GRTFDL34FW2424', (select id from vehicle_type where type='grader' limit 1)),
    ('The Cat', 'No. 16', '1963-04-19', 'LIOFEL34FW2333', (select id from vehicle_type where type='dozer' limit 1)),
    ('O&K', 'G-350', '2000-11-11', 'BEOCEK33FW2424', (select id from vehicle_type where type='dozer' limit 1)),
    ('The Cat', '24H', '2022-10-02', 'XRTFDL34FW2424', (select id from vehicle_type where type='dozer' limit 1)),
    ('Kenworth', 'T890', '2015-03-03', 'AAAFEK34FW2424', (select id from vehicle_type where type='water truck' limit 1)),
    ('Mack', 'P-6', '2000-03-12', 'AOOFDL34F00000', (select id from vehicle_type where type='water truck' limit 1)),
    ('Peterbilt', 'HOSPOS', '2022-02-10', 'TESFEK34FW2PET', (select id from vehicle_type where type='water truck' limit 1)),
    ('Kenworth', 'T300', '2001-01-12', 'T300EL34FW2333', (select id from vehicle_type where type='dozer' limit 1)),
    ('DAEWOO', 'LANOS', '2007-12-12', 'BEOAZAZAZW2424', (select id from vehicle_type where type='grader' limit 1));

-- Fill in the fatigue_level table
insert into fatigue_level (level_title) values
    ('Level 1'),
    ('Level 2'),
    ('Level 3'),
    ('Level 3+'),
    ('Level 4+'),
    ('Level 5');

-- Fill in the mine_sight table
insert into mine_sight (country, city, title) values
    ('Great Britain', 'Norfolk', 'Grimes Graves'),
    ('Spain', 'South', 'Las Médulas'),
    ('Germany', 'Goslar', 'Mines of Rammelsberg'),
    ('Sweden', 'Dalarna', 'Great Pit at Falun'),
    ('USA', 'Bloomfield', 'Malakoff Diggins'),
    ('USA', 'Minnesota', 'Hull-Rust-Mahoning Mine'),
    ('USA', 'Chicago', 'Bingham Canyon Mine'),
    ('USA', 'Nevada', 'Goldstrike Mine'),
    ('Ukraine', 'Lviv', 'Montains'),
    ('Poland', 'Warszawa', 'Bobrin Pit'),
    ('France', 'Lion', 'Brawl Startsing');

-- Fill in the company table
insert into company (title) values
    ('Glencore'),
    ('BHP'),
    ('Rio Tinto'),
    ('China Shenhua Energy'),
    ('Vale'),
    ('Anglo American plc'),
    ('Zijin Mining'),
    ('Coal India'),
    ('Coal Lviv Great'),
    ('Boroland'),
    ('Cluch Coal');

-- Fill in the company_mine_sight table
insert into company_mine_sight (company_id, mine_sight_id) values
    ((select id from company where title='Glencore'), (select id from mine_sight where title='Grimes Graves')),
    ((select id from company where title='Glencore'), (select id from mine_sight where title='Goldstrike Mine')),
    ((select id from company where title='BHP'), (select id from mine_sight where title='Las Médulas')),
    ((select id from company where title='Rio Tinto'), (select id from mine_sight where title='Mines of Rammelsberg')),
    ((select id from company where title='China Shenhua Energy'), (select id from mine_sight where title='Great Pit at Falun')),
    ((select id from company where title='Vale'), (select id from mine_sight where title='Malakoff Diggins')),
    ((select id from company where title='Anglo American plc'), (select id from mine_sight where title='Hull-Rust-Mahoning Mine')),
    ((select id from company where title='Zijin Mining'), (select id from mine_sight where title='Goldstrike Mine')),
    ((select id from company where title='Coal India'), (select id from mine_sight where title='Bingham Canyon Mine')),
    ((select id from company where title='Coal Lviv Great'), (select id from mine_sight where title='Montains')),
    ((select id from company where title='Boroland'), (select id from mine_sight where title='Bobrin Pit')),
    ((select id from company where title='Cluch Coal'), (select id from mine_sight where title='Goldstrike Mine'));

-- Fill in the driver table
insert into driver (name, surname, company_id, phone_number) values
    ('Sherry', 'Vega', (select id from company where title='Glencore'), '205-699-0780'),
    ('Nancy', 'Alongi', (select id from company where title='Coal India'), '281-721-3145'),
    ('Paul', 'Struble', (select id from company where title='Zijin Mining'), '715-829-3484'),
    ('Gary', 'Kavanaugh', (select id from company where title='Rio Tinto'), '907-383-4156'),
    ('George', 'Noble', (select id from company where title='China Shenhua Energy'), '214-446-8679'),
    ('Jo', 'Buddrell', (select id from company where title='Anglo American plc'), '518-605-0682'),
    ('Doris', 'Matos', (select id from company where title='Vale'), '213-367-7665'),
    ('Nelly', 'Wiseman', (select id from company where title='BHP'), '218-364-0840'),
    ('Troy', 'Pickard', (select id from company where title='Rio Tinto'), '423-896-8310'),
    ('Margaret', 'Jones', (select id from company where title='China Shenhua Energy'), '224-993-6981'),
    ('Randy', 'Williams', (select id from company where title='China Shenhua Energy'), '919-225-1787'),
    ('Jason', 'Clasher', (select id from company where title='Anglo American plc'), '623-572-9586'),
    ('Marks', 'Iloon', (select id from company where title='Coal India'), '333-005-0682'),
    ('Marian', 'Sheeran', (select id from company where title='Boroland'), '111-307-7665'),
    ('Natalia', 'Borovyk', (select id from company where title='Coal Lviv Great'), '218-364-0000');

-- Fill in the medical_info table
insert into medical_info (driver_id, sight_state, blood_type, updated_at) values
    ((select id from driver where phone_number='205-699-0780'), '100', '1+', curdate()),
    ((select id from driver where phone_number='281-721-3145'), '90', '4+', curdate()),
    ((select id from driver where phone_number='715-829-3484'), '100', '2+', curdate()),
    ((select id from driver where phone_number='907-383-4156'), '97', '3-', curdate()),
    ((select id from driver where phone_number='214-446-8679'), '69', '1+', curdate()),
    ((select id from driver where phone_number='518-605-0682'), '100', '4+', curdate()),
    ((select id from driver where phone_number='213-367-7665'), '100', '2+', curdate()),
    ((select id from driver where phone_number='218-364-0840'), '80', '3-', curdate()),
    ((select id from driver where phone_number='423-896-8310'), '99', '1+', curdate()),
    ((select id from driver where phone_number='224-993-6981'), '90', '4+', curdate()),
    ((select id from driver where phone_number='919-225-1787'), '78', '2+', curdate()),
    ((select id from driver where phone_number='623-572-9586'), '100', '3-', curdate()),
    ((select id from driver where phone_number='333-005-0682'), '95', '4+', curdate()),
    ((select id from driver where phone_number='111-307-7665'), '99', '2+', curdate()),
    ((select id from driver where phone_number='218-364-0000'), '98', '3-', curdate());


-- Fill in the fatigue_monitoring table
insert into fatigue_monitoring (driver_id, vehicle_id, mine_sight_id, fatigue_level_title, record_time) values
(
    (select id from driver where phone_number='281-721-3145'),
    (select id from vehicle where serial_number='FEOFEK34FW2424'),
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='281-721-3145') limit 1),
    'Level 2',
    CURRENT_TIMESTAMP()
),
(
    (select id from driver where phone_number='205-699-0780'),
    (select id from vehicle where serial_number='AOOFEL34FW2424'),
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='205-699-0780') limit 1),
    'Level 2',
    CURRENT_TIMESTAMP()
),
(
    (select id from driver where phone_number='715-829-3484'),
    (select id from vehicle where serial_number='AOOFDL34FW2424'),
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='715-829-3484') limit 1),
    'Level 2',
    CURRENT_TIMESTAMP()
),
(
    (select id from driver where phone_number='907-383-4156'),
    (select id from vehicle where serial_number='BEOAZAZAZW2424'),
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='907-383-4156') limit 1),
    'Level 3',
    CURRENT_TIMESTAMP()
),
(
    (select id from driver where phone_number='214-446-8679'),
    (select id from vehicle where serial_number='T300EL34FW2333'),
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='214-446-8679') limit 1),
    'Level 3',
    CURRENT_TIMESTAMP()
),
(
    (select id from driver where phone_number='213-367-7665'),
    (select id from vehicle where serial_number='BEOCEK34FW2424'),
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='213-367-7665') limit 1),
    'Level 3',
    CURRENT_TIMESTAMP()
),
(
    (select id from driver where phone_number='218-364-0840'),
    (select id from vehicle where serial_number='GRTFDL34FW2424'),
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='218-364-0840') limit 1),
    'Level 3',
    CURRENT_TIMESTAMP()
),
(
    (select id from driver where phone_number='423-896-8310'),
    (select id from vehicle where serial_number='LIOFEL34FW2333'),
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='423-896-8310') limit 1),
    'Level 3',
    CURRENT_TIMESTAMP()
),
(
    (select id from driver where phone_number='224-993-6981'),
    (select id from vehicle where serial_number='XRTFDL34FW2424'),
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='224-993-6981') limit 1),
    'Level 3',
    CURRENT_TIMESTAMP()
),
(
    (select id from driver where phone_number='623-572-9586'),
    (select id from vehicle where serial_number='TESFEK34FW2PET'),
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='623-572-9586') limit 1),
    'Level 3',
    CURRENT_TIMESTAMP()
);

-- ADD INDEXES
ALTER TABLE fatigue_monitoring ADD INDEX (driver_id);
ALTER TABLE driver ADD INDEX (phone_number);


-- ONE TO MANY CONSTRAINT TRIGGERS
DROP TRIGGER IF EXISTS before_insert_vehicle_1tm_check;
DROP TRIGGER IF EXISTS before_update_vehicle_1tm_check;
DROP TRIGGER IF EXISTS before_update_vehicle_type_1tm_check;
DROP TRIGGER IF EXISTS before_delete_vehicle_type_1tm_check;

DELIMITER //
CREATE TRIGGER before_insert_vehicle_1tm_check
	BEFORE INSERT ON vehicle FOR EACH ROW
BEGIN
	DECLARE id_match INT;
    select count(*) into id_match from vehicle_type where id = NEW.vehicle_type_id;
    IF id_match = 0 THEN
		SIGNAL SQLSTATE '22000'
		SET MESSAGE_TEXT = 'A foreign key constraint fails for vehicle_type_id';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER before_update_vehicle_1tm_check
	BEFORE UPDATE ON vehicle FOR EACH ROW
BEGIN
	DECLARE id_match INT;
    IF OLD.vehicle_type_id <> NEW.vehicle_type_id THEN
		select count(*) into id_match from vehicle_type where id = NEW.vehicle_type_id;
		IF id_match = 0 THEN
			SIGNAL SQLSTATE '22000'
			SET MESSAGE_TEXT = 'A foreign key constraint fails for vehicle_type_id';
		END IF;
	END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER before_update_vehicle_type_1tm_check
	BEFORE UPDATE ON vehicle_type FOR EACH ROW
BEGIN
	DECLARE is_used_count INT;
    IF OLD.id <> NEW.id THEN
		select count(*) into is_used_count from vehicle where vehicle_type_id = OLD.id;
		IF is_used_count > 0 THEN
			SIGNAL SQLSTATE '22000'
			SET MESSAGE_TEXT = 'A foreign key constraint fails for vehicle_type_id';
		END IF;
	END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER before_delete_vehicle_type_1tm_check
	BEFORE DELETE ON vehicle_type FOR EACH ROW
BEGIN
	DECLARE is_used_count INT;
	select count(*) into is_used_count from vehicle where vehicle_type_id = OLD.id;
	IF is_used_count > 0 THEN
		SIGNAL SQLSTATE '22000'
		SET MESSAGE_TEXT = 'A foreign key constraint fails for vehicle_type_id in vehicle table';
	END IF;
END //
DELIMITER ;





-- STORED PROCEDURES

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
