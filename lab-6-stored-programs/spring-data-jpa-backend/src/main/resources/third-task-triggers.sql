USE bohdan_boretskyi_smartcap;


-- 3a: значення певного стовпця не може закінчувати двома нулями
DROP TRIGGER IF EXISTS check_for_00_at_company_title_end_before_insert;
DELIMITER //
CREATE TRIGGER check_for_00_at_company_title_end_before_insert
	BEFORE INSERT ON company FOR EACH ROW
BEGIN
    IF RIGHT(NEW.title, 2) = '00' THEN
		SIGNAL SQLSTATE '22000'
		SET MESSAGE_TEXT = 'Title cannot end with ''00''';
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS check_for_00_at_company_title_end_before_update;
DELIMITER //
CREATE TRIGGER check_for_00_at_company_title_end_before_update
	BEFORE UPDATE ON company FOR EACH ROW
BEGIN
    IF RIGHT(NEW.title, 2) = '00' THEN
		SIGNAL SQLSTATE '22000'
		SET MESSAGE_TEXT = 'Title cannot end with ''00''';
    END IF;
END //
DELIMITER ;

-- test
-- INSERT INTO company(title) VALUES ("AOAOAO");


-- 3b: Заборонити будь-яку модифікацію даних в таблиці
DROP TRIGGER IF EXISTS restrict_modification_of_company_table;
DELIMITER //
CREATE TRIGGER restrict_modification_of_company_table
	BEFORE UPDATE ON company FOR EACH ROW
BEGIN
	SIGNAL SQLSTATE '22000'
	SET MESSAGE_TEXT = 'You can not update this table.';
END //
DELIMITER ;

-- test
-- UPDATE company SET title = '' WHERE id =2;

-- 3j Для стовпця name table driver допускається ввід лише таких імен: 'Svitlana', 'Petro', 'Olha', 'Taras'.
DROP TRIGGER IF EXISTS only_top_names_accepted_in_driver_insertion;
DELIMITER //
CREATE TRIGGER only_top_names_accepted_in_driver_insertion
	BEFORE insert ON driver FOR EACH ROW
BEGIN
    IF NEW.name NOT IN ('Svitlana', 'Petro', 'Olha', 'Taras') THEN
		SIGNAL SQLSTATE '22000'
		SET MESSAGE_TEXT = 'ONLY Svitlana, Petro, Olha, Taras can be drivers';
    END IF;
END //
DELIMITER ;

-- test
-- insert into driver (name, surname, company_id, phone_number) values ('Svitlana', 'Vega', (select id from company where title='Glencore'), '2018-699-0780');