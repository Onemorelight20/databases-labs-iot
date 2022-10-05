use bohdan_boretskyi_smartcap;

-- Fill in the vehicle_type table
insert into vehicle_type (type) value ('truck');
insert into vehicle_type (type) value ('excavator');
insert into vehicle_type (type) value ('dozer');
insert into vehicle_type (type) value ('grader');
insert into vehicle_type (type) value ('water truck');

-- Fill in the vehicle table
insert into vehicle (brand, model, manufacturing_date, licence_plate_number, serial_number, vehicle_type_id, weight_in_kilos) values 
 ('Haul', '2422', '2000-12-30', 'AME232', 'FEOFEK34FW2424', (select id from vehicle_type where type='truck' limit 1), 20000),
 ('ALMA', 'Transporter', '2010-10-22', 'AME212', 'AOOFEL34FW2424', (select id from vehicle_type where type='truck' limit 1), 25000),
 ('BelAZ', '75710', '2013-03-21', 'BEL244', 'BEOFEK34FW2424', (select id from vehicle_type where type='truck' limit 1), 360000),
 ('Komatsu', 'PC8000-6', '2008-10-02', 'BIG212', 'AOOFDL34FW2424', (select id from vehicle_type where type='excavator' limit 1), 710000),
 ('Demag', 'H740 OS', '2019-04-30', 'EXC009', 'EXCFEK34FW2424', (select id from vehicle_type where type='excavator' limit 1), 740000),
 ('Liebherr', 'R9800', '2005-08-12', 'LIE212', 'AOOFEL34FW2333', (select id from vehicle_type where type='excavator' limit 1), 840000),
 ('Bucyrus', 'RH400', '2022-01-02', 'BDL244', 'BEOCEK34FW2424', (select id from vehicle_type where type='excavator' limit 1), 909000),
 ('Galion', 'T-700', '1955-10-02', 'GRA212', 'GRTFDL34FW2424', (select id from vehicle_type where type='grader' limit 1), 710000),
 ('The Cat', 'No. 16', '1963-04-19', 'LIX012', 'LIOFEL34FW2333', (select id from vehicle_type where type='dozer' limit 1), 40000),
 ('O&K', 'G-350', '2000-11-11', 'AAL244', 'BEOCEK33FW2424', (select id from vehicle_type where type='dozer' limit 1), 40000),
 ('The Cat', '24H', '2022-10-02', 'NEW212', 'XRTFDL34FW2424', (select id from vehicle_type where type='dozer' limit 1), 62000),
 ('Kenworth', 'T890', '2015-03-03', 'BEBR44', 'AAAFEK34FW2424', (select id from vehicle_type where type='water truck' limit 1), 36300),
 ('Mack', 'P-6', '2000-03-12', 'MCKR33', 'AOOFDL34F00000', (select id from vehicle_type where type='water truck' limit 1), 710000),
 ('Peterbilt', 'HOSPOS', '2022-02-10', 'PET0S9', 'TESFEK34FW2PET', (select id from vehicle_type where type='water truck' limit 1), 740099),
 ('Kenworth', 'T300', '2001-01-12', 'KEN202', 'T300EL34FW2333', (select id from vehicle_type where type='dozer' limit 1), 84400),
 ('DAEWOO', 'LANOS', '2007-12-12', 'ZAZ244', 'BEOAZAZAZW2424', (select id from vehicle_type where type='grader' limit 1), 1300);
 
 
 -- Fill in the sensor table
