drop  schema if exists `bohdan_boretskyi_smartcap`;
create schema `bohdan_boretskyi_smartcap`;

use bohdan_boretskyi_smartcap;

-- tables
-- Table: company
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

-- Table: doctor
CREATE TABLE doctor (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    surname varchar(50) NOT NULL,
    phone_number varchar(50) NOT NULL unique,
    CONSTRAINT doctor_pk PRIMARY KEY (id)
);

-- Table: driver
CREATE TABLE driver (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    surname varchar(50) NOT NULL,
    company_id int NOT NULL,
    phone_number varchar(40) NOT NULL unique,
    CONSTRAINT id PRIMARY KEY (id)
);

-- Table: fatigue_level
CREATE TABLE fatigue_level (
    level_title varchar(40) NOT NULL unique,
    CONSTRAINT fatigue_level_pk PRIMARY KEY (level_title)
);

-- Table: fatigue_monitoring
CREATE TABLE fatigue_monitoring (
    id int NOT NULL AUTO_INCREMENT,
    work_shift_id int NOT NULL,
    is_critical bool NOT NULL,
    fatigue_level_level_title varchar(40) NOT NULL,
    location_id int NOT NULL,
    vehicle_speed_km_per_h int NOT NULL,
    record_time timestamp NOT NULL,
    sensor_id int NOT NULL,
    CONSTRAINT fatigue_monitoring_pk PRIMARY KEY (id)
);

-- Table: location
CREATE TABLE location (
   id int NOT NULL AUTO_INCREMENT,
   latitude decimal(8,6) NOT NULL,
   longitude decimal(9,6) NOT NULL,
   record_time timestamp NOT NULL,
   vehicle_id int NOT NULL,
   CONSTRAINT location_pk PRIMARY KEY (id)
);

-- Table: medical_info
CREATE TABLE medical_info (
    id int NOT NULL AUTO_INCREMENT,
    driver_id int NOT NULL,
    sight_state varchar(50) NOT NULL,
    blood_type varchar(50) NOT NULL,
    doctor_id int NOT NULL,
    updated_at date NOT NULL,
    CONSTRAINT medical_info_pk PRIMARY KEY (id)
);

-- Table: mine_sight
CREATE TABLE mine_sight (
    id int NOT NULL AUTO_INCREMENT,
    country varchar(50) NOT NULL,
    city varchar(100) NOT NULL,
    title varchar(100) NOT NULL,
    area_in_square_meeters int NOT NULL,
    CONSTRAINT mine_sight_pk PRIMARY KEY (id)
);

-- Table: sensor
CREATE TABLE sensor (
    id int NOT NULL AUTO_INCREMENT,
    brand varchar(50) NOT NULL,
    model varchar(50) NOT NULL,
    date_installed date NOT NULL,
    vehicle_id int NOT NULL,
    CONSTRAINT sensor_pk PRIMARY KEY (id)
);

-- Table: vehicle
CREATE TABLE vehicle (
    id int NOT NULL AUTO_INCREMENT,
	brand varchar(50) NOT NULL,
	model varchar(50) NOT NULL,
	manufacturing_date date NOT NULL,
    licence_plate_number varchar(50) NOT NULL unique,
    serial_number varchar(50) NOT NULL unique,
    vehicle_type_id int NOT NULL,
    weight_in_kilos int NOT NULL,
    UNIQUE INDEX vehicle_ak_1_licence_plate_number (licence_plate_number),
    CONSTRAINT vehicle_pk PRIMARY KEY (id)
);

-- Table: vehicle_type
CREATE TABLE vehicle_type (
    id int NOT NULL AUTO_INCREMENT,
    type varchar(50) NOT NULL unique,
    CONSTRAINT vehicle_type_pk PRIMARY KEY (id)
);

