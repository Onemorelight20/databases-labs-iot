use bohdan_boretskyi_smartcap;

-- #1 to conveniently see company employees
select title company_title, name, surname from driver d inner join company c on d.company_id = c.id order by company_title;

-- #2 to see work shifts of specific driver in this month
select concat(d.name, ' ', d.surname) as driver, count(w_s.id) as working_shifts_this_month 
	from driver d join work_shift w_s on d.id = w_s.driver_id group by d.id;
    
-- #3 to see the second biggest mine sight in USA
select * from mine_sight where country = 'USA' 
	order by area_in_square_meeters desc 
    limit 1, 1;
    
-- #4 to find the average speed of each vehicle for the work shift
select work_shift_id, concat(d.name, ' ', d.surname) as driver, concat(v.brand, ' ', v.model) as vehicle, avg_speed 
	from work_shift w_s 
    join (select work_shift_id, avg(vehicle_speed_km_per_h) avg_speed from fatigue_monitoring group by work_shift_id) f 
    on f.work_shift_id = w_s.id
    join vehicle v on v.id = w_s.vehicle_id
    join driver d on w_s.driver_id = d.id;
    
-- #5 show drivers with high fatigue level
select name, surname, work_shift_id, fatigue_level_title, location_id, vehicle_speed_km_per_h, record_time
	from fatigue_monitoring f_m join driver d on d.id = f_m.driver_id where fatigue_level_title>='Level 3'; 
    
-- #6 show vehicles with 20_000 <= weight <= 100_000
select * from vehicle where weight_in_kilos between 20000 and 100000;

-- #7 show sensors that were installed during a leap year (e. g. sensors were broken during what time)
select * from sensor 
	where (year(date_installed) % 4 = 0 and year(date_installed) % 100 <> 0) or year(date_installed) % 400 = 0;
    
-- #8 show drivers that are driving ONLY trucks
select * from vehicle_type;
select * from work_shift;
select * from vehicle;