insert into sensor (brand, model, date_installed, vehicle_id) values
('AirLink', 'MP70', '2019-12-28', (select id from vehicle where serial_number='FEOFEK34FW2424')), 
('AirLink', 'MP30', '2019-12-29', (select id from vehicle where serial_number='AOOFEL34FW2424')),
('GNSS', 'XA1110', '2020-09-08', (select id from vehicle where serial_number='BEOFEK34FW2424')), 
('GNSS', 'XM1210', '2020-09-12', (select id from vehicle where serial_number='AOOFDL34FW2424')),
('Sierra', 'FX30', '2019-01-20', (select id from vehicle where serial_number='EXCFEK34FW2424')), 
('EirLink', 'MPee30', '2019-12-01', (select id from vehicle where serial_number='AOOFEL34FW2333')),
('Apple', 'XFG11', '2021-09-13', (select id from vehicle where serial_number='BEOCEK34FW2424')), 
('Apple', 'MC44', '2021-09-12', (select id from vehicle where serial_number='GRTFDL34FW2424')),
('Apple', 'MC14', '2021-09-12', (select id from vehicle where serial_number='GRTFDL34FW2424')),
('AirLink', 'MP70', '2019-12-28', (select id from vehicle where serial_number='LIOFEL34FW2333')), 
('AirLink', 'MP30', '2019-12-29', (select id from vehicle where serial_number='BEOCEK33FW2424')),
('GQ', 'XAXA1110', '2020-09-08', (select id from vehicle where serial_number='AAAFEK34FW2424')),
('MK', 'FXD30', '2019-11-11', (select id from vehicle where serial_number='AOOFDL34F00000')), 
('PETR', 'MPee30', '2019-12-01', (select id from vehicle where serial_number='TESFEK34FW2PET')),
('GGL', 'XFG', '2021-05-13', (select id from vehicle where serial_number='T300EL34FW2333')), 
('GGL', 'MPXLC14', '2020-09-12', (select id from vehicle where serial_number='BEOAZAZAZW2424'));

-- Fill in the fatigue_level table
insert into fatigue_level (level_title) values 
('Level 1'),
('Level 2'),
('Level 3'),
('Level 3+'),
('Level 4+'),
('Level 5');

-- Fill in the doctor table
insert into doctor (name, surname, phone_number) values 
('Daniel', 'Rollin', '404-674-9826'),
('Sandra', 'Franklin', '248-779-9251'),
('Darrell', 'Jones', '845-246-8392'),
('Denise', 'Woorand', '309-253-8929'),
('Gregory', 'Barnes', '300-200-8929'),
('Petro', 'Mostavchuk', '301-111-1111'),
('Bear', 'Engl', '777-000-0808'),
('Check', 'Noris', '345-887-2222'),
('Denise', 'Woorand', '309-999-8929'),
('Mary', 'Bu', '201-821-9222'),
('Oleh', 'Peterson', '095-595-2422'),
('Denise', 'Blue', '908-704-6588');