-- Table: work_shift
CREATE TABLE work_shift (
    id int NOT NULL AUTO_INCREMENT,
    driver_id int NOT NULL,
    vehicle_id int NOT NULL,
    medical_info_id int NOT NULL,
    mine_sight_id int NOT NULL,
    begin_at timestamp NOT NULL,
    end_at int NULL,
    CONSTRAINT work_shift_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: company_mine_sight_company (table: company_mine_sight)
ALTER TABLE company_mine_sight ADD CONSTRAINT company_mine_sight_company FOREIGN KEY company_mine_sight_company (company_id)
    REFERENCES company (id);
    
ALTER TABLE location ADD CONSTRAINT location_vehicle FOREIGN KEY location_vehicle (vehicle_id)
    REFERENCES vehicle (id);

-- Reference: company_mine_sight_mine_sight (table: company_mine_sight)
ALTER TABLE company_mine_sight ADD CONSTRAINT company_mine_sight_mine_sight FOREIGN KEY company_mine_sight_mine_sight (mine_sight_id)
    REFERENCES mine_sight (id);

-- Reference: driver_company (table: driver)
ALTER TABLE driver ADD CONSTRAINT driver_company FOREIGN KEY driver_company (company_id)
    REFERENCES company (id);

-- Reference: fatigue_monitoring_fatigue_level (table: fatigue_monitoring)
ALTER TABLE fatigue_monitoring ADD CONSTRAINT fatigue_monitoring_fatigue_level FOREIGN KEY fatigue_monitoring_fatigue_level (fatigue_level_level_title)
    REFERENCES fatigue_level (level_title);

-- Reference: fatigue_monitoring_location (table: fatigue_monitoring)
ALTER TABLE fatigue_monitoring ADD CONSTRAINT fatigue_monitoring_location FOREIGN KEY fatigue_monitoring_location (location_id)
    REFERENCES location (id);

-- Reference: fatigue_monitoring_sensor (table: fatigue_monitoring)
ALTER TABLE fatigue_monitoring ADD CONSTRAINT fatigue_monitoring_sensor FOREIGN KEY fatigue_monitoring_sensor (sensor_id)
    REFERENCES sensor (id);

-- Reference: fatigue_monitoring_work_shift (table: fatigue_monitoring)
ALTER TABLE fatigue_monitoring ADD CONSTRAINT fatigue_monitoring_work_shift FOREIGN KEY fatigue_monitoring_work_shift (work_shift_id)
    REFERENCES work_shift (id);

-- Reference: medical_info_doctor (table: medical_info)
ALTER TABLE medical_info ADD CONSTRAINT medical_info_doctor FOREIGN KEY medical_info_doctor (doctor_id)
    REFERENCES doctor (id);

-- Reference: medical_info_driver (table: medical_info)
ALTER TABLE medical_info ADD CONSTRAINT medical_info_driver FOREIGN KEY medical_info_driver (driver_id)
    REFERENCES driver (id);

-- Reference: sensor_vehicle (table: sensor)
ALTER TABLE sensor ADD CONSTRAINT sensor_vehicle FOREIGN KEY sensor_vehicle (vehicle_id)
    REFERENCES vehicle (id);

-- Reference: vehicle_vehicle_type (table: vehicle)
ALTER TABLE vehicle ADD CONSTRAINT vehicle_vehicle_type FOREIGN KEY vehicle_vehicle_type (vehicle_type_id)
    REFERENCES vehicle_type (id);

-- Reference: work_shift_driver (table: work_shift)
ALTER TABLE work_shift ADD CONSTRAINT work_shift_driver FOREIGN KEY work_shift_driver (driver_id)
    REFERENCES driver (id);

-- Reference: work_shift_medical_info (table: work_shift)
ALTER TABLE work_shift ADD CONSTRAINT work_shift_medical_info FOREIGN KEY work_shift_medical_info (medical_info_id)
    REFERENCES medical_info (id);

-- Reference: work_shift_mine_sight_info (table: work_shift)
ALTER TABLE work_shift ADD CONSTRAINT work_shift_mine_sight_info FOREIGN KEY work_shift_mine_sight_info (mine_sight_id)
    REFERENCES mine_sight (id);

-- Reference: work_shift_vehicle (table: work_shift)
ALTER TABLE work_shift ADD CONSTRAINT work_shift_vehicle FOREIGN KEY work_shift_vehicle (vehicle_id)
    REFERENCES vehicle (id);

-- End of file.

