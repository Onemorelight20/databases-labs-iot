    
select * from vehicle;
select * from vehicle_type;
insert into vehicle (brand, model, manufacturing_date, serial_number, vehicle_type_id) values
    ('Haul', '2422', '2000-12-30', 'FEOFETESTTW2424', 10);
    
update vehicle set vehicle_type_id = 3 where id=16;
    
create table debug(
	val VARCHAR(50)
);

select * from debug;

update vehicle_type set id = 10 where id = 1;
delete from vehicle_type where id = 1;

show triggers;
