-- 2a test
SET @new_id = -1;
CALL insert_into_mine_sight("Ukraine", "LVIV", "COOL_TITLE()VERY))", @new_id);
select @new_id;
select * from mine_sight;


-- 2b test
CALL insert_into_company_mine_sight_m_to_m("", "");
INSERT INTO company(title) VALUES("ENVYYYY");
INSERT INTO mine_sight(country, city, title) VALUES ("Ukraine", "Dnipro", "DUk");
call insert_into_company_mine_sight_m_to_m("ENVYYYY", "DUk");
select * from company_mine_sight;


-- 2c test
CALL insert_10_records_into_company_table();
SELECT * FROM company;


-- 2d test
SET @sum_result = -1;
CALL companies_ids_sum(@sum_result);
SELECT @sum_result;


-- 2ei test
CALL create_2_tables_and_insert_data_dinamically();
SHOW TABLES;

select * from dynamic_2022_11_06_17_57_36_number1;
select * from dynamic_2022_11_06_17_57_36_number2;