-- Fill in the mine_sight table
insert into mine_sight (country, city, title, area_in_square_meeters) values
('Great Britain', 'Norfolk', 'Grimes Graves', 40000),
('Spain', 'South', 'Las Médulas', 130000),
('Germany', 'Goslar', 'Mines of Rammelsberg', 4900),
('Sweden', 'Dalarna', 'Great Pit at Falun', 70200),
('USA', 'Bloomfield', 'Malakoff Diggins', 893000),
('USA', 'Minnesota', 'Hull-Rust-Mahoning Mine', 77000),
('USA', 'Chicago', 'Bingham Canyon Mine', 40000),
('USA', 'Nevada', 'Goldstrike Mine', 320000),
('Ukraine', 'Lviv', 'Montains', 7001),
('Poland', 'Warszawa', 'Bobrin Pit', 90000),
('France', 'Lion', 'Brawl Startsing', 261000);

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
insert into medical_info (driver_id, sight_state, blood_type, doctor_id, updated_at) values
((select id from driver where phone_number='205-699-0780'), '100', '1+', (select id from doctor where phone_number='404-674-9826'), curdate()),
((select id from driver where phone_number='281-721-3145'), '90', '4+', (select id from doctor where phone_number='248-779-9251'), curdate()),
((select id from driver where phone_number='715-829-3484'), '100', '2+', (select id from doctor where phone_number='845-246-8392'), curdate()),
((select id from driver where phone_number='907-383-4156'), '97', '3-', (select id from doctor where phone_number='309-253-8929'), curdate()),
((select id from driver where phone_number='214-446-8679'), '69', '1+', (select id from doctor where phone_number='404-674-9826'), curdate()),
((select id from driver where phone_number='518-605-0682'), '100', '4+', (select id from doctor where phone_number='248-779-9251'), curdate()),
((select id from driver where phone_number='213-367-7665'), '100', '2+', (select id from doctor where phone_number='845-246-8392'), curdate()),
((select id from driver where phone_number='218-364-0840'), '80', '3-', (select id from doctor where phone_number='309-253-8929'), curdate()),
((select id from driver where phone_number='423-896-8310'), '99', '1+', (select id from doctor where phone_number='300-200-8929'), curdate()),
((select id from driver where phone_number='224-993-6981'), '90', '4+', (select id from doctor where phone_number='248-779-9251'), curdate()),
((select id from driver where phone_number='919-225-1787'), '78', '2+', (select id from doctor where phone_number='300-200-8929'), curdate()),
((select id from driver where phone_number='623-572-9586'), '100', '3-', (select id from doctor where phone_number='300-200-8929'), curdate()),
((select id from driver where phone_number='333-005-0682'), '95', '4+', (select id from doctor where phone_number='201-821-9222'), curdate()),
((select id from driver where phone_number='111-307-7665'), '99', '2+', (select id from doctor where phone_number='201-821-9222'), curdate()),
((select id from driver where phone_number='218-364-0000'), '98', '3-', (select id from doctor where phone_number='908-704-6588'), curdate());

-- Fill in the work_shift table
insert into work_shift (driver_id, vehicle_id, medical_info_id, mine_sight_id, begin_at) values 
(	
	(select id from driver where phone_number='281-721-3145'), 
	(select id from vehicle where serial_number='FEOFEK34FW2424'), 
	(select id from medical_info where driver_id = driver_id order by updated_at desc limit 1), 
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='281-721-3145') limit 1), 
    curdate()
),
(	
	(select id from driver where phone_number='205-699-0780'), 
	(select id from vehicle where serial_number='AOOFEL34FW2424'), 
	(select id from medical_info where driver_id = driver_id order by updated_at desc limit 1), 
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='205-699-0780') limit 1), 
    curdate()
),
(	
	(select id from driver where phone_number='715-829-3484'), 
	(select id from vehicle where serial_number='AOOFDL34FW2424'), 
	(select id from medical_info where driver_id = driver_id order by updated_at desc limit 1), 
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='715-829-3484') limit 1), 
    curdate()
),
(	
	(select id from driver where phone_number='907-383-4156'), 
	(select id from vehicle where serial_number='BEOAZAZAZW2424'), 
	(select id from medical_info where driver_id = driver_id order by updated_at desc limit 1), 
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='907-383-4156') limit 1), 
    curdate()
),
(	
	(select id from driver where phone_number='214-446-8679'), 
	(select id from vehicle where serial_number='T300EL34FW2333'), 
	(select id from medical_info where driver_id = driver_id order by updated_at desc limit 1), 
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='214-446-8679') limit 1), 
    curdate()
),
 (	
	(select id from driver where phone_number='213-367-7665'), 
	(select id from vehicle where serial_number='BEOCEK34FW2424'), 
	(select id from medical_info where driver_id = driver_id order by updated_at desc limit 1), 
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='213-367-7665') limit 1), 
    curdate()
),
(	
	(select id from driver where phone_number='218-364-0840'), 
	(select id from vehicle where serial_number='GRTFDL34FW2424'), 
	(select id from medical_info where driver_id = driver_id order by updated_at desc limit 1), 
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='218-364-0840') limit 1), 
    curdate()
),
(	
	(select id from driver where phone_number='423-896-8310'), 
	(select id from vehicle where serial_number='LIOFEL34FW2333'), 
	(select id from medical_info where driver_id = driver_id order by updated_at desc limit 1), 
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='423-896-8310') limit 1), 
    curdate()
),
(	
	(select id from driver where phone_number='224-993-6981'), 
	(select id from vehicle where serial_number='XRTFDL34FW2424'), 
	(select id from medical_info where driver_id = driver_id order by updated_at desc limit 1), 
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='224-993-6981') limit 1), 
    curdate()
),
(	
	(select id from driver where phone_number='623-572-9586'), 
	(select id from vehicle where serial_number='TESFEK34FW2PET'), 
	(select id from medical_info where driver_id = driver_id order by updated_at desc limit 1), 
    (select mine_sight_id from company_mine_sight where company_id = (select company_id from driver where phone_number='623-572-9586') limit 1), 
    curdate()
);

