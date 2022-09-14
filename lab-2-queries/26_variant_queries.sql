use lab_2_SQL;

-- task 1
-- 1. БД «Аеропорт». Знайдіть номери всіх рейсів, що бувають у місті
-- 'London'. Вивести: trip_no, town_from, town_to. Вихідні дані впоряд-
-- кувати за зростанням за стовпцем time_out. 
select trip_no, town_from, town_to from trip where town_from='London' or town_to='London' ORDER BY time_out ASC;

-- task 2
-- 2. БД «Аеропорт». З таблиці Pass_in_trip вивести дати, коли були зай-
-- няті місця 'c' у будь-якому ряді.
select date from pass_in_trip where place like '_c';

-- task 3
-- 3. БД «Комп. фірма». Вкажіть виробника для тих ПК, що мають
-- жорсткий диск об’ємом не більше 8 Гбайт. Вивести: maker, type,
-- speed, hd.
select maker, type, speed, hd from pc inner join product using(model) where hd <= 8;

-- task 4
-- 4. БД «Комп. фірма». Знайдіть виробників, що випускають ПК, але не
-- ноутбуки (використати ключове слово ALL). Вивести maker.
select distinct maker from product where 
	maker <> ALL(select maker from laptop inner join product using(model)) 
    and maker = ANY(select maker from pc inner join product using(model));
    
-- task 5
-- 5. БД «Кораблі». Знайдіть класи кораблів, у яких хоча б один корабель
-- був затоплений у битвах. Вивести: class. (Назви класів кораблів визна-
-- чати за таблицею Ships, якщо його там немає, тоді порівнювати чи
-- його назва не співпадає з назвою класу, тобто він є головним)
-- select * from ships;
-- select * from outcomes where result = 'sunk';
select if(class is null, ship, class) as result_class 
	from (select * from outcomes where result = 'sunk') as sunk_outcome 
    left join ships on ships.name = sunk_outcome.ship;
    
-- task 6
-- 6. БД «Комп. фірма». Для таблиці PC вивести всю інформацію з
-- коментарями в кожній комірці, наприклад, 'модель: 1121', 'ціна:
-- 600,00' і т.д.
select concat('code: ', code) comm_code, 
	concat('model: ', model) comm_model, 
    concat('speed: ', speed) comm_speed, 
    concat('ram: ', ram) comm_ram, 
    concat('hd: ', hd) comm_hd, 
    concat('cd: ', cd) comm_cd, 
    concat('price: ', price) comm_price from pc;
    
-- task 7
-- 7. БД «Комп. фірма». Знайдіть виробників найдешевших кольорових
-- принтерів. Вивести: maker, price.
select maker, price from printer join product 
	on printer.model = product.model
	order by printer.price asc
    LIMIT 3;
    
-- task 8
-- 8. БД «Комп. фірма». Для таблиці Product отримати підсумковий набір
-- у вигляді таблиці зі стовпцями maker, pc, laptop та printer, у якій для
-- кожного виробника необхідно вказати кількість продукції, що ним
-- випускається, тобто наявну загальну кількість продукції в таблицях,
-- відповідно, PC, Laptop та Printer. (Підказка: використовувати підза-
-- пити в якості обчислювальних стовпців)    
select maker, 
	COUNT(pc_id) as pc, 
	COUNT(laptop_id) as laptop, 
    COUNT(printer_id) as printer from product 
	left join (select model as pc_id, model from pc) t1 using(model) 
    left join (select model as laptop_id, model from laptop) t2 using(model) 
    left join (select model as printer_id, model from printer) t3 using(model)
    group by maker
    order by maker;
    
-- task 9
-- 9. БД «Фірма прий. вторсировини». Приймаючи, що прихід та розхід
-- грошей на кожному пункті прийому може фіксуватися довільне число
-- раз на день (лише таблиці Income та Outcome), написати запит із таки-
-- ми вихідними даними: point (пункт), date (дата), inc (прихід), out (роз-
-- хід), у якому в кожному пункті за кожну дату відповідає лише одна
-- стрічка. (Підказка: використовувати зовнішнє з’єднання та оператор
-- CASE)
select * from income left join outcome using(point, date);
select * from income;
select * from income_o;
select * from income left join outcome using(point, date);
select * from income rignt join outcome using(point, date);