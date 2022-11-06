USE bohdan_boretskyi_smartcap;

# one to many constraint using triggers
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