-- Fill in the location table
insert into location (latitude, longitude, record_time, vehicle_id) values 
(33.621198, -112.262954, CURRENT_TIMESTAMP(), (select id from vehicle where serial_number='FEOFEK34FW2424')),
(39.768769, -84.228603, CURRENT_TIMESTAMP(), (select id from vehicle where serial_number='AOOFEL34FW2424')),
(33.621198, -112.262954, CURRENT_TIMESTAMP(), (select id from vehicle where serial_number='AOOFDL34FW2424')),
(39.768769, -84.228603, CURRENT_TIMESTAMP(), (select id from vehicle where serial_number='FEOFEK34FW2424')),
(33.621198, -112.262954, CURRENT_TIMESTAMP(), (select id from vehicle where serial_number='BEOAZAZAZW2424')),
(39.768769, -84.228603, CURRENT_TIMESTAMP(), (select id from vehicle where serial_number='T300EL34FW2333')),
(33.621198, -112.262954, CURRENT_TIMESTAMP(), (select id from vehicle where serial_number='BEOCEK34FW2424')),
(39.768769, -84.228603, CURRENT_TIMESTAMP(), (select id from vehicle where serial_number='GRTFDL34FW2424')),
(33.621198, -112.262954, CURRENT_TIMESTAMP(), (select id from vehicle where serial_number='LIOFEL34FW2333')),
(39.768769, -84.228603, CURRENT_TIMESTAMP(), (select id from vehicle where serial_number='LIOFEL34FW2333')),
(33.621198, -112.262954, CURRENT_TIMESTAMP(), (select id from vehicle where serial_number='XRTFDL34FW2424')),
(39.768769, -84.228603, CURRENT_TIMESTAMP(), (select id from vehicle where serial_number='TESFEK34FW2PET'));

-- Fill in the fatigue_monitoring table
insert into fatigue_monitoring (driver_id, work_shift_id, is_critical, fatigue_level_title, location_id, vehicle_speed_km_per_h, record_time) values
(
	(select id from driver where phone_number='281-721-3145'),
	(select id from work_shift where driver_id = (select id from driver where phone_number='281-721-3145') order by begin_at desc limit 1),
    false,
    'Level 2',
    (select id from location where vehicle_id = 
		(select vehicle_id from work_shift where driver_id = (select id from driver where phone_number='281-721-3145' limit 1) order by begin_at desc limit 1) order by record_time desc limit 1),
    88,
    CURRENT_TIMESTAMP()
),
(
	(select id from driver where phone_number='205-699-0780'),
	(select id from work_shift where driver_id = (select id from driver where phone_number='205-699-0780') order by begin_at desc limit 1),
    false,
    'Level 3',
    (select id from location where vehicle_id = 
		(select vehicle_id from work_shift where driver_id = (select id from driver where phone_number='205-699-0780' limit 1) order by begin_at desc limit 1) order by record_time desc limit 1),
    48,
    CURRENT_TIMESTAMP()
),
(
	(select id from driver where phone_number='715-829-3484'),
	(select id from work_shift where driver_id = (select id from driver where phone_number='715-829-3484') order by begin_at desc limit 1),
    false,
    'Level 2',
    (select id from location where vehicle_id = 
		(select vehicle_id from work_shift where driver_id = (select id from driver where phone_number='715-829-3484' limit 1) order by begin_at desc limit 1) order by record_time desc limit 1),
    48,
    CURRENT_TIMESTAMP()
),
(
	(select id from driver where phone_number='907-383-4156'),
	(select id from work_shift where driver_id = (select id from driver where phone_number='907-383-4156') order by begin_at desc limit 1),
    false,
    'Level 2',
    (select id from location where vehicle_id = 
		(select vehicle_id from work_shift where driver_id = (select id from driver where phone_number='907-383-4156' limit 1) order by begin_at desc limit 1) order by record_time desc limit 1),
    88,
    CURRENT_TIMESTAMP()
),
(
	(select id from driver where phone_number='214-446-8679'),
	(select id from work_shift where driver_id = (select id from driver where phone_number='214-446-8679') order by begin_at desc limit 1),
    false,
    'Level 3',
    (select id from location where vehicle_id = 
		(select vehicle_id from work_shift where driver_id = (select id from driver where phone_number='214-446-8679' limit 1) order by begin_at desc limit 1) order by record_time desc limit 1),
    48,
    CURRENT_TIMESTAMP()
),
(
	(select id from driver where phone_number='213-367-7665'),
	(select id from work_shift where driver_id = (select id from driver where phone_number='213-367-7665') order by begin_at desc limit 1),
    false,
    'Level 2',
    (select id from location where vehicle_id = 
		(select vehicle_id from work_shift where driver_id = (select id from driver where phone_number='213-367-7665' limit 1) order by begin_at desc limit 1) order by record_time desc limit 1),
    48,
    CURRENT_TIMESTAMP()
),
(
	(select id from driver where phone_number='218-364-0840'),
	(select id from work_shift where driver_id = (select id from driver where phone_number='218-364-0840') order by begin_at desc limit 1),
    false,
    'Level 2',
    (select id from location where vehicle_id = 
		(select vehicle_id from work_shift where driver_id = (select id from driver where phone_number='218-364-0840' limit 1) order by begin_at desc limit 1) order by record_time desc limit 1),
    48,
    CURRENT_TIMESTAMP()
),
(
	(select id from driver where phone_number='423-896-8310'),
	(select id from work_shift where driver_id = (select id from driver where phone_number='423-896-8310') order by begin_at desc limit 1),
    false,
    'Level 2',
    (select id from location where vehicle_id = 
		(select vehicle_id from work_shift where driver_id = (select id from driver where phone_number='423-896-8310' limit 1) order by begin_at desc limit 1) order by record_time desc limit 1),
    48,
    CURRENT_TIMESTAMP()
),
(
	(select id from driver where phone_number='224-993-6981'),
	(select id from work_shift where driver_id = (select id from driver where phone_number='224-993-6981') order by begin_at desc limit 1),
    false,
    'Level 2',
    (select id from location where vehicle_id = 
		(select vehicle_id from work_shift where driver_id = (select id from driver where phone_number='224-993-6981' limit 1) order by begin_at desc limit 1) order by record_time desc limit 1),
    48,
    CURRENT_TIMESTAMP()
),
(
	(select id from driver where phone_number='623-572-9586'),
	(select id from work_shift where driver_id = (select id from driver where phone_number='623-572-9586') order by begin_at desc limit 1),
    false,
    'Level 2',
    (select id from location where vehicle_id = 
		(select vehicle_id from work_shift where driver_id = (select id from driver where phone_number='623-572-9586' limit 1) order by begin_at desc limit 1) order by record_time desc limit 1),
    48,
    CURRENT_TIMESTAMP()
);



-- ADD INDEXES
ALTER TABLE fatigue_monitoring ADD INDEX (driver_id);
ALTER TABLE  work_shift ADD INDEX (driver_id, vehicle_id);
ALTER TABLE driver ADD INDEX (phone_number);

SHOW INDEXES FROM fatigue_monitoring;
SHOW INDEXES FROM work_shift;